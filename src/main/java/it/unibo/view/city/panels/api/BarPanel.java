package it.unibo.view.city.panels.api;

import java.awt.*;
import java.awt.List;
import java.util.*;

import javax.swing.JTextArea;

import it.unibo.controller.base.BaseControllerImpl;


public interface BarPanel {


     JTextArea getInfo(Dimension size);

    void getPlayerInfo(BaseControllerImpl basedata);

}
