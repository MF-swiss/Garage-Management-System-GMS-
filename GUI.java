import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;
import javax.swing.table.*;

public class GUI {
        // Hilfsmethoden für Zahl-Parsing
        private static Integer parseIntOrNull(String s) {
            try { return Integer.parseInt(s); } catch (Exception e) { return null; }
        }
        private static Double parseDoubleOrNull(String s) {
            try { return Double.parseDouble(s); } catch (Exception e) { return null; }
        }
    private static final String[] FRAMES = {"|", "/", "-", "\\"};
    private int index = 0;

    private JFrame mainFrame;
    private JFrame confirmFrame;
    private JLabel animationLabel;
    private JLabel helloLabel;
    private JLabel typeDisplayLabel;
    private Timer animationTimer;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUI().createMainWindow());
    }

    private void createMainWindow() {
    mainFrame = new JFrame("Garage Management System (GMS)");
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setSize(800, 600);
    mainFrame.getContentPane().setBackground(Color.GRAY);
    mainFrame.setLocationRelativeTo(null);

    JPanel panel = new JPanel();
    panel.setBackground(Color.WHITE);
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    // Fahrzeug-Tabelle (Dummy-Daten)
    String[] columnNames = {"Marke", "Typ", "Kennzeichen", "Verbrauch", "Reichweite", "Tank", "Sitze", "Geschwindigkeit"};
    Object[][] data = {
        {"Cupra", "Ateca", "SG 1", 8.5, 540, null, null, 120},
        {"MINI", "Cooper", "SG 4", null, 580, null, 5, 220},
        {"Ford", "Tourneo Custom", "SG 3", 8.0, 1000, 75, 8, null}
    };
    DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    final JTable table = new JTable(tableModel);
    TableRowSorter<TableModel> sorter = new TableRowSorter<>(tableModel);
    table.setRowSorter(sorter);
    JScrollPane tableScroll = new JScrollPane(table);
    tableScroll.setPreferredSize(new Dimension(700, 120));

    // Filter-Logik: Suche in allen Spalten
    JPanel searchPanel = new JPanel();
    searchPanel.setBackground(Color.WHITE);
    searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
    JLabel searchLabel = new JLabel("Suche: ");
    JTextField searchField = new JTextField();
    searchField.setMaximumSize(new Dimension(200, 25));
    searchPanel.add(searchLabel);
    searchPanel.add(searchField);
    searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
        public void insertUpdate(javax.swing.event.DocumentEvent e) { filter(); }
        public void removeUpdate(javax.swing.event.DocumentEvent e) { filter(); }
        public void changedUpdate(javax.swing.event.DocumentEvent e) { filter(); }
        private void filter() {
            String text = searchField.getText();
            if (text.trim().length() == 0) {
                sorter.setRowFilter(null);
            } else {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + java.util.regex.Pattern.quote(text)));
            }
        }
    });

    helloLabel = new JLabel("Garage Management System (GMS)", SwingConstants.CENTER);
    helloLabel.setOpaque(true);
    helloLabel.setBackground(Color.DARK_GRAY);
    helloLabel.setForeground(Color.WHITE);
    helloLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    helloLabel.setMaximumSize(new Dimension(280, 35));

    // Label für Typ-Anzeige
    typeDisplayLabel = new JLabel("Typ: -");
    typeDisplayLabel.setOpaque(true);
    typeDisplayLabel.setBackground(Color.LIGHT_GRAY);
    typeDisplayLabel.setForeground(Color.BLACK);
    typeDisplayLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    typeDisplayLabel.setMaximumSize(new Dimension(280, 25));

    // Eingabefeld für Typ (optional, kann entfernt werden)
    JPanel inputPanel = new JPanel();
    inputPanel.setBackground(Color.LIGHT_GRAY);
    inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
    JLabel typeLabel = new JLabel("Typ: ");
    JTextField typeField = new JTextField();
    typeField.setMaximumSize(new Dimension(120, 25));
    JButton addTypeButton = new JButton("Typ anzeigen");
    addTypeButton.addActionListener(e -> {
        String typ = typeField.getText();
        if (!typ.isEmpty()) {
            typeDisplayLabel.setText("Typ: " + typ);
            typeField.setText("");
        }
    });
    inputPanel.add(typeLabel);
    inputPanel.add(typeField);
    inputPanel.add(Box.createHorizontalStrut(10));
    inputPanel.add(addTypeButton);

    // Formular für neues Fahrzeug
    JPanel addPanel = new JPanel();
    addPanel.setBackground(Color.LIGHT_GRAY);
    addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.X_AXIS));
    JTextField markeField = new JTextField(); markeField.setMaximumSize(new Dimension(80, 25));
    JTextField typF = new JTextField(); typF.setMaximumSize(new Dimension(80, 25));
    JTextField kennzField = new JTextField(); kennzField.setMaximumSize(new Dimension(80, 25));
    JTextField verbrauchField = new JTextField(); verbrauchField.setMaximumSize(new Dimension(60, 25));
    JTextField reichweiteField = new JTextField(); reichweiteField.setMaximumSize(new Dimension(60, 25));
    JTextField tankField = new JTextField(); tankField.setMaximumSize(new Dimension(60, 25));
    JTextField sitzeField = new JTextField(); sitzeField.setMaximumSize(new Dimension(40, 25));
    JTextField speedField = new JTextField(); speedField.setMaximumSize(new Dimension(60, 25));
    addPanel.add(new JLabel("Marke:")); addPanel.add(markeField);
    addPanel.add(new JLabel(" Typ:")); addPanel.add(typF);
    addPanel.add(new JLabel(" Kennz.:")); addPanel.add(kennzField);
    addPanel.add(new JLabel(" Verbrauch:")); addPanel.add(verbrauchField);
    addPanel.add(new JLabel(" Reichw.:")); addPanel.add(reichweiteField);
    addPanel.add(new JLabel(" Tank:")); addPanel.add(tankField);
    addPanel.add(new JLabel(" Sitze:")); addPanel.add(sitzeField);
    addPanel.add(new JLabel(" Speed:")); addPanel.add(speedField);
    JButton addCarButton = new JButton("Fahrzeug hinzufügen");
    addCarButton.addActionListener(e -> {
        Object[] row = new Object[8];
        row[0] = markeField.getText();
        row[1] = typF.getText();
        row[2] = kennzField.getText();
        row[3] = verbrauchField.getText().isEmpty() ? null : parseDoubleOrNull(verbrauchField.getText());
        row[4] = reichweiteField.getText().isEmpty() ? null : parseIntOrNull(reichweiteField.getText());
        row[5] = tankField.getText().isEmpty() ? null : parseIntOrNull(tankField.getText());
        row[6] = sitzeField.getText().isEmpty() ? null : parseIntOrNull(sitzeField.getText());
        row[7] = speedField.getText().isEmpty() ? null : parseIntOrNull(speedField.getText());
        tableModel.addRow(row);
        markeField.setText(""); typF.setText(""); kennzField.setText(""); verbrauchField.setText("");
        reichweiteField.setText(""); tankField.setText(""); sitzeField.setText(""); speedField.setText("");
    });
    addPanel.add(addCarButton);

    JButton printButton = new JButton("Drucken");
    printButton.setBackground(Color.BLUE);
    printButton.setForeground(Color.WHITE);
    printButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
    printButton.addActionListener(e -> {
        try {
            table.print();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(mainFrame, "Fehler beim Drucken: " + ex.getMessage());
        }
    });

    JButton exitButton = new JButton("EXIT");
    exitButton.setBackground(Color.RED);
    exitButton.setForeground(Color.WHITE);
    exitButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
    exitButton.addActionListener(e -> confirmExit());

    panel.add(Box.createVerticalStrut(20));
    panel.add(helloLabel);
    panel.add(Box.createVerticalStrut(10));
    panel.add(typeDisplayLabel);
    panel.add(Box.createVerticalStrut(10));
    panel.add(inputPanel);
    panel.add(Box.createVerticalStrut(20));
    panel.add(searchPanel);
    panel.add(Box.createVerticalStrut(10));
    panel.add(tableScroll);
    panel.add(Box.createVerticalStrut(20));
    panel.add(addPanel);
    panel.add(Box.createVerticalStrut(20));
    panel.add(printButton);
    panel.add(Box.createVerticalStrut(40));
    panel.add(exitButton);

    mainFrame.add(panel, BorderLayout.CENTER);
    mainFrame.setVisible(true);
    }


    private void action3() {
        System.out.println("EXIT2 wurde geklickt!");

        if (animationTimer != null) {
            animationTimer.stop();
        }

        if (confirmFrame != null) {
            confirmFrame.dispose();
        }
        if (mainFrame != null) {
            mainFrame.dispose();
        }
    }

    private void confirmExit() {
        // Stoppe ggf. laufende Animation, damit sie beim erneuten Öffnen nicht doppelt läuft
        if (animationTimer != null) {
            animationTimer.stop();
        }
        if (confirmFrame != null && confirmFrame.isDisplayable()) {
            confirmFrame.toFront();
            return;
        }

        confirmFrame = new JFrame("False!");
        confirmFrame.setSize(400, 300);
            // nicht mehr benötigt
        confirmFrame.setLocationRelativeTo(mainFrame);

        JPanel panel = new JPanel();
        panel.setBackground(Color.RED);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel textLabel = new JLabel(
            "Bist du sicher, dass du dieses Fenster schliessen möchtest?",
            SwingConstants.CENTER
        );
        textLabel.setForeground(Color.WHITE);
        textLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        animationLabel = new JLabel(FRAMES[0], SwingConstants.CENTER);
        animationLabel.setForeground(Color.WHITE);
        animationLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        animationLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        JButton exit2Button = new JButton("EXIT2");
        exit2Button.setAlignmentX(JButton.CENTER_ALIGNMENT);
        exit2Button.addActionListener(e -> action3());

        panel.add(Box.createVerticalStrut(30));
        panel.add(textLabel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(animationLabel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(exit2Button);

        confirmFrame.add(panel);
        confirmFrame.setVisible(true);

        startAnimation();
    }

    private void startAnimation() {
        animationTimer = new Timer(500, e -> {
            index = (index + 1) % FRAMES.length;
            if (animationLabel != null) {
                animationLabel.setText(FRAMES[index]);
            }
        });
        animationTimer.start();
    }

    // entfernt (Duplikat)
}
