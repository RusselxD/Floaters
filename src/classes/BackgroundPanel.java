package classes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel {

    int width;
    int height;

    Image backgroundImage;

    BackgroundPanel(int width, int height) {

        this.width = width;
        this.height = height;

        setBounds(0, 0, width, height);
        backgroundImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/background.jpg"))).getImage();
    }

    public void paint(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(backgroundImage, 0, 0, width, height, null);
    }

    protected void resizeBackground(int newWidth, int newHeight) {

        setBounds(0, 0, newWidth, newHeight);

        width = newWidth;
        height = newHeight;

        repaint();
    }

}
