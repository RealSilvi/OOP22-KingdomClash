package it.unibo.view.map;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
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
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import it.unibo.model.data.GameConfiguration;
import it.unibo.view.map.internal.GraphicUtils;
import it.unibo.view.map.mapdata.MapConfiguration;

/**
 * A simple panel made of tiles that can be of different types and
 * can have different behaviours.
 */
public final class MapPanelImpl extends JPanel implements MapPanel {

    private transient Logger logger = Logger.getLogger(this.getClass().getName());

    public static final int BATTLE_LEVELS = 3;
    public static final int RANDOM_SEED = 65455;

    private transient Map<ButtonIdentification, Image> imageMap = 
        new EnumMap<>(ButtonIdentification.class);
    
    private List<JButton> tiles = new ArrayList<>();
    private Random randomGen = new Random(RANDOM_SEED);
    @SuppressWarnings("unused")
    //Used to get global configuration
    private transient GameConfiguration configuration;
    private transient MapConfiguration mapConfiguration;

    private List<Integer> specialTileIndexes = new ArrayList<>();

    /**
     * Constructs a MapPanel, a GUI composed of different types of tiles
     * and assigns a controller to fire events to.
     * @param controller the controller that will be assigned to this GUI
     */
    public MapPanelImpl(GameConfiguration configuration) {
        this.configuration = configuration;
        this.mapConfiguration = configuration.getMapConfiguration();
        initialize();
    }

    private void initialize() {
        loadAssets();
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                updateButtonIcons();
            }
        });
        generateTileGrid();
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
        IntConsumer setBeatenAction = tileIndex -> {
            JButton tile = tiles.get(specialTileIndexes.get(tileIndex));
            if (tileIndex > beatenLevels) {
                tile.setDisabledIcon(null);
                tile.setEnabled(true);
                tile.setActionCommand(ButtonIdentification.ENEMY.getActionCommand());
            } else {
                tile.setEnabled(false);
                tile.setActionCommand(ButtonIdentification.DEATH.getActionCommand());
            }
        };

        getNonbeatenLevelsStream(true).forEach(setBeatenAction);

        getNonbeatenLevelsStream(false).forEach(setBeatenAction);

        updateButtonIcons();
    }

    @Override
    public void setActiveBattle(final int battleIndex) {
        IntConsumer setActiveAction = tileIndex -> {
            JButton tile = tiles.get(specialTileIndexes.get(tileIndex));
            if (tileIndex == battleIndex) {
                tile.setEnabled(true);
                tile.setActionCommand(ButtonIdentification.ENEMY.getActionCommand());
            } else {
                tile.setEnabled(false);
            }
        };
        //Creates a stream of non-beaten levels
        getNonbeatenLevelsStream(false).forEach(setActiveAction);

        updateButtonIcons();
    }

    @Override
    public void setBattleActionListener(final ActionListener battleActionListener) {
        tiles.get(specialTileIndexes.get(0)).addActionListener(battleActionListener);
    }
    @Override
    public void clearBattleActionListener(final ActionListener battleActionListenerToRemove) {
        tiles.get(specialTileIndexes.get(0)).removeActionListener(battleActionListenerToRemove);
    }
    @Override
    public void setBaseActionListener(final ActionListener baseActionListener) {
        getSpecialTileStream().skip(1).forEach(enemyTileIndex -> 
            tiles.get(enemyTileIndex).addActionListener(baseActionListener));
    }
    @Override
    public void clearBaseActionListener(final ActionListener baseActionListenerToRemove) {
        getSpecialTileStream().skip(1).forEach(enemyTileIndex -> 
            tiles.get(enemyTileIndex).removeActionListener(baseActionListenerToRemove));
    }

    private Dimension calculateCellSize() {
        int width = getWidth();
        int height = getHeight();

        int cellWidth = width / mapConfiguration.getColumns();
        int cellHeight = height / mapConfiguration.getRows();

        return new Dimension(cellWidth, cellHeight);
    }
    /**
     * Updates the icons of the buttons.
     */
    private void updateButtonIcons() {
        JButton newBtnDim = tiles.get(0);
        Arrays.stream(ButtonIdentification.values()).forEach(identifier ->{
            ImageIcon temporaryIcon = new ImageIcon(
            GraphicUtils.resizeImage(
                imageMap.get(identifier), newBtnDim.getWidth(), newBtnDim.getHeight()));

            tiles.stream()
                .filter(tile -> tile.getActionCommand().equals(identifier.getActionCommand()))
                .forEach(tile -> {
                    tile.setIcon(temporaryIcon);
                    if (tile.getActionCommand()
                        .equals(ButtonIdentification.TILE.getActionCommand())
                        || tile.getActionCommand()
                            .equals(ButtonIdentification.DEATH.getActionCommand())) {

                        tile.setDisabledIcon(temporaryIcon);
                    }
                });
        });
    }
    /**
     * Populates the map with the player's base and the enemies.
     */
    private void populateMap() {
        for (int index = 0; index<=BATTLE_LEVELS; index++) {
            ImageIcon imageReference;
            ButtonIdentification command;
            Cursor tempCursor = new Cursor(Cursor.HAND_CURSOR);
            int temporaryIndex = 11;
            if (index == 0) {
                imageReference = new ImageIcon(imageMap.get(ButtonIdentification.PLAYER));
                command = ButtonIdentification.PLAYER;
                tiles.get(temporaryIndex).setEnabled(true);
            } else {
                do {
                    temporaryIndex = randomGen.nextInt()%(mapConfiguration.getRows()*mapConfiguration.getColumns());
                } while (specialTileIndexes.contains(temporaryIndex) || temporaryIndex < 0);
                imageReference = new ImageIcon(imageMap.get(ButtonIdentification.ENEMY));
                command = ButtonIdentification.ENEMY;
            }
            specialTileIndexes.add(temporaryIndex);
            tiles.get(temporaryIndex).setIcon(imageReference);
            tiles.get(temporaryIndex).setActionCommand(command.getActionCommand());
            tiles.get(temporaryIndex).setDisabledIcon(null);
            tiles.get(temporaryIndex).setCursor(tempCursor);
        }
    }
    /**
     * Loads game assets.
     */
    private void loadAssets() {
        Arrays.stream(ButtonIdentification.values()).forEach(identification -> {
            try {
                imageMap.put(identification, ImageIO.read(this.getClass().getResource(mapConfiguration.getImageMap().get(identification))));
                bakeTiles();
            } catch (IOException | IllegalArgumentException e) {
                logger.severe("Error loading resources!");
            }
        });
    }

    private void bakeTiles() {
        imageMap.keySet().stream().skip(1).forEach(elementTile ->
            imageMap.put(elementTile,
                GraphicUtils.overlayImages(
                    imageMap.get(ButtonIdentification.TILE), imageMap.get(elementTile))));
    }

    /**
     * Generates the tile map.
     */
    private void generateTileGrid() {
        Dimension cellSize = calculateCellSize();
        this.setLayout(new GridLayout(mapConfiguration.getRows(), mapConfiguration.getColumns()));
        for (int rowIndex = 0; rowIndex < mapConfiguration.getRows(); rowIndex++) {
            for (int columnIndex = 0;
                columnIndex < mapConfiguration.getColumns(); columnIndex++) {
                JButton button = new JButton();
                button.setBorderPainted(false);
                button.setPreferredSize(cellSize);
                button.setActionCommand(ButtonIdentification.TILE.getActionCommand());
                button.setIcon(new ImageIcon(imageMap.get(ButtonIdentification.TILE)));
                button.setDisabledIcon(new ImageIcon(imageMap.get(ButtonIdentification.TILE)));
                button.setBorderPainted(false);
                button.setContentAreaFilled(false);
                button.setEnabled(false);
                tiles.add(button);
                this.add(button);
            }
        }
    }

    private IntStream getSpecialTileStream() {
        return IntStream.range(0, specialTileIndexes.size());
    }

    /**
     * @param invertBehaviour   true to invert the function's behaviour
     * @return                  a stream of non-beaten levels
     *                          if the parameter is true
     */
    private IntStream getNonbeatenLevelsStream(boolean invertBehaviour) {
        IntPredicate beatenLevelCondition = tileIndex -> 
                tiles.get(specialTileIndexes.get(tileIndex))
                    .getActionCommand()
                    .equals(ButtonIdentification.ENEMY.getActionCommand());

            if (invertBehaviour) {
                beatenLevelCondition = beatenLevelCondition.negate();
            }

        return getSpecialTileStream().skip(1).filter(beatenLevelCondition);
    }
}
