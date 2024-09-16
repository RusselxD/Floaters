package classes;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Action;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class WelcomeFrame extends JFrame {

    Image backgroundImage;
    JComboBox<String> speed;

    BackgroundPanel backgroundPanel;

    WelcomeFrame() {

        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/smiling.png"))).getImage());

        backgroundImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/blurBackground.jpg"))).getImage();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setResizable(false);
        setLayout(new BorderLayout());

        backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(null);

        addLabels(backgroundPanel);
        addEnterKey();

        add(backgroundPanel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class BackgroundPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
        }
    }

    void addLabels(JPanel panel) {
        JLabel label1 = new JLabel("Set their speed:");
        label1.setFont(new Font("Courier", Font.BOLD, 20));
        label1.setBounds(105, 50, 150, 25);

        String[] choices = {"Slow", "Average", "Fast"};
        speed = new JComboBox<>(choices);
        speed.setFont(new Font("Courier", Font.PLAIN, 15));
        speed.setBounds(360, 50, 100, 25);
        speed.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        panel.add(label1);
        panel.add(speed);

        JLabel label2 = new JLabel("Enter / Tap anywhere:");
        label2.setFont(new Font("Courier", Font.PLAIN, 15));
        label2.setBounds(105, 130, 500, 17);

        JLabel label21 = new JLabel("Add another smiley.");
        label21.setFont(new Font("Courier", Font.PLAIN, 15));
        label21.setBounds(305, 130, 500, 17);

        JLabel label3 = new JLabel("Space:");
        label3.setFont(new Font("Courier", Font.PLAIN, 15));
        label3.setBounds(105, 170, 500, 17);

        JLabel label31 = new JLabel("Erase a random smiley");
        label31.setFont(new Font("Courier", Font.PLAIN, 15));
        label31.setBounds(305, 170, 500, 17);

        JLabel label4 = new JLabel("You can erase a specific smiley by clicking on them.");
        label4.setFont(new Font("Courier", Font.PLAIN, 15));
        label4.setForeground(Color.darkGray);
        label4.setBounds(115, 250, 500, 17);

        JLabel label5 = new JLabel("Press ENTER to start.");
        label5.setFont(new Font("Courier", Font.BOLD, 15));
        label5.setBounds(200, 300, 500, 17);

        panel.add(label2);
        panel.add(label21);
        panel.add(label3);
        panel.add(label31);
        panel.add(label4);
        panel.add(label5);
    }

    void addEnterKey(){

        Action start = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String fastNess = (String) speed.getSelectedItem();
                assert fastNess != null;
                int sleep = switch (fastNess){
                    case "Slow" -> 20;
                    case "Average" -> 15;
                    case "Fast" -> 5;
                    default -> 0;
                };

                Main.startProgram(sleep);
            }
        };

        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), "Enter");
        this.getRootPane().getActionMap().put("Enter", start);

    }
}
