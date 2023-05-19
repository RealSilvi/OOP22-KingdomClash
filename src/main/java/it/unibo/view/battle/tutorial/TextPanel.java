package it.unibo.view.battle.tutorial;

import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.battle.panels.utilities.ImageIconsSupplier;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class TextPanel extends DrawPanel{

    private final static int VERTICAL_PADDING=10;
    private final static int HORIZONTAL_PADDING=30;

    private final JLabel title;
    private final JTextArea content;

    public TextPanel(final String title,final String content,final Dimension size) {
        super(ImageIconsSupplier.BACKGROUND_FILL_PATTERN,size);

        this.title = new JLabel(title);
        this.content = new JTextArea(content);

        Border padding = BorderFactory.createEmptyBorder(
                VERTICAL_PADDING,
                HORIZONTAL_PADDING,
                VERTICAL_PADDING,
                HORIZONTAL_PADDING);
        this.title.setBorder(padding);
        this.content.setBorder(padding);

        this.content.setLineWrap(true);
        this.content.setWrapStyleWord(true);
        this.content.setEditable(false);

        this.title.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        Font font;
        try {
            font= Font.createFont(Font.TRUETYPE_FONT,new File("src/main/resources/it/unibo/icons/battle/armalite.ttf"));
            GraphicsEnvironment g= GraphicsEnvironment.getLocalGraphicsEnvironment();
            g.registerFont(Font.createFont(Font.TRUETYPE_FONT,new File("src/main/resources/it/unibo/icons/battle/armalite.ttf")));
        }catch(IOException | FontFormatException e){
            font=Font.getFont("arial");
        }

        this.title.setFont(font.deriveFont(40f));
        this.content.setFont(this.content.getFont().deriveFont(20f));

        this.content.setOpaque(false);
        this.title.setOpaque(false);

        this.title.setForeground(ImageIconsSupplier.PRIMARY_COLOR);
        this.content.setForeground(ImageIconsSupplier.PRIMARY_COLOR);

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.add(this.title);
        this.add(this.content);
    }
}
