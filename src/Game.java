import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.awt.FontFormatException;
import java.util.Random;

public class Game {

    JLabel hpLabel, hpJLabelNumber, weaponLabel, weaponLableNumber;
    JFrame window;
    Container con;
    JButton startButton, choice1, choice2, choice3, choice4;
    JPanel mainTextPanel, choiceButtoPanel, panel, playerPanel;
    JTextArea mainTextArea;
    int playerHP;
    String weapon;

    TitleScreenHandler tsHandler = new TitleScreenHandler();
    Timer glitchTimer;

    // Animated text variable
    String text = "I found a body beside the reactor! Somebody killed orange urgh you bastard! What we gonna do this is a disaster!";
    int charIndex = 0;
    Timer timer;

    public static void main(String[] args) {
        new Game();
    }

    // Constructor
    public Game() {
        // JFrame
        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        window.getContentPane().setBackground(Color.black);
        con = window.getContentPane();

        // Main JPanel
        panel = new JPanel(null);
        panel.setBounds(0, 0, 800, 600);
        panel.setBackground(Color.BLACK);

        // Main title label
        JLabel titleNameLabelMain = new JLabel("Cyberpunk");
        titleNameLabelMain.setForeground(Color.CYAN);
        titleNameLabelMain.setFont(loadCustomFont("/C:/Users/Alina Baum/Downloads/sdglitchdemo/SDGlitch_Demo.ttf", Font.BOLD | Font.ITALIC, 130));
        titleNameLabelMain.setBounds(120, 160, 670, 200);

        // Glow title label
        JLabel titleNameLabel = new JLabel("CYBERPUNK");
        titleNameLabel.setForeground(new Color(0, 255, 255, 50));
        titleNameLabel.setFont(loadCustomFont("/C:/Users/Alina Baum/Downloads/sdglitchdemo/SDGlitch_Demo.ttf", Font.BOLD | Font.ITALIC, 134));
        titleNameLabel.setBounds(120, 160, 670, 200);

        // Add labels to main panel
        panel.add(titleNameLabelMain);
        panel.add(titleNameLabel);

        // Start glitch effect
        startGlitchEffect(titleNameLabelMain);

        // Panel for start button
        JPanel startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300, 400, 200, 100);
        startButtonPanel.setBackground(Color.BLACK);

        // Start button
        startButton = new JButton("START");
        startButton.setBackground(Color.BLACK);
        startButton.setForeground(Color.CYAN);
        startButton.setFont(loadCustomFont("/C:/Users/Alina Baum/Downloads/sdglitchdemo/SDGlitch_Demo.ttf", Font.BOLD | Font.ITALIC, 25));
        startButton.addActionListener(tsHandler);

        // Add start button to start button panel
        startButtonPanel.add(startButton);

        // Add start button panel to main panel
        panel.add(startButtonPanel);

        // Add main panel to JFrame
        con.add(panel);

        // Make JFrame visible
        window.setVisible(true);
    }

    // Method to load custom font
    private Font loadCustomFont(String fontPath, int style, float size) {
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(style, size);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            return customFont;
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return new Font("Serif", Font.PLAIN, 12); // Fallback font
        }
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------

    public void createGameScreen() {
        panel.setVisible(false);

        // Main Text Panel
        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100, 100, 600, 250);
        mainTextPanel.setBackground(Color.BLACK);
        mainTextPanel.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        con.add(mainTextPanel);

        // Main text
        mainTextArea = new JTextArea();
        mainTextArea.setBounds(100, 100, 600, 250);
        mainTextArea.setBackground(Color.BLACK);
        mainTextArea.setForeground(new Color(255, 0, 119, 200));
        mainTextArea.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 25));
        mainTextArea.setLineWrap(true);
        mainTextPanel.add(mainTextArea);

        // Start text animation
        startTextAnimation();

        // Choice Panel
        choiceButtoPanel = new JPanel();
        choiceButtoPanel.setBounds(250, 350, 300, 150);
        choiceButtoPanel.setBackground(Color.BLACK);
        choiceButtoPanel.setLayout(new GridLayout(4, 1));
        choiceButtoPanel.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        con.add(choiceButtoPanel);

        // Buttons for choice
        choice1 = new JButton("Choice 1");
        choice1.setBackground(Color.BLACK);
        choice1.setForeground(new Color(255, 0, 119, 200));
        choice1.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 25));
        choice1.setFocusPainted(false);
        choice1.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 119, 200)));
        choiceButtoPanel.add(choice1);

        choice2 = new JButton("Choice 2");
        choice2.setBackground(Color.BLACK);
        choice2.setForeground(new Color(255, 0, 119, 200));
        choice2.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 25));
        choice2.setFocusPainted(false);
        choice2.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 119, 200)));
        choiceButtoPanel.add(choice2);

        choice3 = new JButton("Choice 3");
        choice3.setBackground(Color.BLACK);
        choice3.setForeground(new Color(255, 0, 119, 200));
        choice3.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 25));
        choice3.setFocusPainted(false);
        choice3.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 119, 200)));
        choiceButtoPanel.add(choice3);

        choice4 = new JButton("Choice 4");
        choice4.setBackground(Color.BLACK);
        choice4.setForeground(new Color(255, 0, 119, 200));
        choice4.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 25));
        choice4.setFocusPainted(false);
        choice4.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 119, 200)));
        choiceButtoPanel.add(choice4);

        // Player Panel
        playerPanel = new JPanel();
        playerPanel.setBounds(100, 15, 600, 50);
        playerPanel.setBackground(Color.BLACK);
        playerPanel.setLayout(new GridLayout(1, 4));
        con.add(playerPanel);

        hpLabel = new JLabel("HP:");
        hpLabel.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 25));
        hpLabel.setForeground(Color.CYAN);
        playerPanel.add(hpLabel);

        hpJLabelNumber = new JLabel();
        hpJLabelNumber.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 25));
        hpJLabelNumber.setForeground(Color.CYAN);
        playerPanel.add(hpJLabelNumber);

        weaponLabel = new JLabel("Weapon:");
        weaponLabel.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 25));
        weaponLabel.setForeground(Color.CYAN);
        playerPanel.add(weaponLabel);

        weaponLableNumber = new JLabel();
        weaponLableNumber.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 25));
        weaponLableNumber.setForeground(Color.CYAN);
        playerPanel.add(weaponLableNumber);
    }

    // Method for the glitch effect
    private void startGlitchEffect(JLabel label) {
        int originalX = label.getX();
        int originalY = label.getY();
        glitchTimer = new Timer(100, new ActionListener() {
            Random rand = new Random();
            String originalText = label.getText();

            public void actionPerformed(ActionEvent e) {
                if (rand.nextInt(10) < 1) { // 70% chance to glitch
                    StringBuilder sb = new StringBuilder(originalText);
                    for (int i = 0; i < rand.nextInt(5) + 1; i++) {
                        int index = rand.nextInt(sb.length());
                        char glitchChar = (char) (rand.nextInt(26) + 'A'); // Random uppercase letter
                        sb.setCharAt(index, glitchChar);
                    }
                    label.setText(sb.toString());
                    int offsetX = rand.nextInt(11) - 5; // Random offset between -5 and 5
                    int offsetY = rand.nextInt(11) - 5; // Random offset between -5 and 5
                    label.setLocation(originalX + offsetX, originalY + offsetY);
                } else {
                    label.setText(originalText); // Reset to original text
                    label.setLocation(originalX, originalY); // Reset to original position
                }
            }
        });
        glitchTimer.start();
    }

    // Method for text animation
    public void startTextAnimation() {
        timer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (charIndex < text.length()) {
                    mainTextArea.append(Character.toString(text.charAt(charIndex)));
                    charIndex++;
                } else {
                    timer.stop(); // Stop the timer once the text is fully displayed
                }
            }
        });
        timer.start();
    }

    public class TitleScreenHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            createGameScreen();
        }
    }
}







      /*  setTitle("Stechenrechner");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new java.awt.Color(36,36,36));
        setLayout(null);
        JLabel ankunftsStundenLabel = new JLabel("Ankunftsstunde:");
        ankunftsStundenLabel.setBounds(20, 20, 150, 25);
        ankunftsStundenLabel.setForeground(new java.awt.Color(255, 255, 255));
        add(ankunftsStundenLabel);
        ankunftsStundenField = new JTextField();
        ankunftsStundenField.setBounds(180, 20, 150, 25);
        add(ankunftsStundenField);
        JLabel ankunftsMinutenLabel = new JLabel("Ankunftsminute:");
        ankunftsMinutenLabel.setBounds(20, 60, 150, 25);
        ankunftsMinutenLabel.setForeground(new java.awt.Color(255, 255, 255));
        add(ankunftsMinutenLabel);
        ankunftsMinutenField = new JTextField();
        ankunftsMinutenField.setBounds(180, 60, 150, 25);
        add(ankunftsMinutenField);
        JLabel bleibZeitLabel = new JLabel("Bleibzeit (in Stunden):");
        bleibZeitLabel.setBounds(20, 100, 150, 25);
        bleibZeitLabel.setForeground(new java.awt.Color(255, 255, 255));
        add(bleibZeitLabel);
        bleibZeitField = new JTextField();
        bleibZeitField.setBounds(180, 100, 150, 25);
        add(bleibZeitField);
        JLabel pausenZeitLabel = new JLabel("Pausenzeit (in Stunden):");
        pausenZeitLabel.setBounds(20, 140, 150, 25);
        pausenZeitLabel.setForeground(new java.awt.Color(255, 255, 255));
        add(pausenZeitLabel);
        pausenZeitField = new JTextField();
        pausenZeitField.setBounds(180, 140, 150, 25);
        add(pausenZeitField);
        JButton berechnenButton = new JButton("Zeit berechnen");
        berechnenButton.setBounds(20, 210, 200, 30);
        berechnenButton.setForeground(new java.awt.Color(255, 255, 255));
        berechnenButton.setBackground(new java.awt.Color(70,70,70));
        add(berechnenButton);
        ergebnisLabel = new JLabel("");
        ergebnisLabel.setBounds(20, 240, 300, 25);
        ergebnisLabel.setForeground(new java.awt.Color(255, 255, 255));
        add(ergebnisLabel);
        ueberstundenLabel = new JLabel("");
        ueberstundenLabel.setBounds(210, 240, 200, 25);
        ueberstundenLabel.setForeground(new java.awt.Color(255, 255, 255));
        add(ueberstundenLabel); */