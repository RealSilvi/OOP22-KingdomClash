
package it.unibo.view.city.utilities;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.Popup;
import javax.swing.PopupFactory;
/**
 * This class 
 */
public class ResourcePopupPanel {
    private static final int HEIGHT = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.5);
    private static final int WIDTH = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.3);

    private Popup popup;
    private final int width;
    private final int height;
    private JPanel contentpanel;
    private boolean visibility;
    private Component container;
    private int xposition;
    private int yposition;
    /**
     * 
     * @param container in which panel this popup is applicate
     * @param xposition the x coordinate position where the popup is saw
     * @param yposition the x coordinate position where the popup is saw
     * @param panel the content of the popup
     */
    public ResourcePopupPanel(final Component container, final int xposition, final int yposition, final JPanel panel) {
        this.width = WIDTH;
        this.height = HEIGHT;
        this.visibility = false;
        this.container = container;
        this.xposition = xposition;
        this.yposition = yposition;
        this.contentpanel = panel;
        this.contentpanel.setPreferredSize(new Dimension(this.width, this.height));
        this.popup = new PopupFactory().getPopup(container, contentpanel, xposition, yposition);
    }
    /**
     * This method allows to make the popup visible on each click
     * with a boolean parameter.
     */
    public void changeVisibility() {
        this.visibility = !this.visibility;
        if (visibility) {
            popup.show();
        } else {
            popup.hide();
            this.popup = new PopupFactory().getPopup(container, contentpanel, xposition, yposition);
        }
    }
     /**
     * This method close the popus when is unused or when the game is close.
     */
    public void dispose() {
        popup.hide();
    }
}
