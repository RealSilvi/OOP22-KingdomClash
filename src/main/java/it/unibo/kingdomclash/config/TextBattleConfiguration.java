package it.unibo.kingdomclash.config;

/**
 * Configuration of text areas in the battle.
 */
public class TextBattleConfiguration {

    private final String tutorialNorthTitle;
    private final String tutorialNorthText;
    private final String tutorialSouthTitle;
    private final String tutorialSouthText;
    private final String tutorialEastTitle;
    private final String tutorialEastText;
    private final String tutorialWestTitle;
    private final String tutorialWestText;
    private final String tutorialCenterTitle;
    private final String tutorialCenterText;
    private final String endWinPanelTitle;
    private final String endWinPanelText;
    private final String endLosePanelTitle;
    private final String endLosePanelText;

    /**
     * Set's the default configuration.
     */
    public TextBattleConfiguration() {
        this.tutorialNorthTitle = " ";
        this.tutorialNorthText = "This panel contains the opponent's set of dice.";
        this.tutorialSouthTitle = " ";
        this.tutorialSouthText = " In this panel, you'll find your set of data,"
                + " where you can select troops to deploy on the battlefield. "
                + "The chosen troops are automatically arranged on the battlefield.";
        this.tutorialEastTitle = " ";
        this.tutorialEastText = "This panel provides information about the "
                + "troops currently on the battlefield. ";
        this.tutorialWestTitle = " ";
        this.tutorialWestText = "Here, the combat effectiveness of each troop "
                + "is compared to the level of the opponent's troops. "
                + "If two troops of the same level clash, they cancel each other out. "
                + "However, if one troop has a higher level than the other, "
                + "it prevails over the other. ";
        this.tutorialCenterTitle = " ";
        this.tutorialCenterText = "Within this panel, you'll find the battlefield"
                + ",where deployed troops are automatically arranged. "
                + "The outcome of the combat between one troop and another "
                + "is displayed in the information panel. ";
        this.endWinPanelTitle = "YOU WIN";
        this.endWinPanelText = " ";
        this.endLosePanelTitle = "YOU LOSE ";
        this.endLosePanelText = "";
    }

    /**
     * @return the title of the north Panel of Tutorial.
     */
    public String getTutorialNorthTitle() {
        return tutorialNorthTitle;
    }

    /**
     * @return the text of the north Panel of Tutorial.
     */
    public String getTutorialNorthText() {
        return tutorialNorthText;
    }

    /**
     * @return the title of the south Panel of Tutorial.
     */
    public String getTutorialSouthTitle() {
        return tutorialSouthTitle;
    }

    /**
     * @return the text of the south Panel of Tutorial.
     */
    public String getTutorialSouthText() {
        return tutorialSouthText;
    }

    /**
     * @return the title of the east Panel of Tutorial.
     */
    public String getTutorialEastTitle() {
        return tutorialEastTitle;
    }

    /**
     * @return the text of the east Panel of Tutorial.
     */
    public String getTutorialEastText() {
        return tutorialEastText;
    }

    /**
     * @return the title of the west Panel of Tutorial.
     */
    public String getTutorialWestTitle() {
        return tutorialWestTitle;
    }

    /**
     * @return the text of the west Panel of Tutorial.
     */
    public String getTutorialWestText() {
        return tutorialWestText;
    }

    /**
     * @return the title of the center Panel of Tutorial.
     */
    public String getTutorialCenterTitle() {
        return tutorialCenterTitle;
    }

    /**
     * @return the text of the Center Panel of Tutorial.
     */
    public String getTutorialCenterText() {
        return tutorialCenterText;
    }

    /**
     * @return the title of the end win Panel of Tutorial.
     */
    public String getEndWinPanelTitle() {
        return endWinPanelTitle;
    }

    /**
     * @return the title of the end loses Panel of Tutorial.
     */
    public String getEndLosePanelTitle() {
        return endLosePanelTitle;
    }

    /**
     * @return the text of the end win Panel of Tutorial.
     */
    public String getEndWinPanelText() {
        return endWinPanelText;
    }

    /**
     * @return the text of the end loses Panel of Tutorial.
     */
    public String getEndLosePanelText() {
        return endLosePanelText;
    }
}
