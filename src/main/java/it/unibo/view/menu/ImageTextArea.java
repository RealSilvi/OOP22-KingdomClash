package it.unibo.view.menu;

import javax.swing.*;
import java.awt.*;

public class ImageTextArea extends JTextArea {
    private Image backgroundImage;

    public ImageTextArea() {
        super();
    }

    public void setImage(final Image backgroundImage){
        this.backgroundImage = backgroundImage;
        setOpaque(false);
        repaint();
    }

    @Override
    public void paint(final Graphics graphics) {
        graphics.drawImage(backgroundImage, 0, 0, null);
        super.paint(graphics);
    }

}