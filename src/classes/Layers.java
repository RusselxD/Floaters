package classes;

import java.awt.Dimension;

import javax.swing.JLayeredPane;

public class Layers extends JLayeredPane {

    BackgroundPanel backgroundPanel;
    EmojiPanel emojiPanel;

    int width, height;

    Layers(int width, int height, int sleep) {

        setLayout(null);

        this.width = width;
        this.height = height;

        setPreferredSize(new Dimension(this.width, this.height));

        backgroundPanel = new BackgroundPanel(this.width, this.height);
        add(backgroundPanel, Integer.valueOf(0));

        emojiPanel = new EmojiPanel(this.width, this.height, sleep);
        add(emojiPanel, Integer.valueOf(1));

    }

    void resizeLayers(int newWidth, int newHeight) {

        width = newWidth;
        height = newHeight;

        setPreferredSize(new Dimension(newWidth, newHeight));

    }
}
