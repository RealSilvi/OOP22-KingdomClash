package it.unibo.model.battle.entitydata;

import it.unibo.model.battle.CellsImpl;
import it.unibo.view.battle.Troop;

import java.util.*;

public class PlayerDataImpl implements PlayerData {

    public static final int PLAYER_TROOPS = 5;
    public static final int TOTAL_TROOPS = 10;

    private Map<Integer, CellsImpl> playerTroop = new HashMap<>();

    public PlayerDataImpl(){
        for(int i=0; i < PLAYER_TROOPS; i++){
            this.playerTroop.put(i,new CellsImpl(Troop.getRandomTroop(),false,false));
        }
    }

    @Override
    public void AddPlayerTroop(Integer key) {
        this.playerTroop.get(key).setClicked(true);
    }

    @Override
    public void RemovePlayerTroop(Integer key) {
        this.playerTroop.get(key).setClicked(false);
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
    public Map<Integer, CellsImpl> changeNotSelectedTroop() {
        for(int i=0; i < PLAYER_TROOPS; i++){
            if(!playerTroop.get(i).getClicked()){
                playerTroop.get(i).setTroop(Troop.getRandomTroop());
            }
        }
        return playerTroop;
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

        for( int i=0; i<TOTAL_TROOPS; i++){
            int a=i;
            playerOptionalList.addAll(getSelected().stream().filter(x -> x.getId()==a).map(Optional::of).toList());
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

            if(playerOptionalList.size() < botOptionalList.size()){
                difference_size = botOptionalList.size() - playerOptionalList.size();
                for(i = 0; i < difference_size; i++ ){
                    playerOptionalList.add(Optional.empty());
                }
            } else if (playerOptionalList.size() > botOptionalList.size()) {
                difference_size = playerOptionalList.size() - botOptionalList.size();
                for(i = 0; i < difference_size; i++ ){
                    botOptionalList.add(Optional.empty());
                }
            }

        }

        //updateField(playerOptionalList, botOptionalList)
        return playerOptionalList;
    }

    @Override
    public void setAllChosen() {

        this.playerTroop.values().forEach(x -> x.setChosen(true));

    }

}
