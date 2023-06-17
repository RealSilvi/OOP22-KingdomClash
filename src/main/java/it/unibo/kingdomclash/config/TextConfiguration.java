package it.unibo.kingdomclash.config;

public  class TextConfiguration{

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


    public TextConfiguration() {
        this.tutorialNorthTitle = " ";
        this.tutorialNorthText = "This panel contains the opponent's set of dice.";
        this.tutorialSouthTitle = " ";
        this.tutorialSouthText = " In this panel, you'll find your set of data, where you can select troops to deploy on the battlefield. The chosen troops are automatically arranged on the battlefield.";
        this.tutorialEastTitle = " ";
        this.tutorialEastText = "This panel provides information about the troops currently on the battlefield. ";
        this.tutorialWestTitle= " ";
        this.tutorialWestText = "Here, the combat effectiveness of each troop is compared to the level of the opponent's troops. If two troops of the same level clash, they cancel each other out. However, if one troop has a higher level than the other, it prevails over the other. ";
        this.tutorialCenterTitle = " ";
        this.tutorialCenterText = "Within this panel, you'll find the battlefield,where deployed troops are automatically arranged. The outcome of the combat between one troop and another is displayed in the information panel. ";
        this.endWinPanelTitle = "YOU WIN";
        this.endWinPanelText=" ";
        this.endLosePanelTitle = "YOU LOSE ";
        this.endLosePanelText = "";
    }

    public String getTutorialNorthTitle() {
        return tutorialNorthTitle;
    }

    public String getTutorialNorthText() {
        return tutorialNorthText;
    }

    public String getTutorialSouthTitle() {
        return tutorialSouthTitle;
    }

    public String getTutorialSouthText() {
        return tutorialSouthText;
    }

    public String getTutorialEastTitle() {
        return tutorialEastTitle;
    }

    public String getTutorialEastText() {
        return tutorialEastText;
    }

    public String getTutorialWestTitle() {
        return tutorialWestTitle;
    }

    public String getTutorialWestText() {
        return tutorialWestText;
    }

    public String getTutorialCenterTitle() {
        return tutorialCenterTitle;
    }

    public String getTutorialCenterText() {
        return tutorialCenterText;
    }

    public String getEndWinPanelTitle() {
        return endWinPanelTitle;
    }

    public String getEndLosePanelTitle() {
        return endLosePanelTitle;
    }

    public String getEndWinPanelText() {
        return endWinPanelText;
    }

    public String getEndLosePanelText() {
        return endLosePanelText;
    }
}
