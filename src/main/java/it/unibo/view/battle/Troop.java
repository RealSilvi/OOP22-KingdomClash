package it.unibo.view.battle;

import java.util.Arrays;
import java.util.Random;

public enum Troop {

    AXE(0," ",false,1),

    SWORD(1," ",false,1),

    CATAPULT(2," ",false,1),

    ARROW(3," ",false,1),

    SHIELD(4," ",true,1),

    HELMET(5," ",true,1),

    TOWER(6," ",true,1),

    DODGE(7," ",true,1);

    private final int id;
    private final String url;
    private final boolean defense;
    private final int level;

    Troop(int id, String url, boolean defense, int level) {
        this.id = id;
        this.url = url;
        this.defense = defense;
        this.level=level;
    }

    public static Troop getRandomTroop(){
        Random r = new Random();

        return Arrays.stream(values())
                .skip(r.nextInt(values().length))
                .iterator()
                .next();
    }

    public int getId() {
        return this.id;
    }

    public String getUrl() {
        return this.url;
    }

    public boolean isDefense() {
        return this.defense;
    }

    public int getLevel() {
        return this.level;
    }

    public Troop getNullable(Troop troop){
        return (troop.equals(Troop.AXE)) ? Troop.HELMET :
                (troop.equals(Troop.SWORD)) ? Troop.DODGE :
                        (troop.equals(Troop.CATAPULT)) ? Troop.TOWER :
                                (troop.equals(Troop.ARROW)) ? Troop.SHIELD :
                                        (troop.equals(Troop.SHIELD)) ? Troop.ARROW :
                                                (troop.equals(Troop.TOWER)) ? Troop.CATAPULT :
                                                        (troop.equals(Troop.DODGE)) ? Troop.SWORD :
                                                                Troop.CATAPULT;
    }

    @Override
    public String toString() {
        return "Troop{" +
                "id=" + this.getId() +
                ", url='" + this.getUrl() + '\'' +
                ", defense=" + this.isDefense() +
                ", level=" + this.getLevel() +
                '}';
    }

}
