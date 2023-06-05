package it.unibo.view.battle.tutorial;

import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.battle.panels.utilities.ImageIconsSupplier;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TextPanel extends DrawPanel {

    private final static int VERTICAL_PADDING = 10;
    private final static int HORIZONTAL_PADDING = 30;
    private final static float TITLE_FONT_SIZE = 30f;
    private final static float CONTENT_FONT_SIZE = 20f;

    public TextPanel(final String title, final String content, final Dimension size) {
        super(ImageIconsSupplier.BACKGROUND_FILL_PATTERN, size);

        JLabel title1 = new JLabel(title);
        JTextArea content1 = new JTextArea(content);

        Border padding = BorderFactory.createEmptyBorder(
                VERTICAL_PADDING,
                HORIZONTAL_PADDING,
                VERTICAL_PADDING,
                HORIZONTAL_PADDING);
        title1.setBorder(padding);
        content1.setBorder(padding);

        content1.setLineWrap(true);
        content1.setWrapStyleWord(true);
        content1.setEditable(false);

        title1.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        title1.setFont(ImageIconsSupplier.getPrimaryFont().deriveFont(TITLE_FONT_SIZE));
        content1.setFont(content1.getFont().deriveFont(CONTENT_FONT_SIZE));

        title1.setOpaque(false);
        content1.setOpaque(false);

        title1.setForeground(ImageIconsSupplier.PRIMARY_COLOR);
        content1.setForeground(ImageIconsSupplier.PRIMARY_COLOR);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title1);
        this.add(content1);
    }
}