package it.unibo.view.map.internal;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 * Implementation of ButtonUIFactory.
 */
public final class ButtonUIFactoryImpl implements ButtonUIFactory {
    @Override
    public BasicButtonUI buttonUINoGrayOut() {
        return new BasicButtonUI() {
            @Override
            protected void paintIcon(Graphics graphics,
                JComponent componentToPaint, Rectangle iconRectangle) {
                AbstractButton button = (AbstractButton) componentToPaint;
                ButtonModel model = button.getModel();
                Icon icon = button.getIcon();

                if (icon != null) {
                    icon.paintIcon(componentToPaint,
                        graphics, iconRectangle.x, iconRectangle.y);
                }

                if (button.isContentAreaFilled() && model.isArmed()) {
                    graphics.setColor(UIManager.getColor("Button.focus"));
                    graphics.fillRect(iconRectangle.x, iconRectangle.y,
                        iconRectangle.width, iconRectangle.height);
                }
            }
        };
    }
}
