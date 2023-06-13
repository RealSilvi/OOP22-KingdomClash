package it.unibo.view.menu.extensiveclasses;

import javax.swing.*;
import java.awt.*;

public class ImageButton extends JButton {

    public ImageButton(final String text, final Icon backgroundImage, Dimension size) {
        super(text);
        setIcon(backgroundImage);
        setPreferredSize(size);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setFocusable(false);
        setOpaque(false);
    }

}
