package it.unibo.model.battle;

import it.unibo.view.battle.Troop;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;

public class BattleModelImpl implements BattleModel{

    public static final int TOTAL_TROOPS = 10;
    public static final int PLAYER_TROOPS = 5;
    public static final int BOT_TROOPS = 5;
    public static final int CORRISPONDED_TROOP = 0;

    //private final Map<Integer, Map<Troop, Boolean>> battleTroop = new HashMap<>();
    private List<Troop> playerTroop = new ArrayList<>();
    private List<Troop> botTroop = new ArrayList<>();

    private List<Optional<Troop>> playerField = new ArrayList<>();
    private List<Optional<Troop>> botField = new ArrayList<>();

    int counted_round = 0;
    int botLife = 10;

    int playerLife = 10;


    private void Initialize(){
        Map<Troop, Boolean> buttonTroop = new HashMap<>();
        buttonTroop.put(Troop.getRandomTroop(),true);
        int i;
        for(i = 0; i < TOTAL_TROOPS; i++){
            this.battleTroop.put(i,buttonTroop);
        }
    }

    @Override
    public void BattlePass() {



    }

    @Override
    public void BattleSpin() {

        int i;
        for(i = PLAYER_TROOPS; i < TOTAL_TROOPS; i++){
            Troop troop = conteinedTroop(i);
            if(this.battleTroop.get(i).get(troop)){
                this.battleTroop.get(i).remove(troop);
                this.battleTroop.get(i).put(Troop.getRandomTroop(),true);
            }
        }

        //TODO notify the controller to disable SpinButton and change the troops.

    }

    private void BattleCombat(){

        playerField.forEach(x -> {

                if(botField.get(CORRISPONDED_TROOP).isPresent() && x.isPresent()){
                    if(x.get().getLevel() > botField.get(CORRISPONDED_TROOP).get().getLevel()){
                        if(!x.get().isDefense()){
                            botLife--;
                        }
                    }else if(x.get().getLevel() < botField.get(CORRISPONDED_TROOP).get().getLevel()){
                        if(x.get().isDefense()){
                            playerLife--;
                        }
                    }
                }else if(botField.get(CORRISPONDED_TROOP).isEmpty() && x.isPresent() && (!x.get().isDefense())){
                    botLife--;
                }else if(x.isEmpty() && botField.get(CORRISPONDED_TROOP).isPresent() && (!botField.get(CORRISPONDED_TROOP).get().isDefense())){
                    playerLife--;
                }

                if(botField.get(CORRISPONDED_TROOP).isPresent()){
                    botField.remove(CORRISPONDED_TROOP);
                }

        });

        playerField = null;

    }

    private Troop conteinedTroop(Integer key){

        int key_troop = 0;
        Troop troop = getRightTroop(key_troop);

        while(!this.battleTroop.get(key).containsKey(troop)) {
            key_troop++;
            troop = getRightTroop(key_troop);
        }

        return troop;
    }

    private void getOrderedField(){
        List<Optional<Troop>> playerOptionalList = new ArrayList<>();
        List<Optional<Troop>> botOptionalList = new ArrayList<>();
        playerField = playerOptionalList;
        botField = botOptionalList;
        int difference_size;

        for( int i=0; i<TOTAL_TROOPS; i++){
            int a=i;
            playerOptionalList.addAll(playerTroop.stream().filter(x -> x.getId()==a).map(Optional::of).toList());
            botOptionalList.addAll(botTroop.stream()
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

    }

    private void notifyController(){

    }



}