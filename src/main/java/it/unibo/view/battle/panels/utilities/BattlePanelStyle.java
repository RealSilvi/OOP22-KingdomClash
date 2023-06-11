package it.unibo.view.battle.panels.utilities;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public interface BattlePanelStyle {

    String FONTS_DIRECTORY = "/it/unibo/fonts/battle/";

    String PRIMARY_FONT_NAME = "armalite";

    String FONT_EXTENSION = ".ttf";

    Color PRIMARY_COLOR = new Color(168, 19, 48);
    Color SECONDARY_COLOR = new Color(250, 160, 21);
    Color DEFAULT_COLOR = Color.darkGray;

    static Font loadTtfFont(final String pathToDirectory,final String fontName){
        final float fontSize = 40f;
        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(BattlePanelStyle.class.getResourceAsStream(pathToDirectory + fontName + FONT_EXTENSION)));
            GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
            g.registerFont(font);
            return font.deriveFont(fontSize);
        } catch (IOException | FontFormatException e) {
            font = Font.getFont(Font.SANS_SERIF);
            return font.deriveFont(fontSize);
        }
    }

    static Font getPrimaryFont() {
        return loadTtfFont(FONTS_DIRECTORY , PRIMARY_FONT_NAME);
    }
}
