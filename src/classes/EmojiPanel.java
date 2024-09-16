package classes;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class EmojiPanel extends JPanel implements MouseListener{

    int width, height;

    int sleep;

    ArrayList<Emoji> floatingEmojis;

    EmojiPanel(int width, int height, int sleep) {

        this.width = width;
        this.height = height;

        this.sleep = sleep;

        floatingEmojis = new ArrayList<>();

        addMouseListener(this);
        setLayout(null);
        setBounds(0, 0, width, height);
        setOpaque(false);

    }

    void addEmoji(int xPos, int yPos) {

        Emoji newEmoji = new Emoji(width, height, this, xPos, yPos);
        floatingEmojis.add(newEmoji);

        add(newEmoji);

        Thread emojiThread = new Thread(() -> {
            while (!Thread.interrupted()) {
                newEmoji.move();

                try {
                    Thread.sleep(sleep);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        emojiThread.start();
    }

    void resizePanel(int newWidth, int newHeight) {

        setBounds(0, 0, newWidth, newHeight);
        width = newWidth;
        height = newHeight;

        for (Emoji emoji : floatingEmojis) {
            emoji.resizeScope(newWidth, newHeight);
        }
    }

    void removeEmoji(){

        if(floatingEmojis.isEmpty()) return;

        Emoji emoji = floatingEmojis.get(0);
        floatingEmojis.remove(emoji);
        emoji.remove();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        addEmoji(x, y);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
