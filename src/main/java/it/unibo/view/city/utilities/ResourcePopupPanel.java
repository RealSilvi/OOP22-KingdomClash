package it.unibo.view.city.utilities;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.Popup;
import javax.swing.PopupFactory;

public class ResourcePopupPanel {
    private Popup popup;
    private final int width;
    private final int height;
    private JPanel contentpanel;
    private boolean visibility;
    private Component container;
    private int xposition;
    private int yposition;

    public ResourcePopupPanel(final Component container, final int xposition, final int yposition, final JPanel panel) {
        this.width = 200;
        this.height = 200;
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
    public void changeVisibility(){
        this.visibility = !this.visibility;
        if(visibility) {
            popup.show();
        } else {
            popup.hide();
            this.popup = new PopupFactory().getPopup(container, contentpanel, xposition, yposition);
        }
    }
     /**
     * This method close the popus when is unused or when the game is close.
     */
    public void dispose(){
        popup.hide();
    }
}
