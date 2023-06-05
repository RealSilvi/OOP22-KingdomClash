package it.unibo.view.battle;

import java.util.Arrays;
import java.util.Random;
//TODO: Remove this enum because it has become useless now
public enum Troop {

    AXE(0, false, 1),

    SWORD(1, false, 1),

    CATAPULT(2, false, 1),

    ARROW(3, false, 1),

    SHIELD(4, true, 1),

    HELMET(5, true, 1),

    TOWER(6, true, 1),

    DODGE(7, true, 1);

    private final int id;
    private final boolean defense;
    private final int level;

    Troop(int id, boolean defense, int level) {
        this.id = id;
        this.defense = defense;
        this.level = level;
    }

    public static Troop getRandomTroop() {
        Random r = new Random();

        return Arrays.stream(values())
                .skip(r.nextInt(values().length))
                .iterator()
                .next();
    }

    public int getId() {
        return this.id;
    }

    public boolean isDefense() {
        return this.defense;
    }

    public int getLevel() {
        return this.level;
    }

    public static Troop getNullable(Troop troop) {
        return (troop.equals(Troop.AXE)) ? Troop.HELMET :
                (troop.equals(Troop.SWORD)) ? Troop.DODGE :
                        (troop.equals(Troop.CATAPULT)) ? Troop.TOWER :
                                (troop.equals(Troop.ARROW)) ? Troop.SHIELD :
                                        (troop.equals(Troop.SHIELD)) ? Troop.ARROW :
                                                (troop.equals(Troop.HELMET)) ? Troop.AXE :
                                                        (troop.equals(Troop.TOWER)) ? Troop.CATAPULT :
                                                                (troop.equals(Troop.DODGE)) ? Troop.SWORD :
                                                                        null;
    }

    @Override
    public String toString() {
        return "Troop{" +
                "id=" + this.getId() +
                ", defense=" + this.isDefense() +
                ", level=" + this.getLevel() +
                '}';
    }

    public static Troop getTroop(Integer id) {
        if (Troop.AXE.id == id) {
            return Troop.AXE;
        } else if (Troop.SWORD.id == id) {
            return Troop.SWORD;
        } else if (Troop.CATAPULT.id == id) {
            return Troop.CATAPULT;
        } else if (Troop.ARROW.id == id) {
            return Troop.ARROW;
        } else if (Troop.SHIELD.id == id) {
            return Troop.SHIELD;
        } else if (Troop.HELMET.id == id) {
            return Troop.HELMET;
        } else if (Troop.TOWER.id == id) {
            return Troop.TOWER;
        } else {
            return Troop.DODGE;
        }

    }

}
