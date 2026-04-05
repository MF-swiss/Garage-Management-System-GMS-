import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class GUI {
    private static final String[] FRAMES = {"⌛", "⏳"};
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
        mainFrame.setSize(400, 350);
        mainFrame.getContentPane().setBackground(Color.GRAY);
        mainFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.GREEN);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


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

        // Eingabefeld für Typ
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(Color.GREEN);
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
        JLabel typeLabel = new JLabel("Typ: ");
        javax.swing.JTextField typeField = new javax.swing.JTextField();
        typeField.setMaximumSize(new Dimension(120, 25));
        JButton addButton = new JButton("Hinzufügen");
        addButton.addActionListener(e -> {
            String typ = typeField.getText();
            if (!typ.isEmpty()) {
                typeDisplayLabel.setText("Typ: " + typ);
                typeField.setText("");
            }
        });
        inputPanel.add(typeLabel);
        inputPanel.add(typeField);
        inputPanel.add(Box.createHorizontalStrut(10));
        inputPanel.add(addButton);

        JButton infoButton = new JButton("Was passiert hier?");
        infoButton.setBackground(Color.BLUE);
        infoButton.setForeground(Color.WHITE);
        infoButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        infoButton.addActionListener(e -> action2());

        JButton exitButton = new JButton("EXIT");
        exitButton.setBackground(Color.RED);
        exitButton.setForeground(Color.WHITE);
        exitButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        exitButton.addActionListener(e -> action1());

        panel.add(Box.createVerticalStrut(30));
        panel.add(helloLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(typeDisplayLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(inputPanel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(infoButton);
        panel.add(Box.createVerticalStrut(40));
        panel.add(exitButton);

        mainFrame.add(panel, BorderLayout.CENTER);
        mainFrame.setVisible(true);
    }

    private void action1() {
        System.out.println("Button wurde geklickt!");
        confirmExit();
    }

    private void action2() {
        System.out.println("Was passiert hier?");
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
        if (confirmFrame != null && confirmFrame.isDisplayable()) {
            confirmFrame.toFront();
            return;
        }

        confirmFrame = new JFrame("False!");
        confirmFrame.setSize(400, 300);
        confirmFrame.getContentPane().setBackground(Color.RED);
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
            animationLabel.setText(FRAMES[index]);
        });
        animationTimer.start();
    }
}
