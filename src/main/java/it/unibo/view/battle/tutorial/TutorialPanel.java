package it.unibo.view.battle.tutorial;

import it.unibo.view.battle.config.JsonKeys;
import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.battle.panels.utilities.ImageIconsSupplier;
import it.unibo.view.battle.panels.utilities.PanelDimensions;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public final class TutorialPanel{

    private final static int BORDER_LAYOUT_GAP = 3;
    private final static Dimension EXIT_DIMENSION= new Dimension(60,30);

    private final JPanel mainPanel;

    private final JPanel backPanel;
    private final JButton turnBack;
    private final TextPanelFactoryImpl panelFactory;


    //TODO interfacce e commenti
    //TODO tutti i final dei parametri
    //TODO tutti i punti alla fine dei comenti
    //TODO tutti i commenti dei costruttori
    //TODO tutti i commenti delle interfacce da rivedere
    //TODO aggiungi roba in json e fai i metodi per averli
    public TutorialPanel() {
        this.turnBack=new JButton(ImageIconsSupplier.getImageExitIndicator(EXIT_DIMENSION));
        this.panelFactory=new TextPanelFactoryImpl();
        this.backPanel=panelFactory.getTutorialSouthPanelDefault();

        this.turnBack.setPreferredSize(EXIT_DIMENSION);
        this.turnBack.setOpaque(false);
        this.turnBack.setBorder(BorderFactory.createLineBorder(ImageIconsSupplier.PRIMARY_COLOR,2,true));

        this.backPanel.add(this.turnBack);

        this.mainPanel= new DrawPanel(Color.darkGray, PanelDimensions.SCREEN_SIZE);
        this.mainPanel.setLayout(new BorderLayout(BORDER_LAYOUT_GAP,BORDER_LAYOUT_GAP));

        this.mainPanel.add(this.backPanel,BorderLayout.SOUTH);
        this.mainPanel.add(this.panelFactory.getTutorialNorthPanelDefault(),BorderLayout.NORTH);
        this.mainPanel.add(this.panelFactory.getTutorialWestPanelDefault(),BorderLayout.WEST);
        this.mainPanel.add(this.panelFactory.getTutorialEastPanelDefault(),BorderLayout.EAST);
        this.mainPanel.add(this.panelFactory.getTutorialCenterPanelDefault(),BorderLayout.CENTER);
    }

    public void addActionListenerExit(ActionListener actionListener){
        this.turnBack.addActionListener(actionListener);
    }

    public JPanel getPanel() {
        return this.mainPanel;
    }

}
