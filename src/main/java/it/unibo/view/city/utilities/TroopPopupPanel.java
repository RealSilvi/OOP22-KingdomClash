
package it.unibo.view.city.utilities;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import it.unibo.controller.base.BaseController;
import it.unibo.kingdomclash.config.PathIconsConfiguration;
import it.unibo.view.utilities.ImageIconsSupplier;
/**
 * This class create the popup that shows the troop and their levels and contains
 *  a button that can improve the level of the troops.
 * 
 */
public class TroopPopupPanel {

    private static final int HEIGHT = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.5);
    private static final int WIDTH = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.3);

    private Popup popup;
    private JPanel contentpanel;
    private boolean visibility;
    private Component container;
    private int xposition;
    private int yposition;
    private JFrame movepopup;
    private PathIconsConfiguration image;
    
    /**
     * This costructor create the popup and gave him the name and the level of the troops and an upgrade button for each other.
     * @param container the content of the Popup
     * @param xposition the coordinate x where the popup is placed
     * @param yposition the coordinate y where the popup is placed
     * @param data use for get the information of the troop and the upgrade button
     */
    public TroopPopupPanel(final Component container, final int xposition, final int yposition, final BaseController data) {
        this.visibility = false;
        this.container = container;
        this.xposition = xposition;
        this.yposition = yposition;
        this.contentpanel = new JPanel();
        this.contentpanel.add(new JLabel("ResourcePopupPanel"));
        this.contentpanel.setLayout(new BoxLayout(contentpanel, BoxLayout.Y_AXIS));
        this.contentpanel.setPreferredSize(new Dimension(HEIGHT, WIDTH));
        this.popup = new PopupFactory().getPopup(container, contentpanel, xposition, yposition);
        this.movepopup = new JFrame("troop Info");
        this.movepopup.getContentPane().add(contentpanel);
        //this.movepopup.setSize(xposition, yposition);
        popup.show();

       data.requestTroopLevels().keySet().stream().forEach(
            singletroop -> {
                JPanel containpanel = new JPanel();
                containpanel.setLayout(new BorderLayout());

                JLabel label = new JLabel(
                "" + singletroop.name() +" " + data.requestTroopLevels().get(singletroop));
                containpanel.add(label, BorderLayout.LINE_START);
                containpanel.add(new JLabel(ImageIconsSupplier.loadImageIcon(image.getTroop(singletroop))), BorderLayout.CENTER);
                var buttonOK = new JButton("upgrade");
                containpanel.add(buttonOK, BorderLayout.LINE_END);
                buttonOK.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent arg0) {
                       data.upgradeTroop(singletroop,
                        data.requestTroopLevels().get(singletroop) + 1);
                    }
                });
                contentpanel.add(containpanel);
            });
     }
    /**
     * This method allows to make the popup visible on each click
     * with a boolean parameter.
     */
    public void changeVisibility() {
        this.visibility = !this.visibility;
        if (visibility) {
            //popup.show();
            movepopup.setVisible(visibility);
        } else {
            //popup.hide();
            //this.popup = new PopupFactory().getPopup(container, contentpanel, xposition, yposition);
            movepopup.setVisible(visibility);
        }
    }
    /**
     * This method close the popus when is unused or when the game is close.
     */
    public void dispose() {
        //popup.hide();
        this.movepopup.setVisible(false);
    }

    private class TroopSelected {
        
    }
}
