import java.awt.*;
import java.awt.event.*;
import java.io.File;
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


    public static void main(String[] args) {
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
