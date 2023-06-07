package it.unibo.view.map.internal;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicButtonUI;

public class ButtonUIFactoryImpl implements ButtonUIFactory {
    @Override
    public BasicButtonUI buttonUINoGrayOut() {
        return new BasicButtonUI() {
            @Override
            protected void paintIcon(Graphics g, JComponent c, Rectangle iconRect) {
                AbstractButton button = (AbstractButton) c;
                ButtonModel model = button.getModel();
                Icon icon = button.getIcon();

                if (icon != null) {
                    icon.paintIcon(c, g, iconRect.x, iconRect.y);
                }

                if (button.isContentAreaFilled() && model.isArmed()) {
                    g.setColor(UIManager.getColor("Button.focus"));
                    g.fillRect(iconRect.x, iconRect.y, iconRect.width, iconRect.height);
                }
            }
        };
    }
}
