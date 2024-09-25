import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Zeitrechner extends JFrame {
    private JTextField ankunftsStundenField, ankunftsMinutenField, bleibZeitField, pausenZeitField;
    private JLabel ergebnisLabel, ueberstundenLabel;
    public Zeitrechner() {
        setTitle("Stechenrechner");
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
        add(ueberstundenLabel);
        berechnenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                berechneFeierabendZeit();
                ueberstunden();
            }
        });
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
        if(ueberstundenInMinuten > 0) {
            ueberstundenLabel.setText("Ueberstunden: " + ueberstundenInMinuten + " Minuten");
        }else{
            ueberstundenLabel.setText("Du hast keine Ueberstunden");
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Zeitrechner().setVisible(true);
            }
        });
    }
}