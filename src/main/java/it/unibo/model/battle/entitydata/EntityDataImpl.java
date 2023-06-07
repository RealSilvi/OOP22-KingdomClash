package it.unibo.model.battle.entitydata;

import it.unibo.controller.battle.BattleControllerImpl;
import it.unibo.model.battle.CellsImpl;
import it.unibo.model.data.FightData;
import it.unibo.model.data.TroopType;

import java.util.*;

import static it.unibo.model.data.FightData.TOTAL_TROOPS;

public class EntityDataImpl implements EntityData{

    public static final int HAND_TROOPS = FightData.HAND_TROOPS;
    public static final int TOTAL_DIFFERENT_TROOP = FightData.TOTAL_DIFFERENT_TROOP;
    public static final int PLAYER = BattleControllerImpl.PLAYER;
    public static final int BOT = BattleControllerImpl.BOT;

    private Map<Integer, CellsImpl> entityTroop = new HashMap<>();

    public EntityDataImpl() {
        for (int i = 0; i < HAND_TROOPS; i++) {
            this.entityTroop.put(i, new CellsImpl(TroopType.getRandomTroop(), false, false));
        }
    }

    public Map<Integer, CellsImpl> getEntityTroop() {
        return this.entityTroop;
    }

    public void setEntityTroop(Map<Integer, CellsImpl> entityTroop) {
        this.entityTroop = entityTroop;
    }

    public void addEntityTroop(Integer key) {
        this.entityTroop.get(key).setClicked(true);
    }

    public void removeEntityTroop(Integer key) {
        this.entityTroop.get(key).setClicked(false);
        this.entityTroop.get(key).setChosen(false);
    }

    public CellsImpl getCells(Integer key) {
        return this.entityTroop.get(key);
    }

    public List<TroopType> getSelected() {
        List<TroopType> selectedTroop = new ArrayList<>();
        for (int i = 0; i < HAND_TROOPS; i++) {
            if (this.entityTroop.get(i).getClicked()) {
                selectedTroop.add(this.entityTroop.get(i).getTroop());
            }
        }
        return selectedTroop;
    }

    public List<TroopType> getNotSelected() {
        List<TroopType> notSelectedTroop = new ArrayList<>();
        for (int i = 0; i < HAND_TROOPS; i++) {
            if (!this.entityTroop.get(i).getClicked()) {
                notSelectedTroop.add(this.entityTroop.get(i).getTroop());
            }
        }
        return notSelectedTroop;
    }

    public List<TroopType> getChosen() {
        List<TroopType> chosenTroop = new ArrayList<>();
        for (int i = 0; i < HAND_TROOPS; i++) {
            if (this.entityTroop.get(i).getChosen()) {
                chosenTroop.add(this.entityTroop.get(i).getTroop());
            }
        }
        return chosenTroop;
    }

    public Map<Integer, TroopType> changeNotSelectedTroop() {
        Map<Integer, TroopType> troopChanged = new HashMap<>();
        for (int i = 0; i < HAND_TROOPS; i++) {
            if (!entityTroop.get(i).getClicked()) {
                entityTroop.get(i).setTroop(TroopType.getRandomTroop());
                troopChanged.put(i, entityTroop.get(i).getTroop());
            }
        }
        return troopChanged;
    }

    public void setClickedToChosen() {
        for (int i = 0; i < HAND_TROOPS; i++) {
            if (entityTroop.get(i).getClicked()) {
                entityTroop.get(i).setChosen(true);
            }
        }
    }

    public Integer selectRandomTroop() {
        List<Integer> keys = new ArrayList<>();
        for (int i = 0; i < HAND_TROOPS; i++) {
            if (!entityTroop.get(i).getClicked()) {
                keys.add(i);
            }
        }
        Random random = new Random();
        int chosen_key;
        chosen_key = random.nextInt(keys.size());
        return keys.get(chosen_key);
    }

    public Boolean isMatch(TroopType troop) {
        return getSelected().contains(TroopType.getNullable(troop).get());
    }

    public Integer getKeyFromTroop(TroopType troop) {
        if (getNotSelected().contains(troop)) {
            for (int i = 0; i < HAND_TROOPS; i++) {
                if (entityTroop.get(i).getTroop().equals(troop) && !entityTroop.get(i).getClicked()) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Orders the field. Puts each troop against nothing or against the opposite,
     * creating a corrected field, with empty spaces and right troops.
     *
     * @param playerData the data of the player,
     *                   and therefore the corresponding troops chosen,
     *                   which we want to compare with those of the bot
     * @param botData    the data of the bot,
     *                   and therefore the corresponding troops chosen,
     *                   which we want to compare with those of the player.
     * @return the corrected entity's field.
     */
    public static List<Optional<TroopType>> getOrderedField(EntityData playerData, EntityData botData) {
        List<Optional<TroopType>> playerOptionalList = new ArrayList<>();
        List<Optional<TroopType>> botOptionalList = new ArrayList<>();
        int difference_size;

        for (int i = 0; i < TOTAL_DIFFERENT_TROOP; i++) {
            int a = i;
            playerOptionalList.addAll(playerData.getSelected().stream().filter(x -> x.ordinal() == a).map(Optional::of).toList());
            botOptionalList.addAll(botData.getSelected().stream()
                    .filter(x ->
                            x.equals(TroopType.getNullable(
                                    Arrays.stream(TroopType.values())
                                            .filter(z -> z.ordinal() == a)
                                            .iterator()
                                            .next()).get())
                    )
                    .map(Optional::of)
                    .toList());
            int b;
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

        playerOptionalList.addAll(botOptionalList);
        return playerOptionalList;
    }


    public void setAllChosen() {

        this.entityTroop.values().forEach(x -> x.setChosen(true));

    }

    public static List<Optional<TroopType>> ExOrdered(EntityData botData, EntityData playerData) {
        List<Optional<TroopType>> bothOrdered = EntityDataImpl.getOrderedField(playerData,botData);
        List<Optional<TroopType>> playerOrdered = bothOrdered.subList(0,(bothOrdered.size()/2)-1);
        List<Optional<TroopType>> botOrdered = bothOrdered.subList(bothOrdered.size()/2,bothOrdered.size()-1);
        List<Optional<TroopType>> finalPlayer = new ArrayList<>(TOTAL_TROOPS);
        List<Optional<TroopType>> finalBot = new ArrayList<>(TOTAL_TROOPS);
        int max_position = TOTAL_TROOPS - 1;

        for (int a = 0; a < TOTAL_TROOPS; a++) {
            finalPlayer.add(Optional.empty());
            finalBot.add(Optional.empty());
        }

        int f = 0;
        for (int i = 0; i < playerOrdered.size(); i++) {
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

        finalPlayer.addAll(finalBot);
        return finalPlayer;
    }

}
