package it.unibo.view.battle.panels.utilities;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public interface BattlePanelStyle {

    String fontsDirectory = System.getProperty("user.dir")
            + File.separator + "src"
            + File.separator + "main"
            + File.separator + "resources"
            + File.separator + "it"
            + File.separator + "unibo"
            + File.separator + "fonts"
            + File.separator + "battle"
            + File.separator;

    Color PRIMARY_COLOR = new Color(168, 19, 48);
    Color SECONDARY_COLOR = new Color(250, 160, 21);
    Color DEFAULT_COLOR = Color.darkGray;

    static Font getPrimaryFont() {
        float fontSize=40f;
        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File( fontsDirectory + "armalite.ttf"));
            GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
            g.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fontsDirectory +"armalite.ttf")));
            return font.deriveFont(fontSize);
        } catch (IOException | FontFormatException e) {
            font = Font.getFont("Arial");
            return font.deriveFont(fontSize);
        }
    }
}
