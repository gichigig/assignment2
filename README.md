# Java Assignment 2 - Pet Radio Button Application 🐾

A desktop application built using **Java Swing** that demonstrates the use of radio buttons (`JRadioButton`) to select and display different pet images. When a selection is made, the application displays a confirmation message box (`JOptionPane`) as required by the assignment specification.

---

## ✨ Features
1. **Five Pet Selections**: Choose between **Bird**, **Cat**, **Dog**, **Rabbit**, and **Pig**.
2. **Dynamic Image Display**: Selecting a radio button instantly updates the displayed pet picture in the center panel.
3. **Interactive Message Box**: Displays a GUI dialog box (`JOptionPane.showMessageDialog`) confirming your selection whenever a radio button is clicked.
4. **Standalone & Portable**: Includes an automatic image generator (`ensureImagesExist()`) that renders cute cartoon pet illustrations on the fly if images are missing. You can compile and run this project anywhere without external dependencies!

---

## 🛠️ How to Compile and Run

### Prerequisites
- Java Development Kit (JDK 8 or higher) installed on your machine.

### Compile the Application
Open your terminal/command prompt in the root project directory and run:
```bash
javac RadioButtonDemo.java
```

### Run the Application
To launch the GUI application:
```bash
java RadioButtonDemo
```

*(Optional)* To generate the pet illustration images (`images/` folder) without launching the GUI:
```bash
java RadioButtonDemo -generate-images
```

---

## 📂 Project Structure
```text
assignment2/
├── RadioButtonDemo.java    # Main application source code
├── images/                 # Directory containing pet PNG illustrations
│   ├── Bird.png
│   ├── Cat.png
│   ├── Dog.png
│   ├── Rabbit.png
│   └── Pig.png
├── .gitignore
└── README.md               # Project documentation
```

---

## 💻 Source Code Overview
- **`RadioButtonDemo` class**: Extends `JFrame` and implements `ActionListener` to handle user selection events.
- **`ButtonGroup`**: Ensures that only one radio button can be selected at a time.
- **`actionPerformed`**: Triggers whenever a user selects a pet:
  1. Updates the `JLabel` icon to show the selected pet image.
  2. Spawns a `JOptionPane.showMessageDialog` showing the message `"You selected: [Pet Name]"`.
- **`generatePetImage`**: Draws cute custom cartoon illustrations using Java's `Graphics2D` API and saves them as `.png` files in the `images/` directory.
