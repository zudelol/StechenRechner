import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.FontFormatException;

public class Zeitrechner extends JFrame {
    private JTextField ankunftsStundenField, ankunftsMinutenField, bleibZeitField, pausenZeitField;
    private JLabel ergebnisLabel, ueberstundenLabel, titleLabelMain;
    private Container con;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Zeitrechner());
        new Musik();
    }


    public Zeitrechner() {
        setTitle("Stechenrechner");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        // Hintergrundbild laden
        ImageIcon backgroundImage = new ImageIcon("background.png");

        // Panel mit Hintergrundbild
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null); // Ohne Layout-Manager
        setContentPane(backgroundPanel); // Setzt den Hintergrund

        // Titel mit Outline
        titleLabelMain = new JLabel("") {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawTextWithOutline(g, "Stechenrechner", 50, 100);
            }
        };
        titleLabelMain.setBounds(50, 50, 700, 200); // Position und Größe des Titels

        // Füge den Titel dem Panel hinzu
        backgroundPanel.add(titleLabelMain);

        // Fenster sichtbar machen
        setVisible(true);
    }

    public void drawTextWithOutline(Graphics g, String text, int x, int y) {
        Graphics2D g2d = (Graphics2D) g;

        // Verwende einen pixeligen Font
        Font font = loadCustomFont("C:/Users/Alina Baum/Downloads/invasion2000//INVASION2000.TTF", Font.BOLD | Font.ITALIC, 65);
        g2d.setFont(font);

        // Zeichne den Text für den Umriss - mehrfach leicht versetzt
        g2d.setColor(new Color(0x2D2D4C));
        g2d.drawString(text, x - 2, y - 2); // Oben links
        g2d.drawString(text, x + 2, y - 2); // Oben rechts
        g2d.drawString(text, x - 2, y + 3); // Unten links
        g2d.drawString(text, x + 2, y + 3); // Unten rechts

        // Zeichne den Haupttext darüber
        g2d.setColor(new Color(223, 149, 70)); // Pink
        g2d.drawString(text, x, y);
    }

    private Font loadCustomFont(String fontPath, int style, float size) {
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(style, size);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            return customFont;
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return new Font("Serif", Font.PLAIN, 12);
        }
    }
    public void berechneFeierabendZeit() {
        int ankunftsStunden = Integer.parseInt(ankunftsStundenField.getText());
        int ankunftsMinuten = Integer.parseInt(ankunftsMinutenField.getText());
        double bleibZeit = Double.parseDouble(bleibZeitField.getText());
        double pausenZeit = Double.parseDouble(pausenZeitField.getText());

        double gesamtZeit = bleibZeit + pausenZeit;
        int gesamtStunden = (int) gesamtZeit;
        int gesamtMinuten = (int) ((gesamtZeit - gesamtStunden) * 60);

        int endMinuten = ankunftsMinuten + gesamtMinuten;
        int extraStunden = endMinuten / 60;
        endMinuten = endMinuten % 60;

        int endStunden = ankunftsStunden + gesamtStunden + extraStunden;

        ergebnisLabel.setText("Feierabend um " + endStunden + ":" + (endMinuten < 10 ? "0" + endMinuten : endMinuten) + " Uhr.");
    }

    public void ueberstunden() {
        double vorgegebeneArbeitszeit = 7.6 * 60;
        double gesamteArbeitszeit = Double.parseDouble(bleibZeitField.getText());
        int arbeitszeitInMinuten = (int) (gesamteArbeitszeit * 60);
        int ueberstundenInMinuten = (int) (arbeitszeitInMinuten - vorgegebeneArbeitszeit);

        if (ueberstundenInMinuten > 0) {
            ueberstundenLabel.setText("Ueberstunden: " + ueberstundenInMinuten + " Minuten");
        } else {
            ueberstundenLabel.setText("Du hast keine Ueberstunden");
        }
    }

}
