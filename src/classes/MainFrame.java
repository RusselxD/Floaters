package classes;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Objects;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.Action;
import javax.swing.AbstractAction;

public class MainFrame extends JFrame implements ComponentListener {

    Layers layers;

    int width;
    int height;

    MainFrame(int sleep) {

        width = 600;
        height = 400;

        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/smiling.png"))).getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLayout(new BorderLayout());

        layers = new Layers(width, height, sleep);

        add(layers, BorderLayout.CENTER);

        setUpKeyBindings();

        addComponentListener(this);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    void setUpKeyBindings() {

        KeyStroke spaceKey = KeyStroke.getKeyStroke("SPACE");
        KeyStroke enterKey = KeyStroke.getKeyStroke("ENTER");

        Action removeEmojiAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layers.emojiPanel.removeEmoji();
            }
        };

        Action addEmojiAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layers.emojiPanel.addEmoji(getWidth() / 2, getHeight() / 2);
            }
        };

        getRootPane().getInputMap().put(spaceKey, "spaceKey");
        getRootPane().getActionMap().put("spaceKey", removeEmojiAction);

        getRootPane().getInputMap().put(enterKey, "enterKey");
        getRootPane().getActionMap().put("enterKey", addEmojiAction);
    }

    @Override
    public void componentResized(ComponentEvent e) {

        int width = getWidth();
        int height = getHeight();

        layers.resizeLayers(width, height);
        layers.backgroundPanel.resizeBackground(width, height);
        layers.emojiPanel.resizePanel(width, height);
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }
}
