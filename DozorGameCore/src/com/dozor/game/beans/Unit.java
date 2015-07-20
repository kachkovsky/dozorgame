package com.dozor.game.beans;

/**
 * @author IGOR-K
 */
public class Unit {

    private int hp;
    private int mp;
    private int stage;
    private int xp;
    //null if no tribunal
    private Integer tribunalPoints;

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public Integer getTribunalPoints() {
        return tribunalPoints;
    }

    public void setTribunalPoints(Integer tribunalPoints) {
        this.tribunalPoints = tribunalPoints;
    }

}
