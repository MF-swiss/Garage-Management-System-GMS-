package main.java;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableModel;
import javax.swing.RowFilter;

public class GUI {
    private JFrame mainFrame;
    private JFrame confirmFrame;
    private javax.swing.Timer animationTimer;
    private int index = 0;
    private JLabel helloLabel;
    private JLabel animationLabel;
    // Für Animation: ASCII-Sanduhr mit Strichen
    private String[] FRAMES = {"|", "/", "-", "\\"};
    private DefaultTableModel tableModel;
    private JTable table;
    private TableRowSorter<TableModel> sorter;
    private JScrollPane tableScroll;
    private JPanel searchPanel;
    private JLabel searchLabel;
    private JTextField searchField;
    private JPanel addPanel;
    private JTextField markeField;
    private JTextField typF;
    private JTextField kennzField;
    private JTextField verbrauchField;
    private JTextField reichweiteField;
    private JTextField tankField;
    private JTextField sitzeField;
    private JTextField speedField;
    private JButton addCarButton;
    private JButton printButton;
    private JButton exitButton;
    private JButton exit2Button;
    private java.util.List<Vehicle> vehicles;

    public GUI(java.util.List<Vehicle> vehicles) {
        this.vehicles = vehicles;
        createMainWindow();
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

        helloLabel = new JLabel(" Garage Management System (GMS) ", SwingConstants.CENTER);
        helloLabel.setOpaque(true);
        helloLabel.setBackground(Color.DARK_GRAY);
        helloLabel.setForeground(Color.WHITE);
        helloLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        helloLabel.setPreferredSize(new Dimension(600, 40));

        // Fahrzeug-Tabelle aus Vehicle-Liste
        String[] columnNames = {"Marke", "Typ", "Kennzeichen", "Verbrauch", "Reichweite", "Tank", "Sitze", "Geschwindigkeit"};
        Object[][] data = new Object[vehicles.size()][8];
        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle v = vehicles.get(i);
            data[i][0] = v.getMarke();
            data[i][1] = v.getTyp();
            data[i][2] = v.getLicensePlate();
            data[i][3] = v.getConsumption().orElse(null);
            data[i][4] = v.getRange().orElse(null);
            data[i][5] = v.getFuelTankCapacity().orElse(null);
            data[i][6] = v.getSeatCapacity().orElse(null);
            data[i][7] = v.getSpeed().orElse(null);
        }
        tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);
        tableScroll = new JScrollPane(table);
        tableScroll.setPreferredSize(new Dimension(700, 200));

        // Filter-Logik: Suche in allen Spalten
        searchPanel = new JPanel();
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
        searchLabel = new JLabel("Suche: ");
        searchField = new JTextField();
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

        // Formular für neues Fahrzeug
        addPanel = new JPanel();
        addPanel.setBackground(Color.LIGHT_GRAY);
        addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.X_AXIS));
        markeField = new JTextField(); markeField.setMaximumSize(new Dimension(80, 25));
        typF = new JTextField(); typF.setMaximumSize(new Dimension(80, 25));
        kennzField = new JTextField(); kennzField.setMaximumSize(new Dimension(80, 25));
        verbrauchField = new JTextField(); verbrauchField.setMaximumSize(new Dimension(60, 25));
        reichweiteField = new JTextField(); reichweiteField.setMaximumSize(new Dimension(60, 25));
        tankField = new JTextField(); tankField.setMaximumSize(new Dimension(60, 25));
        sitzeField = new JTextField(); sitzeField.setMaximumSize(new Dimension(40, 25));
        speedField = new JTextField(); speedField.setMaximumSize(new Dimension(60, 25));

        JLabel markeLabel = new JLabel("Marke*:");
        markeLabel.setForeground(Color.RED.darker());
        markeLabel.setToolTipText("Pflichtfeld");
        JLabel typLabel = new JLabel(" Typ*:");
        typLabel.setForeground(Color.RED.darker());
        typLabel.setToolTipText("Pflichtfeld");
        JLabel kennzLabel = new JLabel(" Kennz.*:");
        kennzLabel.setForeground(Color.RED.darker());
        kennzLabel.setToolTipText("Pflichtfeld");

        addPanel.add(markeLabel); addPanel.add(markeField);
        addPanel.add(typLabel); addPanel.add(typF);
        addPanel.add(kennzLabel); addPanel.add(kennzField);
        addPanel.add(new JLabel(" Verbrauch:")); addPanel.add(verbrauchField);
        addPanel.add(new JLabel(" Reichw.:")); addPanel.add(reichweiteField);
        addPanel.add(new JLabel(" Tank:")); addPanel.add(tankField);
        addPanel.add(new JLabel(" Sitze:")); addPanel.add(sitzeField);
        addPanel.add(new JLabel(" Speed:")); addPanel.add(speedField);
        addCarButton = new JButton("Fahrzeug hinzufügen");
        addCarButton.addActionListener(e -> {
            // Pflichtfelder prüfen
            if (markeField.getText().trim().isEmpty() || typF.getText().trim().isEmpty() || kennzField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(mainFrame, "Bitte alle Pflichtfelder (*) ausfüllen!", "Fehler", JOptionPane.ERROR_MESSAGE);
                return;
            }
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

            // Neues Vehicle-Objekt erzeugen und zur Liste hinzufügen (mit robustem Typ-Cast)
            VehicleBuilder builder = new VehicleBuilder(
                (String) row[0], (String) row[1], (String) row[2]
            );
            // Robust cast für alle Felder (Double/Integer)
            if (row[3] != null) {
                if (row[3] instanceof Double d) builder.setConsumption(d);
                else if (row[3] instanceof Integer i) builder.setConsumption(i.doubleValue());
            }
            if (row[4] != null) {
                if (row[4] instanceof Integer i) builder.setRange(i);
                else if (row[4] instanceof Double d) builder.setRange(d.intValue());
            }
            if (row[5] != null) {
                if (row[5] instanceof Integer i) builder.setFuelTankCapacity(i);
                else if (row[5] instanceof Double d) builder.setFuelTankCapacity(d.intValue());
            }
            if (row[6] != null) {
                if (row[6] instanceof Integer i) builder.setSeatCapacity(i);
                else if (row[6] instanceof Double d) builder.setSeatCapacity(d.intValue());
            }
            if (row[7] != null) {
                if (row[7] instanceof Integer i) builder.setSpeed(i);
                else if (row[7] instanceof Double d) builder.setSpeed(d.intValue());
            }
            vehicles.add(builder.build());

            markeField.setText(""); typF.setText(""); kennzField.setText(""); verbrauchField.setText("");
            reichweiteField.setText(""); tankField.setText(""); sitzeField.setText(""); speedField.setText("");
        });
        addPanel.add(addCarButton);

        printButton = new JButton("Drucken");
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

        exitButton = new JButton("EXIT");
        exitButton.setBackground(Color.RED);
        exitButton.setForeground(Color.WHITE);
        exitButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        exitButton.addActionListener(e -> confirmExit());

        panel.add(Box.createVerticalStrut(20));
        panel.add(helloLabel);
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
            confirmFrame = null;
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

        exit2Button = new JButton("EXIT2");
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
        animationTimer = new javax.swing.Timer(500, e -> {
            index = (index + 1) % FRAMES.length;
            if (animationLabel != null) {
                animationLabel.setText(FRAMES[index]);
            }
        });
        animationTimer.start();
    }

    // entfernt (Duplikat)

    private static Integer parseIntOrNull(String s) {
        try { return Integer.parseInt(s); } catch (Exception e) { return null; }
    }

    private static Double parseDoubleOrNull(String s) {
        try { return Double.parseDouble(s); } catch (Exception e) { return null; }
    }
}
