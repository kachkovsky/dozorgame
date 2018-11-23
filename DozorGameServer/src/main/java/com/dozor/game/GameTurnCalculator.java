package com.dozor.game;

import com.dozor.game.bean.parser.GameJsonParser;
import com.dozor.game.beans.*;
import com.dozor.game.beans.TurnPosition.PartOfTurn;
import com.dozor.game.beans.action.Action;
import com.dozor.game.beans.action.Action.ActionType;
import com.dozor.game.beansfactory.UnitsFactory;
import com.dozor.game.utils.EvidencesCalculator;
import com.dozor.game.utils.LevelCalculator;
import com.dozor.game.utils.UnitsCalculator;
import org.json.JSONException;

import java.util.EnumSet;

/**
 * @author IGOR-K
 */
public class GameTurnCalculator {

    private GameState gameState;

    //false if turn incorret
    public GameState calcTurn(GameState gameState, Action action, int playerIndex) throws JSONException {
        if (playerIndex < 0 || playerIndex > 1) {
            return null;
        }

        if ((gameState.getTurnPosition().getPlayerIndex() != playerIndex) ^ TurnPosition.PartOfTurn.TRIBUNAL_POINTS.equals(gameState.getTurnPosition().getPartOfTurn())) {
            return null;
        }
        switch (action.getAction()) {
            case NEW_UNIT:
                if (!TurnPosition.PartOfTurn.NORMAL.equals(gameState.getTurnPosition().getPartOfTurn())) {
                    return null;
                }
                if (GameUtils.getCurrentUnit(gameState).getMp() < UnitsCalculator.getMpForNewUnit(GameUtils.getCurrentPlayer(gameState).getUnitsList())) {
                    return null;
                }
                break;
            case UP_XP:
                if (!TurnPosition.PartOfTurn.NORMAL.equals(gameState.getTurnPosition().getPartOfTurn())) {
                    return null;
                }
                break;
            case UP_MP:
                if (!TurnPosition.PartOfTurn.NORMAL.equals(gameState.getTurnPosition().getPartOfTurn())) {
                    return null;
                }
                if (GameUtils.getCurrentUnit(gameState).getStage() != 0) {
                    return null;
                }
                break;
            case UP_STAGE: {
                if (!TurnPosition.PartOfTurn.NORMAL.equals(gameState.getTurnPosition().getPartOfTurn())) {
                    return null;
                }
                Unit unit = GameUtils.getCurrentUnit(gameState);
                if (unit.getStage() == 0 || unit.getMp() < unit.getStage()) {
                    return null;
                }
            }
            break;
            case DOWN_STAGE: {
                if (!TurnPosition.PartOfTurn.NORMAL.equals(gameState.getTurnPosition().getPartOfTurn())) {
                    return null;
                }
                Unit unit = GameUtils.getCurrentUnit(gameState);
                if (unit.getMp() <= unit.getStage()) {
                    return null;
                }
            }
            break;
            case FIRE:
                if (!TurnPosition.PartOfTurn.NORMAL.equals(gameState.getTurnPosition().getPartOfTurn())) {
                    return null;
                }
                if (GameUtils.getCurrentUnit(gameState).getMp() < 3) {
                    return null;
                }
                if (GameUtils.hasOtherPlayerUnitWithIdex(gameState, action.getUnitIndex())) {
                    return null;
                }
                if (GameUtils.getCurrentUnit(gameState).getStage() < GameUtils.getOtherPlayer(gameState).getUnitsList().get(action.getUnitIndex()).getStage()) {
                    return null;
                }
                break;
            case CHOOSE_TRIBUNAL:
                if (!TurnPosition.PartOfTurn.BEFORE_TRIBUNAL.equals(gameState.getTurnPosition().getPartOfTurn())) {
                    return null;
                }
                if (GameUtils.getCurrentPlayer(gameState).getEvidences() < GameConsts.TRIBUNAL_POINTS_OF_ONE) {
                    return null;
                }
                break;
            case SEND_TRIBUNAL_UNIT_POINTS:
                if (!TurnPosition.PartOfTurn.TRIBUNAL_POINTS.equals(gameState.getTurnPosition().getPartOfTurn())) {
                    return null;
                }
                if (action.getPointsList() == null) {
                    return null;
                }
                if (GameUtils.getOtherPlayer(gameState).getUnitsList().size() != action.getPointsList().size()) {
                    return null;
                }
                int sum = 0;
                for (int value : action.getPointsList()) {
                    if (value < 0) {
                        return null;
                    }
                    sum += value;
                }

                if (sum != GameUtils.getOtherPlayer(gameState).getUnitsList().size() * GameConsts.TRIBUNAL_POINTS_OF_ONE) {
                    return null;
                }
                break;
            case TRIBUNAL_KILL:
                if (!TurnPosition.PartOfTurn.TRIBUNAL_KILL.equals(gameState.getTurnPosition().getPartOfTurn())) {
                    return null;
                }
                if (GameUtils.hasOtherPlayerUnitWithIdex(gameState, action.getUnitIndex())) {
                    return null;
                }
                if (GameUtils.getCurrentPlayer(gameState).getEvidences() < GameUtils.getOtherPlayer(gameState).getUnitsList().get(action.getUnitIndex()).getTribunalPoints()) {
                    return null;
                }
                break;
            case NOTHING:
                EnumSet normalParts = EnumSet.of(PartOfTurn.NORMAL, PartOfTurn.BEFORE_TRIBUNAL);
                if (!normalParts.contains(gameState.getTurnPosition().getPartOfTurn())) {
                    return null;
                }
                break;
            default:
                return null;
        }
        //deep clone
        this.gameState = GameJsonParser.fromJsonToGame(GameJsonParser.fromGameToJson(gameState, null));
        doAct(action, playerIndex);
        turnTrigger(action, playerIndex);
        return this.gameState;
    }

    private void doAct(Action action, int playerIndex) {
        Unit current = GameUtils.getCurrentUnit(gameState);
        switch (action.getAction()) {
            case NEW_UNIT:
                current.setMp(current.getMp() - UnitsCalculator.getMpForNewUnit(GameUtils.getCurrentPlayer(gameState).getUnitsList()));
                GameUtils.getCurrentPlayer(gameState).getUnitsList().add(0, UnitsFactory.createNewUnit());
                gameState.getTurnPosition().setUnitIndex(gameState.getTurnPosition().getUnitIndex() + 1);
                break;
            case UP_XP:
                current.setXp(current.getXp() + current.getStage() + 1);
                Player other = GameUtils.getOtherPlayer(gameState);
                other.setEvidences(other.getEvidences()
                        + EvidencesCalculator.caclulateXpEvidences(GameUtils.getCurrentPlayer(gameState).getUnitsList(), gameState.getTurnPosition().getUnitIndex(), other.getUnitsList()));
                break;
            case UP_MP:
                current.setMp(current.getMp() + LevelCalculator.getLevelByExp(current.getXp()));
                break;
            case UP_STAGE:
                current.setMp(current.getMp() - current.getStage());
                current.setStage(current.getStage() - 1);
                break;
            case DOWN_STAGE:
                current.setStage(current.getStage() + 1);
                current.setMp(current.getMp() - current.getStage());
                break;
            case FIRE:
                current.setMp(current.getMp() - 3);
                other = GameUtils.getOtherPlayer(gameState);
                other.setEvidences(other.getEvidences()
                        + EvidencesCalculator.caclulateEvidences(GameUtils.getCurrentPlayer(gameState).getUnitsList(), gameState.getTurnPosition().getUnitIndex(), other.getUnitsList(), action.getUnitIndex()));
                Unit u = GameUtils.getOtherPlayer(gameState).getUnitsList().get(action.getUnitIndex());
                if (u.getHp() == 1) {
                    GameUtils.getOtherPlayer(gameState).getUnitsList().remove(action.getUnitIndex());
                } else {
                    u.setHp(u.getHp() - 1);
                }
                break;
            case CHOOSE_TRIBUNAL:
                break;
            case SEND_TRIBUNAL_UNIT_POINTS:
                for (int i = 0; i < action.getPointsList().size(); i++) {
                    GameUtils.getOtherPlayer(gameState).getUnitsList().get(i).setTribunalPoints(action.getPointsList().get(i));
                }
                break;
            case TRIBUNAL_KILL:
                GameUtils.getCurrentPlayer(gameState).setEvidences(GameUtils.getCurrentPlayer(gameState).getEvidences() - GameUtils.getOtherPlayer(gameState).getUnitsList().get(action.getUnitIndex()).getTribunalPoints());
                GameUtils.getOtherPlayer(gameState).getUnitsList().remove(action.getUnitIndex());
                break;
            case NOTHING:
                break;
            default:
                throw new AssertionError(action.getAction().name());
        }
    }

    private void turnTrigger(Action action, int playerIndex) {
        if (calcGameFinished()) {
            gameState.setFinished(true);
            return;
        }
        switch (gameState.getTurnPosition().getPartOfTurn()) {
            case NORMAL:
                Unit current = GameUtils.getCurrentUnit(gameState);
                if (current.getStage() > 0 && !EnumSet.of(ActionType.DOWN_STAGE, ActionType.UP_STAGE).contains(action.getAction())) {
                    if (current.getStage() > current.getMp()) {
                        if (current.getHp() < 2) {
                            GameUtils.getCurrentPlayer(gameState).getUnitsList().remove(current);
                        } else {
                            current.setHp(current.getHp() - 1);
                            current.setMp(0);
                            current.setStage(0);
                        }
                    } else {
                        current.setMp(current.getMp() - current.getStage());
                    }
                }
                if (!nextUnitOrFalse()) {
                    if (GameUtils.getCurrentPlayer(gameState).getEvidences() < GameConsts.TRIBUNAL_POINTS_OF_ONE) {
                        nextPlayer();
                    } else {
                        gameState.getTurnPosition().setPartOfTurn(TurnPosition.PartOfTurn.BEFORE_TRIBUNAL);
                    }
                }
                break;
            case BEFORE_TRIBUNAL:
                if (ActionType.NOTHING.equals(action.getAction())) {
                    nextPlayer();
                } else {
                    gameState.getTurnPosition().setPartOfTurn(TurnPosition.PartOfTurn.TRIBUNAL_POINTS);
                }
                break;
            case TRIBUNAL_POINTS:
                gameState.getTurnPosition().setPartOfTurn(TurnPosition.PartOfTurn.TRIBUNAL_KILL);
                break;
            case TRIBUNAL_KILL:
                for (Unit u : GameUtils.getOtherPlayer(gameState).getUnitsList()) {
                    u.setTribunalPoints(null);
                }
                nextPlayer();
                break;
            default:
                throw new AssertionError(gameState.getTurnPosition().getPartOfTurn().name());
        }
        if (calcGameFinished()) {
            gameState.setFinished(true);
            return;
        }
    }

    private boolean nextUnitOrFalse() {
        TurnPosition position = gameState.getTurnPosition();
        if (position.getUnitIndex() < GameUtils.getCurrentPlayer(gameState).getUnitsList().size() - 1) {
            position.setUnitIndex(position.getUnitIndex() + 1);
            return true;
        } else {
            return false;
        }
    }

    private void nextPlayer() {
        TurnPosition position = gameState.getTurnPosition();
        position.setPlayerIndex(GameUtils.getOtherPlayerIndex(position.getPlayerIndex()));
        position.setUnitIndex(0);
        position.setPartOfTurn(PartOfTurn.NORMAL);
    }

    public boolean calcGameFinished() {
        for (Player p : gameState.getPlayers()) {
            if (p.getUnitsList().isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
