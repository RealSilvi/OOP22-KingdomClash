package it.unibo.view.battle.config;


public enum JsonKeys implements JsonKeysInterface {
    ;
    private String key;

    @Override
    public String getKey() {
        return this.key;
    }

    public enum BattlePanelJsonKeys implements JsonKeysInterface {

        KEY("BattlePanel");

        private String key;

        @Override
        public String getKey() {
            return this.key;
        }

        private BattlePanelJsonKeys(String k) {
            this.key = k;
        }

        public enum TutorialPanelJsonKeys implements JsonKeysInterface {
            KEY("TutorialPanel");

            private String key;

            private TutorialPanelJsonKeys(String k) {
                this.key = k;
            }

            @Override
            public String getKey() {
                return this.key;
            }

            public enum NorthPanelJsonKeys implements JsonKeysInterface {
                KEY("NorthPanel"), TITLE_KEY("title"), TEXT_KEY("text");

                private String key;

                private NorthPanelJsonKeys(String k) {
                    this.key = k;
                }

                @Override
                public String getKey() {
                    return this.key;
                }
            }

            public enum SouthPanelJsonKeys implements JsonKeysInterface {
                KEY("SorthPanel"), TITLE_KEY("title"), TEXT_KEY("text");

                private String key;

                private SouthPanelJsonKeys(String k) {
                    this.key = k;
                }

                @Override
                public String getKey() {
                    return this.key;
                }
            }

            public enum EastPanelJsonKeys implements JsonKeysInterface {
                KEY("EastPanel"), TITLE_KEY("title"), TEXT_KEY("text");

                private String key;

                private EastPanelJsonKeys(String k) {
                    this.key = k;
                }

                @Override
                public String getKey() {
                    return this.key;
                }
            }

            public enum WestPanelJsonKeys implements JsonKeysInterface {
                KEY("WestPanel"), TITLE_KEY("title"), TEXT_KEY("text");

                private String key;

                private WestPanelJsonKeys(String k) {
                    this.key = k;
                }

                @Override
                public String getKey() {
                    return this.key;
                }
            }

            public enum CenterPanelJsonKeys implements JsonKeysInterface {
                KEY("NorthPanel"), TITLE_KEY("title"), TEXT_KEY("text");

                private String key;

                private CenterPanelJsonKeys(String k) {
                    this.key = k;
                }

                @Override
                public String getKey() {
                    return this.key;
                }
            }


        }

        public enum EndPanelJsonKeys implements JsonKeysInterface {
            KEY("EndPanle"), TITLE_KEY("title"), TEXT_KEY("");

            private String key;

            private EndPanelJsonKeys(String k) {
                this.key = k;
            }

            @Override
            public String getKey() {
                return this.key;
            }
        }


    }

}
