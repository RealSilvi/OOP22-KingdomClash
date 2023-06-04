package it.unibo.model.battle.entitydata;

import it.unibo.model.battle.CellsImpl;
import it.unibo.model.data.FightData;
import it.unibo.model.data.TroopType;

import java.util.*;

import static it.unibo.model.data.FightData.TOTAL_TROOPS;

public class BotDataImpl implements BotData {

    public static final int BOT_TROOPS = FightData.BOT_TROOPS;
    public static final int TOTAL_DIFFERENT_TROOP = FightData.TOTAL_DIFFERENT_TROOP;
    private Map<Integer, CellsImpl> botTroop = new HashMap<>();

    public BotDataImpl() {
        for (int i = 0; i < BOT_TROOPS; i++) {
            this.botTroop.put(i, new CellsImpl(TroopType.getRandomTroop(), false, false));
        }
    }

    @Override
    public Map<Integer, CellsImpl> getBotTroop() {
        return this.botTroop;
    }

    @Override
    public void setBotTroop(Map<Integer, CellsImpl> botTroop) {
        this.botTroop = botTroop;
    }

    @Override
    public void addBotTroop(Integer key) {
        this.botTroop.get(key).setClicked(true);
    }

    @Override
    public void removeBotTroop(Integer key) {
        this.botTroop.get(key).setClicked(false);
        this.botTroop.get(key).setChosen(false);
    }

    @Override
    public CellsImpl getCells(Integer key) {
        return this.botTroop.get(key);
    }

    @Override
    public List<TroopType> getSelected() {
        List<TroopType> selectedTroop = new ArrayList<>();
        for (int i = 0; i < BOT_TROOPS; i++) {
            if (this.botTroop.get(i).getClicked()) {
                selectedTroop.add(this.botTroop.get(i).getTroop());
            }
        }
        return selectedTroop;
    }

    @Override
    public List<TroopType> getNotSelected() {
        List<TroopType> notSelectedTroop = new ArrayList<>();
        for (int i = 0; i < BOT_TROOPS; i++) {
            if (!this.botTroop.get(i).getClicked()) {
                notSelectedTroop.add(this.botTroop.get(i).getTroop());
            }
        }
        return notSelectedTroop;
    }

    @Override
    public List<TroopType> getChosen() {
        List<TroopType> chosenTroop = new ArrayList<>();
        for (int i = 0; i < BOT_TROOPS; i++) {
            if (this.botTroop.get(i).getChosen()) {
                chosenTroop.add(this.botTroop.get(i).getTroop());
            }
        }
        return chosenTroop;
    }

    @Override
    public Map<Integer, TroopType> changeNotSelectedTroop() {
        Map<Integer, TroopType> troopChanged = new HashMap<>();
        for (int i = 0; i < BOT_TROOPS; i++) {
            if (!botTroop.get(i).getClicked()) {
                botTroop.get(i).setTroop(TroopType.getRandomTroop());
                troopChanged.put(i, botTroop.get(i).getTroop());
            }
        }
        return troopChanged;
    }

    @Override
    public void setClickedToChosen() {

        for (int i = 0; i < BOT_TROOPS; i++) {
            if (botTroop.get(i).getClicked()) {
                botTroop.get(i).setChosen(true);
            }
        }

    }

    @Override
    public Integer selectRandomTroop() {
        List<Integer> keys = new ArrayList<>();

        for (int i = 0; i < BOT_TROOPS; i++) {
            if (!botTroop.get(i).getClicked()) {
                keys.add(i);
            }
        }
        Random random = new Random();
        int chosen_key;
        chosen_key = random.nextInt(keys.size());
        System.out.println("chosen key: " + chosen_key);
        System.out.println("toList: " + keys.toString());
        return keys.get(chosen_key);
    }

    @Override
    public Boolean isMatch(TroopType troop) {

        return getSelected().contains(TroopType.getNullable(troop));

    }

    @Override
    public Integer getKeyFromTroop(TroopType troop) {

        if (getNotSelected().contains(troop)) {
            for (int i = 0; i < BOT_TROOPS; i++) {
                if (botTroop.get(i).getTroop().equals(troop) && !botTroop.get(i).getClicked()) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public List<Optional<TroopType>> getOrderedField(PlayerData playerData) {
        List<Optional<TroopType>> playerOptionalList = new ArrayList<>();
        List<Optional<TroopType>> botOptionalList = new ArrayList<>();
        int difference_size;

        for (int i = 0; i < TOTAL_DIFFERENT_TROOP; i++) {
            int a = i;
            playerOptionalList.addAll(playerData.getSelected().stream().filter(x -> x.ordinal() == a).map(Optional::of).toList());
            botOptionalList.addAll(getSelected().stream()
                    .filter(x ->
                            x.equals(TroopType.getNullable(
                                    Arrays.stream(TroopType.values())
                                            .filter(z -> z.ordinal() == a)
                                            .iterator()
                                            .next()).get())
                    )
                    .map(Optional::of)
                    .toList());

            int b = 0;
            if (playerOptionalList.size() < botOptionalList.size()) {
                difference_size = botOptionalList.size() - playerOptionalList.size();
                for (b = 0; b < difference_size; b++) {
                    playerOptionalList.add(Optional.empty());
                }
            } else if (playerOptionalList.size() > botOptionalList.size()) {
                difference_size = playerOptionalList.size() - botOptionalList.size();
                for (b = 0; b < difference_size; b++) {
                    botOptionalList.add(Optional.empty());
                }
            }

        }

        return botOptionalList;
    }

    @Override
    public void setAllChosen() {

        this.botTroop.values().forEach(x -> x.setChosen(true));

    }

    public List<Optional<TroopType>> ExOrdered(PlayerData playerData) {
        List<Optional<TroopType>> playerOrdered = playerData.getOrderedField(this);
        List<Optional<TroopType>> botOrdered = getOrderedField(playerData);
        List<Optional<TroopType>> finalPlayer = new ArrayList<>(TOTAL_TROOPS);
        List<Optional<TroopType>> finalBot = new ArrayList<>(TOTAL_TROOPS);
        int max_position = TOTAL_TROOPS - 1;

        for (int a = 0; a < TOTAL_TROOPS; a++) {
            finalPlayer.add(Optional.empty());
            finalBot.add(Optional.empty());
        }

        int f = 0;
        for (int i = 0; i < botOrdered.size(); i++) {
            if (playerOrdered.get(i).isPresent() && !TroopType.isDefense(playerOrdered.get(i).get())) {
                finalPlayer.set(i, playerOrdered.get(i));
                if (botOrdered.get(i).isPresent()) {
                    finalBot.set(i, botOrdered.get(i));
                } else {
                    finalBot.set(i, Optional.empty());
                }
            } else if (playerOrdered.get(i).isPresent()) {
                finalPlayer.set(max_position - (f), playerOrdered.get(i));
                if (botOrdered.get(i).isPresent()) {
                    finalBot.set(max_position - (f++), botOrdered.get(i));
                } else {
                    finalBot.set(max_position - (f++), Optional.empty());
                }
            } else if (playerOrdered.get(i).isEmpty() && botOrdered.get(i).isPresent() && !TroopType.isDefense(botOrdered.get(i).get())) {
                finalBot.set(max_position - (f), botOrdered.get(i));
                finalPlayer.set(max_position - (f++), Optional.empty());
            } else if (playerOrdered.get(i).isEmpty() && botOrdered.get(i).isPresent() && TroopType.isDefense(botOrdered.get(i).get())) {
                finalBot.set(i, botOrdered.get(i));
                finalPlayer.set(i, Optional.empty());
            } else if (playerOrdered.get(i).isEmpty() && botOrdered.get(i).isEmpty()) {
                finalPlayer.set(i, Optional.empty());
                finalBot.set(i, Optional.empty());
            }
        }


        return finalBot;
    }


}
