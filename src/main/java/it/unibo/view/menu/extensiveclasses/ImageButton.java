package it.unibo.view.menu.extensiveclasses;

import javax.swing.JButton;
import javax.swing.Icon;
import java.awt.Dimension;
import javax.swing.SwingConstants;

/**
 * This class is used to extend the JButton class.
 * It helps in creating in a faster way a button with specific values.
 */
@SuppressWarnings("serial")
public class ImageButton extends JButton {

    /**
     * The constructor sets some values for the button.
     * @param text The text to set.
     * @param backgroundImage The image to set.
     * @param size The size of the button to set.
     */
    public ImageButton(final String text, final Icon backgroundImage, final Dimension size) {
        super(text);
        setIcon(backgroundImage);
        setPreferredSize(size);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setFocusable(false);
        setOpaque(false);
    }

}
