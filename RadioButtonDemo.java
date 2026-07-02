import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * RadioButtonDemo - Java Assignment 2
 * 
 * An application that uses five radio buttons to let you choose which kind of pet is displayed:
 * Bird, Cat, Dog, Rabbit, and Pig.
 * Displays the selection using a message box (JOptionPane) and updates the image.
 */
public class RadioButtonDemo extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    // Pet names
    private static final String BIRD = "Bird";
    private static final String CAT = "Cat";
    private static final String DOG = "Dog";
    private static final String RABBIT = "Rabbit";
    private static final String PIG = "Pig";

    private JLabel pictureLabel;
    private JRadioButton birdButton;
    private JRadioButton catButton;
    private JRadioButton dogButton;
    private JRadioButton rabbitButton;
    private JRadioButton pigButton;

    public RadioButtonDemo() {
        super("RadioButtonDemo");

        // Set up main window properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));
        setResizable(false);

        // Ensure images exist in the images/ directory
        ensureImagesExist();

        // Create the radio buttons
        birdButton = new JRadioButton(BIRD);
        birdButton.setMnemonic(KeyEvent.VK_B);
        birdButton.setActionCommand(BIRD);

        catButton = new JRadioButton(CAT);
        catButton.setMnemonic(KeyEvent.VK_C);
        catButton.setActionCommand(CAT);

        dogButton = new JRadioButton(DOG);
        dogButton.setMnemonic(KeyEvent.VK_D);
        dogButton.setActionCommand(DOG);

        rabbitButton = new JRadioButton(RABBIT);
        rabbitButton.setMnemonic(KeyEvent.VK_R);
        rabbitButton.setActionCommand(RABBIT);

        pigButton = new JRadioButton(PIG);
        pigButton.setMnemonic(KeyEvent.VK_P);
        pigButton.setActionCommand(PIG);

        // Group the radio buttons so only one can be selected at a time
        ButtonGroup group = new ButtonGroup();
        group.add(birdButton);
        group.add(catButton);
        group.add(dogButton);
        group.add(rabbitButton);
        group.add(pigButton);

        // Register event listeners for the radio buttons
        birdButton.addActionListener(this);
        catButton.addActionListener(this);
        dogButton.addActionListener(this);
        rabbitButton.addActionListener(this);
        pigButton.addActionListener(this);

        // Put the radio buttons in a column panel
        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new GridLayout(0, 1, 5, 5));
        radioPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Select a Pet"),
                new EmptyBorder(10, 10, 10, 10)));
        radioPanel.add(birdButton);
        radioPanel.add(catButton);
        radioPanel.add(dogButton);
        radioPanel.add(rabbitButton);
        radioPanel.add(pigButton);

        // Set up the label that displays the image
        pictureLabel = new JLabel();
        pictureLabel.setHorizontalAlignment(JLabel.CENTER);
        pictureLabel.setVerticalAlignment(JLabel.CENTER);
        pictureLabel.setPreferredSize(new Dimension(250, 200));
        pictureLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEtchedBorder(),
                new EmptyBorder(10, 10, 10, 10)));

        // Default selection: Pig (matching the screenshot in Assignment 2)
        pigButton.setSelected(true);
        updatePicture(PIG);

        // Add panels to the frame
        add(radioPanel, BorderLayout.WEST);
        add(pictureLabel, BorderLayout.CENTER);

        // Add padding around the main content
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(15, 15, 15, 15));

        // Pack and center window on screen
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Handles radio button click events.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedPet = e.getActionCommand();

        // 1. Update the displayed picture
        updatePicture(selectedPet);

        // 2. Display selection using a message box as required by assignment
        JOptionPane.showMessageDialog(
                this,
                "You selected: " + selectedPet,
                "Pet Selection",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Updates the picture label with the selected pet image.
     */
    private void updatePicture(String petName) {
        ImageIcon icon = createImageIcon("images/" + petName + ".png");
        if (icon != null) {
            pictureLabel.setIcon(icon);
            pictureLabel.setText(null);
        } else {
            pictureLabel.setIcon(null);
            pictureLabel.setText("Image not found: " + petName);
        }
    }

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     */
    private ImageIcon createImageIcon(String path) {
        File imgFile = new File(path);
        if (imgFile.exists()) {
            return new ImageIcon(imgFile.getAbsolutePath());
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Helper method to generate sample pet illustrations if they do not exist.
     * Ensures the program runs smoothly anywhere without external dependencies.
     */
    private static void ensureImagesExist() {
        File imgDir = new File("images");
        if (!imgDir.exists()) {
            imgDir.mkdirs();
        }

        String[] pets = { BIRD, CAT, DOG, RABBIT, PIG };
        Color[] bgColors = {
            new Color(225, 245, 254), // Light Blue for Bird
            new Color(255, 243, 224), // Light Orange for Cat
            new Color(239, 235, 233), // Light Brown for Dog
            new Color(243, 229, 245), // Light Purple for Rabbit
            new Color(252, 228, 236)  // Light Pink for Pig
        };

        for (int i = 0; i < pets.length; i++) {
            File imgFile = new File(imgDir, pets[i] + ".png");
            if (!imgFile.exists()) {
                generatePetImage(imgFile, pets[i], bgColors[i]);
            }
        }
    }

    /**
     * Generates a cute, styled cartoon representation for each pet.
     */
    private static void generatePetImage(File file, String petName, Color bgColor) {
        int width = 220;
        int height = 180;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        // Enable anti-aliasing for smooth drawing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Draw background rounded rectangle
        g2d.setColor(bgColor);
        g2d.fillRoundRect(10, 10, width - 20, height - 20, 25, 25);
        g2d.setColor(bgColor.darker());
        g2d.setStroke(new BasicStroke(3f));
        g2d.drawRoundRect(10, 10, width - 20, height - 20, 25, 25);

        // Draw pet illustration
        g2d.setColor(Color.DARK_GRAY);
        if (petName.equals(PIG)) {
            // Draw Pig body & head (pinkish circle)
            g2d.setColor(new Color(248, 165, 194));
            g2d.fillOval(50, 40, 120, 90);
            g2d.setColor(new Color(224, 86, 136));
            g2d.setStroke(new BasicStroke(2f));
            g2d.drawOval(50, 40, 120, 90);
            // Snout
            g2d.setColor(new Color(247, 143, 179));
            g2d.fillOval(90, 75, 40, 28);
            g2d.setColor(Color.DARK_GRAY);
            g2d.drawOval(90, 75, 40, 28);
            g2d.fillOval(102, 85, 5, 8);
            g2d.fillOval(113, 85, 5, 8);
            // Eyes
            g2d.fillOval(80, 60, 8, 8);
            g2d.fillOval(130, 60, 8, 8);
            // Ears
            int[] xPointsL = {60, 50, 75};
            int[] yPointsL = {50, 25, 45};
            g2d.setColor(new Color(248, 165, 194));
            g2d.fillPolygon(xPointsL, yPointsL, 3);
            int[] xPointsR = {145, 170, 160};
            int[] yPointsR = {45, 25, 50};
            g2d.fillPolygon(xPointsR, yPointsR, 3);
        } else if (petName.equals(CAT)) {
            g2d.setColor(new Color(255, 192, 72));
            g2d.fillOval(60, 45, 100, 80);
            // Ears
            int[] xL = {65, 55, 85};
            int[] yL = {55, 20, 45};
            g2d.fillPolygon(xL, yL, 3);
            int[] xR = {135, 165, 155};
            int[] yR = {45, 20, 55};
            g2d.fillPolygon(xR, yR, 3);
            // Eyes & Nose
            g2d.setColor(Color.DARK_GRAY);
            g2d.fillOval(80, 65, 8, 8);
            g2d.fillOval(130, 65, 8, 8);
            g2d.setColor(Color.PINK);
            g2d.fillPolygon(new int[]{105, 100, 110}, new int[]{85, 80, 80}, 3);
            // Whiskers
            g2d.setColor(Color.DARK_GRAY);
            g2d.setStroke(new BasicStroke(1.5f));
            g2d.drawLine(60, 80, 35, 75);
            g2d.drawLine(60, 85, 35, 85);
            g2d.drawLine(160, 80, 185, 75);
            g2d.drawLine(160, 85, 185, 85);
        } else if (petName.equals(DOG)) {
            g2d.setColor(new Color(210, 153, 34));
            g2d.fillOval(60, 45, 100, 85);
            // Floopy ears
            g2d.setColor(new Color(138, 90, 0));
            g2d.fillOval(45, 50, 25, 60);
            g2d.fillOval(150, 50, 25, 60);
            // Eyes & Nose
            g2d.setColor(Color.DARK_GRAY);
            g2d.fillOval(82, 65, 9, 9);
            g2d.fillOval(128, 65, 9, 9);
            g2d.fillOval(100, 85, 18, 12);
        } else if (petName.equals(RABBIT)) {
            g2d.setColor(Color.WHITE);
            g2d.fillOval(65, 60, 90, 75);
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.drawOval(65, 60, 90, 75);
            // Long Ears
            g2d.setColor(Color.WHITE);
            g2d.fillOval(75, 15, 22, 60);
            g2d.fillOval(120, 15, 22, 60);
            g2d.setColor(Color.PINK);
            g2d.fillOval(80, 25, 12, 40);
            g2d.fillOval(125, 25, 12, 40);
            // Eyes & Nose
            g2d.setColor(Color.RED);
            g2d.fillOval(85, 80, 8, 8);
            g2d.fillOval(125, 80, 8, 8);
            g2d.setColor(Color.PINK);
            g2d.fillOval(106, 95, 8, 6);
        } else if (petName.equals(BIRD)) {
            g2d.setColor(new Color(72, 219, 251));
            g2d.fillOval(65, 50, 90, 75);
            // Wing
            g2d.setColor(new Color(10, 189, 227));
            g2d.fillArc(80, 65, 50, 40, 0, -180);
            // Beak
            g2d.setColor(Color.ORANGE);
            g2d.fillPolygon(new int[]{150, 175, 150}, new int[]{65, 72, 80}, 3);
            // Eye
            g2d.setColor(Color.DARK_GRAY);
            g2d.fillOval(135, 60, 8, 8);
        }

        // Draw label banner at bottom of image
        g2d.setColor(new Color(0, 0, 0, 150));
        g2d.fillRoundRect(40, 135, width - 80, 25, 12, 12);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("SansSerif", Font.BOLD, 14));
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(petName);
        g2d.drawString(petName, (width - textWidth) / 2, 153);

        g2d.dispose();

        try {
            ImageIO.write(image, "png", file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("-generate-images")) {
            ensureImagesExist();
            System.out.println("Pet images generated successfully in images/ directory.");
            return;
        }

        // Use system look and feel for a cleaner GUI
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Fallback to default look and feel
        }

        // Run GUI on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            RadioButtonDemo frame = new RadioButtonDemo();
            frame.setVisible(true);
        });
    }
}
