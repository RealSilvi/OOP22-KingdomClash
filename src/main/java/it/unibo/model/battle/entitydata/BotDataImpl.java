package it.unibo.model.battle.entitydata;

import it.unibo.model.battle.CellsImpl;
import it.unibo.model.data.FightData;
import it.unibo.model.data.GameData;
import it.unibo.view.battle.Troop;

import java.util.*;

public class BotDataImpl implements BotData{

    public static final int BOT_TROOPS = FightData.BOT_TROOPS;
    public static final int TOTAL_TROOPS = FightData.TOTAL_TROOPS;
    public static final int TOTAL_DIFFERENT_TROOP = FightData.TOTAL_DIFFERENT_TROOP;
    private Map<Integer, CellsImpl> botTroop = new HashMap<>();

    public BotDataImpl(){
        for(int i=0; i < BOT_TROOPS; i++){
            this.botTroop.put(i,new CellsImpl(Troop.getRandomTroop(),false,false));
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
    }

    @Override
    public CellsImpl getCells(Integer key) {
        return this.botTroop.get(key);
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
    public Map<Integer, Troop> changeNotSelectedTroop() {
        Map<Integer, Troop> troopChanged = new HashMap<>();
        for(int i=0; i < BOT_TROOPS; i++){
            if(!botTroop.get(i).getClicked()){
                botTroop.get(i).setTroop(Troop.getRandomTroop());
                troopChanged.put(i, botTroop.get(i).getTroop());
            }
        }
        return troopChanged;
    }

    @Override
    public void setClickedToChosen() {

        for(int i = 0; i < BOT_TROOPS; i++){
            if(botTroop.get(i).getClicked()){
                botTroop.get(i).setChosen(true);
            }
        }

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
        System.out.println("chosen key: " + chosen_key);
        System.out.println("toList: " + keys.toString());
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

        for( int i=0; i<TOTAL_DIFFERENT_TROOP; i++){
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

            int b=0;
            if(playerOptionalList.size() < botOptionalList.size()){
                difference_size = botOptionalList.size() - playerOptionalList.size();
                for(b = 0; b < difference_size; b++ ){
                    playerOptionalList.add(Optional.empty());
                }
            } else if (playerOptionalList.size() > botOptionalList.size()) {
                difference_size = playerOptionalList.size() - botOptionalList.size();
                for(b = 0; b < difference_size; b++ ){
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


}
