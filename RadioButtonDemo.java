import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class RadioButtonDemo extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

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

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));
        setResizable(false);


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

        ButtonGroup group = new ButtonGroup();
        group.add(birdButton);
        group.add(catButton);
        group.add(dogButton);
        group.add(rabbitButton);
        group.add(pigButton);

        birdButton.addActionListener(this);
        catButton.addActionListener(this);
        dogButton.addActionListener(this);
        rabbitButton.addActionListener(this);
        pigButton.addActionListener(this);

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

        pictureLabel = new JLabel();
        pictureLabel.setHorizontalAlignment(JLabel.CENTER);
        pictureLabel.setVerticalAlignment(JLabel.CENTER);
        pictureLabel.setPreferredSize(new Dimension(250, 200));
        pictureLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEtchedBorder(),
                new EmptyBorder(10, 10, 10, 10)));

        pigButton.setSelected(true);
        updatePicture(PIG);

        add(radioPanel, BorderLayout.WEST);
        add(pictureLabel, BorderLayout.CENTER);

        ((JPanel) getContentPane()).setBorder(new EmptyBorder(15, 15, 15, 15));

        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedPet = e.getActionCommand();

        updatePicture(selectedPet);

        JOptionPane.showMessageDialog(
                this,
                "You selected: " + selectedPet,
                "Pet Selection",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

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
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
        SwingUtilities.invokeLater(() -> {
            RadioButtonDemo frame = new RadioButtonDemo();
            frame.setVisible(true);
        });
    }
}
