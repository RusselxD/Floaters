package classes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Emoji extends JPanel implements MouseListener {

    EmojiPanel parentPanel;

    int frameWidth, frameHeight;

    int xPos, yPos;
    int xVelocity, yVelocity;
    int emojiDimension;

    BufferedImage origSizedImage;
    Image emoji;

    Emoji(int frameWidth, int frameHeight, EmojiPanel parentPanel, int xPos, int yPos) {

        this.parentPanel = parentPanel;

        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;

        this.xPos = xPos + emojiDimension / 2;
        this.yPos = yPos + emojiDimension / 2;

        this.setLayout(null);
        this.setBounds(xPos, yPos, emojiDimension, emojiDimension);

        try {
            origSizedImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/smiling.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        determineEmojiSize(this.frameWidth, this.frameHeight);
        determineMovement();

        addMouseListener(this);

    }

    public void paint(Graphics g) {
        Graphics2D graphics2d = (Graphics2D) g;
        graphics2d.drawImage(emoji, 0, 0, null);
    }

    private void determineMovement() {
        xVelocity = (((int) (Math.random() * 2)) == 0) ? 1 : -1;
        yVelocity = (((int) (Math.random() * 2)) == 0) ? 1 : -1;
    }

    void resizeScope(int newFrameWidth, int newFrameHeight) {
        frameWidth = newFrameWidth;
        frameHeight = newFrameHeight;
        determineEmojiSize(newFrameWidth, newFrameHeight);
    }

    private void determineEmojiSize(int frameWidth, int frameHeight) {
        // // this calculation will make the emoji's size proportional to the frame size
        emojiDimension = (int) ((frameWidth + frameHeight) * 0.075);

        BufferedImage resizedImage = new BufferedImage(emojiDimension, emojiDimension, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(origSizedImage, 0, 0, emojiDimension, emojiDimension, null);
        g2d.dispose();

        emoji = resizedImage;
    }

    public void move() {

        xPos += xVelocity;
        yPos += yVelocity;

        if (xPos >= frameWidth - getWidth() - 8 || xPos < -5) {
            xVelocity *= -1;
        }
        if (yPos >= frameHeight - getHeight() - 30 || yPos < -5) {
            yVelocity *= -1;
        }

        setBounds(xPos, yPos, emojiDimension, emojiDimension);

    }

    void remove(){
        parentPanel.remove(this);
        parentPanel.revalidate();
        parentPanel.repaint();
        parentPanel.floatingEmojis.remove(this);

        Thread.currentThread().interrupt(); // Interrupt the thread associated with the emoji
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        remove();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

        try {
            origSizedImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/poker.png")));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        determineEmojiSize(frameWidth, frameHeight);
    }

    @Override
    public void mouseExited(MouseEvent e) {

        try {
            origSizedImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/smiling.png")));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


        determineEmojiSize(frameWidth, frameHeight);
    }

}
