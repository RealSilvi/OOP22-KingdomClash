package it.unibo.view.battle.panels.utilities;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public interface BattlePanelStyle {

    String fontsDirectory = File.separator + "it"
            + File.separator + "unibo"
            + File.separator + "fonts"
            + File.separator + "battle"
            + File.separator;

    String primaryFontName = "armalite";

    String fontExtension = ".ttf";

    Color PRIMARY_COLOR = new Color(168, 19, 48);
    Color SECONDARY_COLOR = new Color(250, 160, 21);
    Color DEFAULT_COLOR = Color.darkGray;

    static Font getPrimaryFont() {
        float fontSize = 40f;
        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(BattlePanelStyle.class.getResourceAsStream(fontsDirectory + primaryFontName + fontExtension)));
            GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
            g.registerFont(font);
            return font.deriveFont(fontSize);
        } catch (IOException | FontFormatException e) {
            font = Font.getFont("Arial");
            return font.deriveFont(fontSize);
        }
    }
}
