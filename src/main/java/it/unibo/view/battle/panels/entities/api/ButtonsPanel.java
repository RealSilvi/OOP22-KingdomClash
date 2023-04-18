package it.unibo.view.battle.panels.entities.api;

import javax.swing.*;
import java.awt.event.ActionListener;

public interface ButtonsPanel {
    void disablePassButton();

    void enablePassButton();

    void disableSpinButton();

    void enableSpinButton();

    void setActionListenerPass(ActionListener actionListener);

    void setActionListenerSpin(ActionListener actionListener);

    JPanel getPanel();
}
