package it.unibo.view.city.utilities;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Popup;
import javax.swing.PopupFactory;

public class TroopPopupPanel {
    private Popup popup;
    private final int width;
    private final int height;
    private JPanel contentpanel;
    private boolean visibility;

    public TroopPopupPanel(Component container, int xposition,int yposition) {
        this.width = 200;
        this.height = 200;
        this.visibility = false;
        this.contentpanel = new JPanel();
        this.contentpanel.add(new JLabel("ResourcePopupPanel"));
        this.contentpanel.setPreferredSize(new Dimension(this.width, this.height));
        this.popup = new PopupFactory().getPopup(container, contentpanel, xposition, yposition);
    }

    public void changeVisibility(){
        this.visibility = !this.visibility;
        if(visibility) {
            popup.show();
        } else {
            popup.hide();
        }
    }
}
