package it.unibo.model.battle.entitydata;

import it.unibo.model.battle.CellsImpl;
import it.unibo.model.data.FightData;
import it.unibo.model.data.GameData;
import it.unibo.view.battle.Troop;

import java.util.*;

public class PlayerDataImpl implements PlayerData {

    public static final int PLAYER_TROOPS = FightData.PLAYER_TROOPS;
    public static final int TOTAL_TROOPS = FightData.TOTAL_TROOPS;
    public static final int TOTAL_DIFFERENT_TROOP = FightData.TOTAL_DIFFERENT_TROOP;

    private Map<Integer, CellsImpl> playerTroop = new HashMap<>();

    public PlayerDataImpl(){
        for(int i=0; i < PLAYER_TROOPS; i++){
            this.playerTroop.put(i,new CellsImpl(Troop.getRandomTroop(),false,false));
        }
    }


    @Override
    public Map<Integer, CellsImpl> getPlayerTroop() {
        return this.playerTroop;
    }

    @Override
    public void setPlayerTroop(Map<Integer, CellsImpl> playerTroop) {
        this.playerTroop = playerTroop;
    }

    @Override
    public void addPlayerTroop(Integer key) {
        this.playerTroop.get(key).setClicked(true);
    }

    @Override
    public void removePlayerTroop(Integer key) {
        this.playerTroop.get(key).setClicked(false);
    }

    @Override
    public CellsImpl getCells(Integer key) {
        return this.playerTroop.get(key);
    }

    @Override
    public List<Troop> getSelected() {
        List<Troop> selectedTroop = new ArrayList<>();
        for(int i=0; i < PLAYER_TROOPS; i++){
            if(this.playerTroop.get(i).getClicked()){
                selectedTroop.add(this.playerTroop.get(i).getTroop());
            }
        }
        return selectedTroop;
    }

    @Override
    public List<Troop> getChosen() {
        List<Troop> chosenTroop = new ArrayList<>();
        for(int i=0; i < PLAYER_TROOPS; i++){
            if(this.playerTroop.get(i).getChosen()){
                chosenTroop.add(this.playerTroop.get(i).getTroop());
            }
        }
        return chosenTroop;
    }

    @Override
    public Map<Integer, Troop> changeNotSelectedTroop() {
        Map<Integer, Troop> troopChanged = new HashMap<>();
        for(int i=0; i < PLAYER_TROOPS; i++){
            if(!playerTroop.get(i).getClicked()){
                playerTroop.get(i).setTroop(Troop.getRandomTroop());
                troopChanged.put(i, playerTroop.get(i).getTroop());
            }
        }
        return troopChanged;
    }

    @Override
    public void setClickedToChosen() {

        for(int i = 0; i < PLAYER_TROOPS; i++){
            if(playerTroop.get(i).getClicked()){
                playerTroop.get(i).setChosen(true);
            }
        }

    }

    @Override
    public List<Optional<Troop>> getOrderedField(BotData botData) {
        List<Optional<Troop>> playerOptionalList = new ArrayList<>();
        List<Optional<Troop>> botOptionalList = new ArrayList<>();
        int difference_size;

        for( int i=0; i<TOTAL_DIFFERENT_TROOP; i++) {
            int a = i;
                playerOptionalList.addAll(getSelected().stream().filter(x -> x.getId() == a).map(Optional::of).toList());
                botOptionalList.addAll(botData.getSelected().stream()
                        .filter(x ->
                                x.equals(Troop.getNullable(
                                        Arrays.stream(Troop.values())
                                                .filter(z -> z.getId() == a)
                                                .iterator()
                                                .next()))
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

        return playerOptionalList;
    }

    @Override
    public void setAllChosen() {

        this.playerTroop.values().forEach(x -> x.setChosen(true));

    }

}
