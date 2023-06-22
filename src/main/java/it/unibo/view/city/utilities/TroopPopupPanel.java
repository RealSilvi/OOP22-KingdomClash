package it.unibo.view.city.utilities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
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
    private JFrame movepopup;
    private PathIconsConfiguration image;
    private int level = 1;
    /**
     * This costructor create the popup and gave him the name and the level of the troops and an upgrade button for each other.
     * @param container the content of the Popup
     * @param data use for get the information of the troop and the upgrade button
     * @param pathIconsConfiguration get the texture for the popup
     */
    public TroopPopupPanel(final Component container, final BaseController data,
        final PathIconsConfiguration pathIconsConfiguration) {
        this.visibility = false;
        //this.basedata = data;
        this.contentpanel = new JPanel();
        this.contentpanel.setLayout(new BoxLayout(contentpanel, BoxLayout.Y_AXIS));
        this.contentpanel.setPreferredSize(new Dimension(HEIGHT, WIDTH));
        this.popup = new PopupFactory().getPopup(container, contentpanel, WIDTH, HEIGHT);
        this.movepopup = new JFrame("troop Info");
        this.movepopup.getContentPane().add(contentpanel);
        this.movepopup.setSize(WIDTH, HEIGHT);
        popup.show();
        this.image = pathIconsConfiguration;
        movepopup.setBackground(Color.BLACK);
        
       data.requestTroopLevels().keySet().stream().forEach(
            singletroop -> {
                JPanel containpanel = new JPanel();
                containpanel.setLayout(new BorderLayout());
                containpanel.setBackground(movepopup.getBackground());

                Font font = new Font("font", Font.ITALIC, movepopup.getPreferredSize().width / 15);
                JLabel label = new JLabel(ImageIconsSupplier.getScaledImageIcon(image.getTroop(singletroop),
                 new Dimension(movepopup.getPreferredSize().width / 10, movepopup.getPreferredSize().height / 10)));
                 var buttonOK = new JButton("upgrade");
                 JLabel levels = new JLabel("" + level);
                 levels.setFont(font);
                 levels.setPreferredSize(new Dimension(movepopup.getPreferredSize().width
                  - (label.getPreferredSize().width + buttonOK.getPreferredSize().width),
                  buttonOK.getPreferredSize().height));
                levels.setBackground(new Color(0, 0, 0, 0));
                levels.setHorizontalAlignment(JLabel.CENTER);
                containpanel.add(label, BorderLayout.LINE_START);
                containpanel.add(levels);
                containpanel.add(buttonOK, BorderLayout.LINE_END);
                buttonOK.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent arg0) {
                       data.upgradeTroop(singletroop,
                        data.requestTroopLevels().get(singletroop) + 1);
                        level = data.requestTroopLevels().get(singletroop);
                        levels.setText("" + level);
                        data.upgradeTroop(singletroop);
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
            movepopup.setVisible(visibility);
        } else {
            movepopup.setVisible(visibility);
        }
    }
    /**
     * This method close the popus when is unused or when the game is close.
     */
    public void dispose() {
        this.movepopup.dispose();
    }
}
