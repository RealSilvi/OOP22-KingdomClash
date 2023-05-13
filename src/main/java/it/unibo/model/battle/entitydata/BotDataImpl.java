package it.unibo.model.battle.entitydata;

import it.unibo.model.battle.Cells;
import it.unibo.model.battle.CellsImpl;
import it.unibo.view.battle.Troop;

import java.util.*;

public class BotDataImpl implements BotData{

    public static final int BOT_TROOPS = 5;
    public static final int TOTAL_TROOPS = 10;
    private Map<Integer, CellsImpl> botTroop = new HashMap<>();

    public BotDataImpl(){
        for(int i=0; i < BOT_TROOPS; i++){
            this.botTroop.put(i,new CellsImpl(Troop.getRandomTroop(),false,false));
        }
    }

    @Override
    public void AddBotTroop(Integer key) {
        this.botTroop.get(key).setClicked(true);
    }

    @Override
    public void RemoveBotTroop(Integer key) {
        this.botTroop.get(key).setClicked(false);
    }

    @Override
    public List<Troop> getSelected() {
        List<Troop> selectedTroop = new ArrayList<>();
        for(int i=0; i < BOT_TROOPS; i++){
            if(this.botTroop.get(i).getClicked()){
                selectedTroop.add(this.botTroop.get(i).getTroop());
            }
        }
        return selectedTroop;
    }

    @Override
    public List<Troop> getNotSelected() {
        List<Troop> notSelectedTroop = new ArrayList<>();
        for(int i=0; i < BOT_TROOPS; i++){
            if(!this.botTroop.get(i).getClicked()){
                notSelectedTroop.add(this.botTroop.get(i).getTroop());
            }
        }
        return notSelectedTroop;
    }

    @Override
    public List<Troop> getChosen() {
        List<Troop> chosenTroop = new ArrayList<>();
        for(int i=0; i < BOT_TROOPS; i++){
            if(this.botTroop.get(i).getChosen()){
                chosenTroop.add(this.botTroop.get(i).getTroop());
            }
        }
        return chosenTroop;
    }

    @Override
    public Map<Integer, CellsImpl> changeNotSelectedTroop() {
        for(int i=0; i < BOT_TROOPS; i++){
            if(!botTroop.get(i).getClicked()){
                botTroop.get(i).setTroop(Troop.getRandomTroop());
            }
        }
        return botTroop;
    }

    @Override
    public Integer selectRandomTroop() {
        List<Integer> keys = new ArrayList<>();

        for(int i=0; i < BOT_TROOPS; i++){
            if(!botTroop.get(i).getClicked()){
                keys.add(i);
            }
        }
        Random random = new Random();
        int chosen_key;
        chosen_key = random.nextInt(keys.size());
        return keys.get(chosen_key);
    }

    @Override
    public Boolean isMatch(Troop troop) {

        return getSelected().contains(Troop.getNullable(troop));

    }

    @Override
    public Integer getKeyFromTroop(Troop troop) {

        if(getNotSelected().contains(troop)){
                for(int i=0; i < BOT_TROOPS; i++){
                    if (botTroop.get(i).getTroop().equals(troop) && !botTroop.get(i).getClicked()) {
                        return i;
                    }
                }
        }
        return -1;
    }

    @Override
    public List<Optional<Troop>> getOrderedField(PlayerData playerData) {
        List<Optional<Troop>> playerOptionalList = new ArrayList<>();
        List<Optional<Troop>> botOptionalList = new ArrayList<>();
        int difference_size;

        for( int i=0; i<TOTAL_TROOPS; i++){
            int a=i;
            playerOptionalList.addAll(playerData.getSelected().stream().filter(x -> x.getId()==a).map(Optional::of).toList());
            botOptionalList.addAll(getSelected().stream()
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
        return botOptionalList;
    }

    @Override
    public void setAllChosen() {

        this.botTroop.values().forEach(x -> x.setChosen(true));

    }


}
