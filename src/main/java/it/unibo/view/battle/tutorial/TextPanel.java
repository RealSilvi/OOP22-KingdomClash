package it.unibo.view.battle.tutorial;

import it.unibo.view.battle.config.PathIconsConfiguration;
import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.battle.panels.utilities.BattlePanelStyle;
import it.unibo.view.utilities.ImageIconsSupplier;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TextPanel extends DrawPanel {

    //TODo set iud version

    static final long serialVersionUID = 42L;

    private static final int VERTICAL_PADDING = 10;
    private static final int HORIZONTAL_PADDING = 30;
    private static final float TITLE_FONT_SIZE = 30f;
    private static final float CONTENT_FONT_SIZE = 20f;

    public TextPanel(final String title, final String content, final Dimension size, final PathIconsConfiguration pathIconsConfiguration) {
        super(ImageIconsSupplier.loadImageIcon(pathIconsConfiguration.getBackgroundFillPattern()), size);

        final JLabel title1 = new JLabel(title);
        final JTextArea content1 = new JTextArea(content);

        final Border padding = BorderFactory.createEmptyBorder(
                VERTICAL_PADDING,
                HORIZONTAL_PADDING,
                VERTICAL_PADDING,
                HORIZONTAL_PADDING);
        title1.setBorder(padding);
        content1.setBorder(padding);

        content1.setLineWrap(true);
        content1.setWrapStyleWord(true);
        content1.setEditable(false);

        title1.setAlignmentX(Component.CENTER_ALIGNMENT);

        title1.setFont(BattlePanelStyle.getPrimaryFont().deriveFont(TITLE_FONT_SIZE));
        content1.setFont(content1.getFont().deriveFont(CONTENT_FONT_SIZE));

        title1.setOpaque(false);
        content1.setOpaque(false);

        title1.setForeground(BattlePanelStyle.PRIMARY_COLOR);
        content1.setForeground(BattlePanelStyle.PRIMARY_COLOR);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title1);
        this.add(content1);
    }
}