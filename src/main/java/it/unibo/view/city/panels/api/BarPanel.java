package it.unibo.view.city.panels.api;

import java.awt.*;
import java.awt.List;
import java.util.*;

import javax.swing.JTextArea;

import it.unibo.controller.base.BaseControllerImpl;
import it.unibo.model.data.GameData;
import it.unibo.model.data.Resource;
import it.unibo.model.data.TroopType;

public interface BarPanel {

    void switchbutton();

    public JTextArea getInfo(Dimension size);

    void getPlayerInfo(BaseControllerImpl basedata);

}
