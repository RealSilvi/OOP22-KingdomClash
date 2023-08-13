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
        this.tutorialNorthTitle = "Enemy Troops.";
        this.tutorialNorthText = "You can see which troops the enemy can choose.";
        this.tutorialSouthTitle = "Player Troops.";
        this.tutorialSouthText = "Every round the unlocked slots will refresh and "
                + "give you the possibility to choose new troops which will add to the Battle Field."
                + "When you click on a troop the slot will be locked until the battle ends."
                + "When the battle ends all the slots will unlocked and refresh.";
        this.tutorialWestTitle = "Power Table.";
        this.tutorialWestText = "In this table you can see if you troops are enough "
                + "powerful to win every comparison.";
        this.tutorialEastTitle = "Main Info.";
        this.tutorialEastText = "Here you see your life and enemies life. ì"
                + "When somebody finish his lives the entire battle ends and there's a winner.";
        this.tutorialCenterTitle = "Battle Field.";
        this.tutorialCenterText = "Every round the troops you'll choose will be added in the Battle Field."
                + "Every 3 rounds or when both the players doesnt have more possibility to chose new troops "
                + "the battle will start." + "The combat effectiveness of each troop "
                + "is compared to the level of the opponent's troops. "
                + "If two troops of the same level clash, they cancel each other out. "
                + "However, if one troop has a higher level than the other, "
                + "it prevails over the other. "
                + "The outcome of the combat and the relations between one troop and another "
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
