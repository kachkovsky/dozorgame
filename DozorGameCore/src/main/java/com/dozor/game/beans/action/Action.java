package com.dozor.game.beans.action;

import java.util.List;

/**
 * @author IGOR-K
 */
public class Action {
    public enum ActionType {
        NEW_UNIT,
        UP_XP,
        UP_MP,
        UP_STAGE,
        DOWN_STAGE,
        FIRE,
        CHOOSE_TRIBUNAL,
        SEND_TRIBUNAL_UNIT_POINTS,
        TRIBUNAL_KILL,
        NOTHING;
    }

    private ActionType action;
    private int unitIndex;
    private List<Integer> pointsList;

    public ActionType getAction() {
        return action;
    }

    public void setAction(ActionType action) {
        this.action = action;
    }

    public int getUnitIndex() {
        return unitIndex;
    }

    public void setUnitIndex(int unitIndex) {
        this.unitIndex = unitIndex;
    }

    public List<Integer> getPointsList() {
        return pointsList;
    }

    public void setPointsList(List<Integer> pointsList) {
        this.pointsList = pointsList;
    }

}
