import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.awt.FontFormatException;
import java.util.Random;


public class Zeitrechner extends JFrame {
    private JTextField ankunftsStundenField, ankunftsMinutenField, bleibZeitField, pausenZeitField;
    private JLabel ergebnisLabel, ueberstundenLabel;
    JFrame window;
    Container con;
    JPanel panel;
    JButton zeitStechenButton;


    TitleScreenHandler tsHandler = new TitleScreenHandler();


    public static void main(String[] args) {

        new Zeitrechner();
        new Musik();

    }

    //--------------------------------------------------------------------------------------------------------------
    public Zeitrechner() {

        //Creating vom Main Window
        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        window.getContentPane().setBackground(Color.WHITE);
        con = window.getContentPane();

        //Creating vom Main Panel
        panel = new JPanel(null);
        panel.setBounds(0, 0, 800, 600);
        panel.setBackground(Color.WHITE);


        //Main title Label für Überschrift
        JLabel titleLabelMain = new JLabel("Stechenrechner");
        titleLabelMain.setForeground(new Color(255, 0, 119)); // Main text color (pink)
        titleLabelMain.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabelMain.setFont(loadCustomFont("/C:/Users/Alina Baum/Downloads/invasion2000//INVASION2000.TTF", Font.BOLD | Font.ITALIC, 65));
        titleLabelMain.setBounds(70, 130, 700, 100); // Main title position and size


        // Outline Label für den Umriss (leicht versetzt und größer)
        JLabel titleLabelOutline = new JLabel("Stechenrechner");
        titleLabelOutline.setForeground(Color.BLACK); // Outline color
        titleLabelOutline.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabelOutline.setFont(loadCustomFont("/C:/Users/Alina Baum/Downloads/invasion2000//INVASION2000.TTF", Font.BOLD | Font.ITALIC, 70)); // Slightly larger font for outline
        titleLabelOutline.setBounds(68, 128, 704, 104); // Slightly shifted position for outline

        panel.add(titleLabelMain);
        panel.add(titleLabelOutline);

        JPanel zeitStechenPanel = new JPanel();
        zeitStechenPanel.setBounds(100, 50, 200, 100);
        zeitStechenPanel.setBackground(Color.CYAN);

        zeitStechenButton = new JButton("Zeit des Stechens");
        zeitStechenButton.setBackground(Color.BLACK);
        zeitStechenButton.setForeground(Color.cyan);
        zeitStechenButton.setFont(loadCustomFont("/C:/Users/Alina Baum/IdeaProjects/StechenRechner/dpcomic.ttf", Font.BOLD | Font.ITALIC, 25));
        zeitStechenButton.addActionListener(tsHandler);

        zeitStechenPanel.add(zeitStechenButton);

        con.add(panel);

        window.setVisible(true);


        zeitStechenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                berechneFeierabendZeit();
                ueberstunden();
            }
        });
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
        public void erstelleRechnerWindow () {



        }

        public class TitleScreenHandler implements ActionListener {
            public void actionPerformed(ActionEvent e) {


            }
        }


    }
