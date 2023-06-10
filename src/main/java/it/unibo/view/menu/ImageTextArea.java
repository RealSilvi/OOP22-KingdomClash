package it.unibo.view.menu;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import javax.swing.*;
import java.awt.*;

public class ImageTextArea extends JTextArea {
    private Image backgroundImage;

    public ImageTextArea() {
        super();
    }

    @SuppressFBWarnings(value = "EI2",
            justification = "I want to use the background in input to represent the image in the textArea")
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
