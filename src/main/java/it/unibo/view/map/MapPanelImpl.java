package it.unibo.view.map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.ButtonUI;

import it.unibo.controller.GameController;
import it.unibo.view.map.internal.ButtonUIFactory;
import it.unibo.view.map.internal.ButtonUIFactoryImpl;
import it.unibo.view.map.internal.GraphicUtils;
import it.unibo.view.map.mapdata.MapConfiguration;

/**
 * A simple panel made of tiles that can be of different types and
 * can have different behaviours.
 */
public final class MapPanelImpl extends JPanel implements MapPanel {

    private transient Logger logger = Logger.getLogger(this.getClass().getName());

    public static final int BATTLE_LEVELS = 55;
    public static final int RANDOM_SEED = 123545;
    public static final int ROWS = 10;
    public static final int COLS = 10;

    private transient Map<ButtonIdentification, Image> imageMap = new EnumMap<>(ButtonIdentification.class);
    private List<JButton> tiles = new ArrayList<>();
    private Random randomGen = new Random(RANDOM_SEED);
    private transient GameController controller;
    private transient MapConfiguration configuration = new MapConfiguration();

    private List<Integer> specialTileIndexes = new ArrayList<>();

    /**
     * Constructs a MapPanel, a GUI composed of different types of tiles
     * and assigns a controller to fire events to.
     * @param controller the controller that will be assigned to this GUI
     */
    public MapPanelImpl(GameController controller) {
        this();
        this.controller = controller;
    }

    private MapPanelImpl() {
        Arrays.stream(ButtonIdentification.values()).forEach(identification -> {
            try {
                imageMap.put(identification, ImageIO.read(this.getClass().getResource(configuration.getImageMap().get(identification))));
            } catch (IOException | IllegalArgumentException e) {
                logger.severe("Error loading resources for: "+identification.name());
            }
        });
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                MapPanelImpl.this.setPreferredSize(new Dimension(300, 100));
                updateButtonIcons();
            }
        });
        Dimension cellSize = calculateCellSize();
        //ButtonUI tileUI = new ButtonUIFactoryImpl().buttonUINoGrayOut();
        setLayout(new GridLayout(ROWS, COLS));
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                JButton button = new JButton();
                button.setBorderPainted(false);
                button.setPreferredSize(cellSize);
                button.setActionCommand(ButtonIdentification.TILE.getActionCommand());
                button.setIcon(new ImageIcon(imageMap.get(ButtonIdentification.TILE)));
                //button.setUI(tileUI);
                button.setBorderPainted(false);
                button.setContentAreaFilled(false);
                tiles.add(button);
                this.add(button);
            }
        }
        populateMap();
    }

    /**
     * Opens the panel inside a JFrame, for manual testing purposes only.
     */
    public void showInJFrame() {
        JFrame frame = new JFrame("MapPanelTestGUI");
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.add(this);
        frame.setVisible(true);
    }

    @Override
    public JPanel getAsJPanel() {
        return this;
    }

    @Override
    public void setBeatenLevels(final int beatenLevels) {
        for (int index = 1; index<beatenLevels+1; index++) {
            tiles.get(specialTileIndexes.get(index))
                .setActionCommand(ButtonIdentification.DEATH.getActionCommand());
        }
    }

    @Override
    public void setActiveBattle(final int battleIndex) {
        int selectedIndex = battleIndex>0 ? battleIndex : 0;
        IntStream.range(1, specialTileIndexes.size()).forEach(specialIndex -> 
            tiles.get(specialTileIndexes.get(specialIndex))
                .setEnabled(specialTileIndexes.get(specialIndex) == selectedIndex));
    }

    private Dimension calculateCellSize() {
        int width = getWidth();
        int height = getHeight();

        int cellWidth = width / COLS;
        int cellHeight = height / ROWS;

        return new Dimension(cellWidth, cellHeight);
    }

    private void updateButtonIcons() {
        JButton newBtnDim = tiles.get(0);
        Arrays.stream(ButtonIdentification.values()).forEach(identifier ->{
            ImageIcon temporaryIcon = new ImageIcon(
            GraphicUtils.resizeImage(
                imageMap.get(identifier), newBtnDim.getWidth(), newBtnDim.getHeight()));
            tiles.stream()
                .filter(tile -> tile.getActionCommand().equals(identifier.getActionCommand()))
                .forEach(tile -> tile.setIcon(temporaryIcon));
        });
    }

    private void populateMap() {
        for (int index = 0; index<=BATTLE_LEVELS; index++) {
            ImageIcon imageReference;
            ButtonIdentification command;
            int temporaryIndex = 11;
            if (index == 0) {
                imageReference = new ImageIcon(imageMap.get(ButtonIdentification.PLAYER));
                command = ButtonIdentification.PLAYER;
            } else {
                do {
                    temporaryIndex = randomGen.nextInt()%(ROWS*COLS);
                } while (specialTileIndexes.contains(temporaryIndex) || temporaryIndex < 0);
                imageReference = new ImageIcon(imageMap.get(ButtonIdentification.ENEMY));
                command = ButtonIdentification.ENEMY;
            }
            specialTileIndexes.add(temporaryIndex);
            tiles.get(temporaryIndex).setIcon(imageReference);
            tiles.get(temporaryIndex).setActionCommand(command.getActionCommand());
        }
    }
}
