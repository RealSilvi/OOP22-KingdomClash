package it.unibo.view.menu;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
    public static final Dimension DIMENSION_SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
    private final Image backgroundImage;

    public ImagePanel(final Image backgroundImage) {
        this.backgroundImage = backgroundImage.getScaledInstance((int)DIMENSION_SCREEN.getWidth(), (int)DIMENSION_SCREEN.getHeight(), Image.SCALE_DEFAULT);
    }

    @Override
    public void paintComponent(final Graphics graphics){
        super.paintComponent(graphics);
        graphics.drawImage(backgroundImage, 0, 0, this);
    }

}
