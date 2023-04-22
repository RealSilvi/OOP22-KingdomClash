package it.unibo.model.battle;

import it.unibo.view.battle.Troop;
import java.util.HashMap;
import java.util.Map;

public class BattleModelImpl implements BattleModel{

    public static final int TOTAL_TROOPS = 10;
    public static final int PLAYER_TROOPS = 5;
    public static final int BOT_TROOPS = 5;

    private final Map<Integer, Map<Troop, Boolean>> battleTroop = new HashMap<>();

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

    @Override
    public void ChoosenButton(Integer key){

        Troop troop = conteinedTroop(key);

        if((!this.battleTroop.isEmpty()) && this.battleTroop.get(key).containsValue(true)){
            this.battleTroop.get(key).put(troop, false);
        }else{
            this.battleTroop.get(key).put(troop, true);
        }

        //TODO notify the controller to change the status of the selected troop and the field

    }

    private Troop getRightTroop(Integer key_troop){

        if(Troop.AXE.getId() == key_troop){
            return Troop.AXE;
        } else if (Troop.ARROW.getId() == key_troop) {
            return Troop.ARROW;
        }else if (Troop.HELMET.getId() == key_troop) {
            return Troop.HELMET;
        }else if (Troop.SHIELD.getId() == key_troop) {
            return Troop.SHIELD;
        }else if (Troop.DODGE.getId() == key_troop) {
            return Troop.DODGE;
        }else if (Troop.TOWER.getId() == key_troop) {
            return Troop.TOWER;
        }else if (Troop.CATAPULT.getId() == key_troop) {
            return Troop.CATAPULT;
        }else {
            return Troop.SWORD;
        }

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

}