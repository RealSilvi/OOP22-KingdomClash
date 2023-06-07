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
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import it.unibo.controller.GameController;
import it.unibo.view.map.MapPanel.ButtonIdentification;
import it.unibo.view.map.internal.GraphicUtils;

public final class MapPanelImpl extends JPanel implements MapPanel {

    private transient Logger logger = Logger.getLogger(this.getClass().getName());

    public static final int BATTLE_LEVELS = 3;
    public static final int RANDOM_SEED = 123545;
    public static final int ROWS = 10;
    public static final int COLS = 10;

    private transient Map<ButtonIdentification, Image> imageMap = new EnumMap<>(ButtonIdentification.class);
    private List<JButton> tiles = new ArrayList<>();
    private Random randomGen = new Random(RANDOM_SEED);
    private transient GameController controller;

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
        JPanel internalMapPanel = new JPanel();
        try {
            imageMap.put(ButtonIdentification.TILE, ImageIO.read(this.getClass().getResource("/it/unibo/textures/map/tile_isometric.png")));
            imageMap.put(ButtonIdentification.PLAYER, ImageIO.read(this.getClass().getResource("/it/unibo/textures/map/base_player.png")));
            imageMap.put(ButtonIdentification.ENEMY, ImageIO.read(this.getClass().getResource("/it/unibo/textures/map/base_enemy.png")));
            imageMap.put(ButtonIdentification.DEATH, ImageIO.read(this.getClass().getResource("/it/unibo/textures/map/base_enemy_defeated.png")));
        } catch (IOException | IllegalArgumentException e) {
            logger.warning("Error loading resources!");
            e.printStackTrace();
            internalMapPanel.setBackground(Color.RED);
        }
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                MapPanelImpl.this.setPreferredSize(new Dimension(300, 100));
                updateButtonIcons();
            }
        });
        Dimension cellSize = calculateCellSize();
        setLayout(new GridLayout(ROWS, COLS));
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                JButton button = new JButton();
                button.setBorderPainted(false);
                button.setPreferredSize(cellSize);
                button.setActionCommand(ButtonIdentification.TILE.getActionCommand());
                button.setIcon(new ImageIcon(imageMap.get(ButtonIdentification.TILE)));
                button.setBorderPainted(false);
                tiles.add(button);
                this.add(button);
            }
        }
        populateMap();
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
