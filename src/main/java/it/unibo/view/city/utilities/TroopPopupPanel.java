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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Popup;
import javax.swing.PopupFactory;

import it.unibo.controller.base.BaseController;
import it.unibo.kingdomclash.config.PathIconsConfiguration;
import it.unibo.view.menu.GameMenuImpl;
import it.unibo.view.menu.extensiveclasses.ImageButton;
import it.unibo.view.utilities.BattlePanelStyle;
import it.unibo.view.utilities.ImageIconsSupplier;

/**
 * This class create the popup that shows the troop and their levels and contains
 * a button that can improve the level of the troops.
 */
public class TroopPopupPanel {
    private static final int HEIGHT = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.5);
    private static final int WIDTH = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.3);
    private static final Dimension DIMENSION_BUTTON = new Dimension(WIDTH / 4, HEIGHT / 8);
    private static final ImageIcon BACKGROUND_BUTTON = ImageIconsSupplier.getScaledImageIcon(GameMenuImpl.PATH_BUTTON,
            DIMENSION_BUTTON);
    private static final float FONT_SIZE = (float) 30;
    private Popup popup;
    private final JPanel contentpanel;
    private boolean visibility;
    private final Component container;
    private final PathIconsConfiguration image;
    private int level;
    private final int x;
    private final int y;

    /**
     * This costructor create the popup and gave him the name and the level of the troops and an upgrade button for each other.
     *
     * @param container              the content of the Popup
     * @param data                   use for get the information of the troop and the upgrade button
     * @param pathIconsConfiguration get the texture for the popup
     * @param xPos                   get the x position on the screen
     * @param yPos                   get the y position on the screen
     */
    public TroopPopupPanel(final Component container, final BaseController data,
                           final PathIconsConfiguration pathIconsConfiguration, final int xPos, final int yPos) {
        this.visibility = false;
        this.container = container;
        this.contentpanel = new JPanel();
        this.contentpanel.setLayout(new BoxLayout(contentpanel, BoxLayout.Y_AXIS));
        this.contentpanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.contentpanel.setBackground(Color.BLACK);
        this.popup = new PopupFactory().getPopup(container, contentpanel, xPos, yPos / 2);
        this.image = pathIconsConfiguration;
        this.x = xPos;
        this.y = yPos / 2;
        data.requestTroopLevels().keySet().stream().forEach(
                singletroop -> {
                    final JPanel containpanel = new JPanel();
                    containpanel.setLayout(new BorderLayout());
                    containpanel.setBackground(this.contentpanel.getBackground());
                    final Font font = BattlePanelStyle.getPrimaryFont().deriveFont(FONT_SIZE);
                    final JLabel label = new JLabel(ImageIconsSupplier.getScaledImageIcon(image.getTroop(singletroop),
                            new Dimension(WIDTH / 8, HEIGHT / 8)));
                    final JButton buttonOK = new ImageButton("upgrade", BACKGROUND_BUTTON, DIMENSION_BUTTON);
                    buttonOK.setForeground(Color.white);
                    final JLabel levels = new JLabel("Level " + data.requestTroopLevels().get(singletroop));
                    levels.setForeground(Color.WHITE);
                    levels.setFont(font);
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
                            levels.setText("Level" + level);
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
            popup.show();
        } else {
            popup.hide();
            this.popup = new PopupFactory().getPopup(container, contentpanel, this.x, this.y);
        }
    }

    /**
     * This method close the popus when is unused or when the game is close.
     */
    public void dispose() {
        popup.hide();
    }
}
