package it.unibo.model.battle.entitydata;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.battle.CellsImpl;
import it.unibo.model.data.TroopType;
import it.unibo.kingdomclash.config.BattleConfiguration;


import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Optional;
import java.util.Arrays;

/**
 * Class used to implement methods of EntityData.
 */
public final class EntityDataImpl implements EntityData {

    /** The number of total troops (player + bot).*/
    private static int totalTroops;
    /** Variable used to indicate the number of troops in the hand.*/
    private final int handTroops;
    /** Indicates all the different troops present in the game.*/
    public static final int TOTAL_DIFFERENT_TROOP = TroopType.values().length;

    private Map<Integer, CellsImpl> entityTroop;
    private final Random random = new Random();

    /**
     * The constructor initializes the troops in the hand.
     * @param battlePanelConfiguration The configuration with the necessary information
     * used in all the battle.
     */
    @SuppressFBWarnings(value = "ST",
            justification = "I need the static variable to use it in the static classes")
    public EntityDataImpl(final BattleConfiguration battlePanelConfiguration) {
        this.entityTroop = new HashMap<>();
        this.handTroops = battlePanelConfiguration.getNrOfSlots();
        totalTroops = battlePanelConfiguration.getNrOfFieldSpots();
        for (int i = 0; i < handTroops; i++) {
            this.entityTroop.put(i, new CellsImpl(TroopType.getRandomTroop(), false));
        }
    }

    @SuppressFBWarnings(value = "EI2",
            justification = "I want to store the input, to get its reference")
    @Override
    public void setEntityTroop(final Map<Integer, CellsImpl> entityTroop) {
        this.entityTroop = entityTroop;
    }
    @SuppressFBWarnings(value = "EI",
            justification = "I want to get the object to modify it and all the references")
    @Override
    public Map<Integer, CellsImpl> getEntityTroop() {
        return this.entityTroop;
    }

    @Override
    public void addEntityTroop(final Integer key) {
        this.entityTroop.get(key).setClicked(true);
    }

    @Override
    public void removeEntityTroop(final Integer key) {
        this.entityTroop.get(key).setClicked(false);
        this.entityTroop.get(key).setChosen(false);
    }

    @Override
    public CellsImpl getCells(final Integer key) {
        return this.entityTroop.get(key);
    }

    @Override
    public List<TroopType> getSelected() {
        final List<TroopType> selectedTroop = new ArrayList<>();
        for (int i = 0; i < handTroops; i++) {
            if (this.entityTroop.get(i).getClicked()) {
                selectedTroop.add(this.entityTroop.get(i).getTroop());
            }
        }
        return selectedTroop;
    }

    @Override
    public List<TroopType> getNotSelected() {
        final List<TroopType> notSelectedTroop = new ArrayList<>();
        for (int i = 0; i < handTroops; i++) {
            if (!this.entityTroop.get(i).getClicked()) {
                notSelectedTroop.add(this.entityTroop.get(i).getTroop());
            }
        }
        return notSelectedTroop;
    }

    @Override
    public Map<Integer, TroopType> changeNotSelectedTroop() {
        final Map<Integer, TroopType> troopChanged = new HashMap<>();
        for (int i = 0; i < handTroops; i++) {
            if (!entityTroop.get(i).getClicked()) {
                entityTroop.get(i).setTroop(TroopType.getRandomTroop());
                troopChanged.put(i, entityTroop.get(i).getTroop());
            }
        }
        return troopChanged;
    }

    @Override
    public void setClickedToChosen() {
        for (int i = 0; i < handTroops; i++) {
            if (entityTroop.get(i).getClicked()) {
                entityTroop.get(i).setChosen(true);
            }
        }
    }

    @Override
    public Integer selectRandomTroop() {
        final List<Integer> keys = new ArrayList<>();
        for (int i = 0; i < handTroops; i++) {
            if (!entityTroop.get(i).getClicked()) {
                keys.add(i);
            }
        }
        int chosenKey;
        chosenKey = random.nextInt(keys.size());
        return keys.get(chosenKey);
    }

    @Override
    public Boolean isMatch(final TroopType troop) {
        if (TroopType.getNullable(troop).isPresent()) {
            return getSelected().contains(TroopType.getNullable(troop).get());
        } else {
            return false;
        }
    }

    @Override
    public Integer getKeyFromTroop(final TroopType troop) {
        if (getNotSelected().contains(troop)) {
            for (int i = 0; i < handTroops; i++) {
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
    public static List<Optional<TroopType>> getOrderedField(final EntityData playerData, final EntityData botData) {
        final List<Optional<TroopType>> playerOptionalList = new ArrayList<>();
        final List<Optional<TroopType>> botOptionalList = new ArrayList<>();
        int differenceSize;

        for (int i = 0; i < TOTAL_DIFFERENT_TROOP; i++) {
            final int a = i;
            playerOptionalList.addAll(playerData.getSelected().stream().filter(x -> x.ordinal() == a).map(Optional::of).toList());
            botOptionalList.addAll(botData.getSelected().stream()
                    .filter(x ->
                            x.equals(TroopType.getNullable(
                                    Arrays.stream(TroopType.values())
                                            .filter(z -> z.ordinal() == a)
                                            .iterator()
                                            .next()).orElse(null))
                    )
                    .map(Optional::of)
                    .toList());
            int b;
            if (playerOptionalList.size() < botOptionalList.size()) {
                differenceSize = botOptionalList.size() - playerOptionalList.size();
                for (b = 0; b < differenceSize; b++) {
                    playerOptionalList.add(Optional.empty());
                }
            } else if (playerOptionalList.size() > botOptionalList.size()) {
                differenceSize = playerOptionalList.size() - botOptionalList.size();
                for (b = 0; b < differenceSize; b++) {
                    botOptionalList.add(Optional.empty());
                }
            }

        }
        final int troopsToFill = totalTroops - playerOptionalList.size();
        for (int a = 0; a < troopsToFill; a++) {
            playerOptionalList.add(Optional.empty());
            botOptionalList.add(Optional.empty());
        }
        playerOptionalList.addAll(botOptionalList);
        return playerOptionalList;
    }

    @Override
    public void setAllChosen() {
        this.entityTroop.values().forEach(x -> x.setChosen(true));
    }

    /**
     * Its ordinate troops in a specific way, taking the getOrderedField.
     * @param botData data of the bot.
     * @param playerData data of the player.
     * @return A new ordered list of troops.
     */
    public static List<Optional<TroopType>> exOrdered(final EntityData botData, final EntityData playerData) {
        final List<Optional<TroopType>> bothOrdered = EntityDataImpl.getOrderedField(playerData, botData);
        final List<Optional<TroopType>> playerOrdered = bothOrdered.subList(0, bothOrdered.size() / 2);
        final List<Optional<TroopType>> botOrdered = bothOrdered.subList(bothOrdered.size() / 2, bothOrdered.size());
        final List<Optional<TroopType>> finalPlayer = new ArrayList<>(totalTroops);
        final List<Optional<TroopType>> finalBot = new ArrayList<>(totalTroops);
        final int maxPosition = totalTroops - 1;

        for (int a = 0; a < totalTroops; a++) {
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
                finalPlayer.set(maxPosition - (f), playerOrdered.get(i));
                if (botOrdered.get(i).isPresent()) {
                    finalBot.set(maxPosition - (f++), botOrdered.get(i));
                } else {
                    finalBot.set(maxPosition - (f++), Optional.empty());
                }
            } else if (playerOrdered.get(i).isEmpty() && botOrdered.get(i).isPresent()
                    && !TroopType.isDefense(botOrdered.get(i).get())) {
                finalBot.set(maxPosition - (f), botOrdered.get(i));
                finalPlayer.set(maxPosition - (f++), Optional.empty());
            } else if (playerOrdered.get(i).isEmpty() && botOrdered.get(i).isPresent()
                    && TroopType.isDefense(botOrdered.get(i).get())) {
                finalBot.set(i, botOrdered.get(i));
                finalPlayer.set(i, Optional.empty());
            }
        }

        finalPlayer.addAll(finalBot);
        return finalPlayer;
    }

}
