package it.unibo.view.battle.panels.impl;

import it.unibo.kingdomclash.config.PathIconsConfiguration;
import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.utilities.BattlePanelStyle;
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

    private final JLabel titleLabel;
    private final JTextArea contentText;

    public TextPanel(final String title, final String content, final Dimension size, final PathIconsConfiguration pathIconsConfiguration) {
        this(size,pathIconsConfiguration);
        this.setTitle(title);
        this.setContent(content);
    }

    public TextPanel(Dimension size,final PathIconsConfiguration pathIconsConfiguration) {
        super(ImageIconsSupplier.loadImageIcon(pathIconsConfiguration.getBackgroundFillPattern()), size);
        this.titleLabel = new JLabel();
        this.contentText = new JTextArea();

        final Border padding = BorderFactory.createEmptyBorder(
                VERTICAL_PADDING,
                HORIZONTAL_PADDING,
                VERTICAL_PADDING,
                HORIZONTAL_PADDING);
        titleLabel.setBorder(padding);
        contentText.setBorder(padding);

        contentText.setLineWrap(true);
        contentText.setWrapStyleWord(true);
        contentText.setEditable(false);

        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        titleLabel.setFont(BattlePanelStyle.getPrimaryFont().deriveFont(TITLE_FONT_SIZE));
        contentText.setFont(contentText.getFont().deriveFont(CONTENT_FONT_SIZE));

        titleLabel.setOpaque(false);
        contentText.setOpaque(false);

        titleLabel.setForeground(BattlePanelStyle.PRIMARY_COLOR);
        contentText.setForeground(BattlePanelStyle.PRIMARY_COLOR);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(titleLabel);
        this.add(contentText);
    }

    public void setTitle(final String title) {
        this.titleLabel.removeAll();
        this.titleLabel.setText(title);
    }

    public void setContent(final String content) {
        this.contentText.removeAll();
        this.contentText.setText(content);
    }
}