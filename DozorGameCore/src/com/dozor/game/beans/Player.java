package com.dozor.game.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @author IGOR-K
 */
public class Player {

    private List<Unit> unitsList = new ArrayList<>();
    private int evidences;
    private String nick;

    public List<Unit> getUnitsList() {
        return unitsList;
    }

    public int getEvidences() {
        return evidences;
    }

    public void setEvidences(int evidences) {
        this.evidences = evidences;
    }

    public void setUnitsList(List<Unit> unitsList) {
        this.unitsList = unitsList;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }


}
