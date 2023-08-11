package it.unibo.view.battle.panels.impl;

import it.unibo.kingdomclash.config.PathIconsConfiguration;
import it.unibo.view.battle.panels.entities.DrawPanelImpl;
import it.unibo.view.utilities.BattlePanelStyle;
import it.unibo.view.utilities.ImageIconsSupplier;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.border.Border;
import java.awt.Dimension;
import java.awt.Component;

/**
 * This class is not designed to handle serialization.
 */
@SuppressWarnings("serial")
public final class TextPanelImpl extends DrawPanelImpl {

    private static final int VERTICAL_PADDING = 10;
    private static final int HORIZONTAL_PADDING = 30;
    private static final float TITLE_FONT_SIZE = 30f;
    private static final float CONTENT_FONT_SIZE = 20f;

    private final JLabel titleLabel;
    private final JTextArea contentText;

    /**
     * Construct a DrawPanelImpl with a title section and a text section.
     *
     * @param title                  the title to set in the panel.
     * @param content                the content to set in the panel.
     * @param size                   the size of the panel.
     * @param pathIconsConfiguration where are defined the paths of the textures.
     */
    public TextPanelImpl(
            final String title,
            final String content,
            final Dimension size,
            final PathIconsConfiguration pathIconsConfiguration) {
        this(size, pathIconsConfiguration);
        this.setTitle(title);
        this.setContent(content);
    }

    /**
     * Constructs a DrawPanelImpl with an empty title section and an empty text section.
     *
     * @param size                   the size of the panel.
     * @param pathIconsConfiguration where are defined the paths of the textures..
     */
    public TextPanelImpl(final Dimension size, final PathIconsConfiguration pathIconsConfiguration) {
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

    /**
     * Set the title on the panel.
     *
     * @param title the title to set in the panel.
     */
    public void setTitle(final String title) {
        this.titleLabel.removeAll();
        this.titleLabel.setText(title);
    }

    /**
     * Set thr content on the panel.
     *
     * @param content the content to set in the panel.
     */
    public void setContent(final String content) {
        this.contentText.removeAll();
        this.contentText.setText(content);
    }
}
