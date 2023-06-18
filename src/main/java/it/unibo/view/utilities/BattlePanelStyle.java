package it.unibo.view.utilities;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.FontFormatException;
import java.io.IOException;
import java.util.Objects;

/**
 * This interface is used to collect Style configurations in static field and methods.
 */
public interface BattlePanelStyle {

    /**
     * The path where are saved the default fonts.
     */
    String FONTS_DIRECTORY = "/it/unibo/fonts/battle/";

    /**
     * The name of the primary font.
     */
    String PRIMARY_FONT_NAME = "armalite";

    /**
     * The font extension.
     */
    String FONT_EXTENSION = ".ttf";

    /**
     * The primary color of the application.
     */
    Color PRIMARY_COLOR = new Color(168, 19, 48);

    /**
     * The secondary color of the application.
     */
    Color SECONDARY_COLOR = new Color(250, 160, 21);

    /**
     * The default color of the application.
     */
    Color DEFAULT_COLOR = Color.darkGray;

    /**
     * Load the ttf fonts.
     * @param pathToDirectory the path to the directory.
     * @param fontName the name of the font file without extension.
     * @return the font required, in case of failure return sans serif.
     */
    static Font loadTtfFont(final String pathToDirectory, final String fontName) {
        final float fontSize = 40f;
        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(BattlePanelStyle.class.getResourceAsStream(pathToDirectory + fontName + FONT_EXTENSION)));
            final GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
            g.registerFont(font);
            return font.deriveFont(fontSize);
        } catch (IOException | FontFormatException e) {
            font = Font.getFont(Font.SANS_SERIF);
            return font.deriveFont(fontSize);
        }
    }

    /**
     * Gives you the primary font of the application.
     * @return the primary font of the application.
     */
    static Font getPrimaryFont() {
        return loadTtfFont(FONTS_DIRECTORY, PRIMARY_FONT_NAME);
    }
}
