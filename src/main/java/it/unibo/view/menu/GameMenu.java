package it.unibo.view.menu;

import javax.swing.*;
import java.awt.event.ActionListener;

public interface GameMenu {

    JPanel getPanel();

    void setButtonsVisibilityMenu(GameMenuImpl.BUTTONS_MENU name, Boolean visibility);

    void setActionListenerInfo(ActionListener actionListener);

    void setActionListenerNewGame(ActionListener actionListener);

    void setActionListenerLoad(ActionListener actionListener);

    void setActionListenerMusic(ActionListener actionListener);

    void setActionListenerExit(ActionListener actionListener);

}
