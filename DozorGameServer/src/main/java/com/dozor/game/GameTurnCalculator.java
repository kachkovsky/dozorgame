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

    private Game game;

    //false if turn incorret
    public Game calcTurn(Game game, Action action, int playerIndex) throws JSONException {
        if (playerIndex < 0 || playerIndex > 1) {
            return null;
        }

        if ((game.getTurnPosition().getPlayerIndex() != playerIndex) ^ TurnPosition.PartOfTurn.TRIBUNAL_POINTS.equals(game.getTurnPosition().getPartOfTurn())) {
            return null;
        }
        switch (action.getAction()) {
            case NEW_UNIT:
                if (!TurnPosition.PartOfTurn.NORMAL.equals(game.getTurnPosition().getPartOfTurn())) {
                    return null;
                }
                if (GameUtils.getCurrentUnit(game).getMp() < UnitsCalculator.getMpForNewUnit(GameUtils.getCurrentPlayer(game).getUnitsList())) {
                    return null;
                }
                break;
            case UP_XP:
                if (!TurnPosition.PartOfTurn.NORMAL.equals(game.getTurnPosition().getPartOfTurn())) {
                    return null;
                }
                break;
            case UP_MP:
                if (!TurnPosition.PartOfTurn.NORMAL.equals(game.getTurnPosition().getPartOfTurn())) {
                    return null;
                }
                if (GameUtils.getCurrentUnit(game).getStage() != 0) {
                    return null;
                }
                break;
            case UP_STAGE: {
                if (!TurnPosition.PartOfTurn.NORMAL.equals(game.getTurnPosition().getPartOfTurn())) {
                    return null;
                }
                Unit unit = GameUtils.getCurrentUnit(game);
                if (unit.getStage() == 0 || unit.getMp() < unit.getStage()) {
                    return null;
                }
            }
            break;
            case DOWN_STAGE: {
                if (!TurnPosition.PartOfTurn.NORMAL.equals(game.getTurnPosition().getPartOfTurn())) {
                    return null;
                }
                Unit unit = GameUtils.getCurrentUnit(game);
                if (unit.getMp() <= unit.getStage()) {
                    return null;
                }
            }
            break;
            case FIRE:
                if (!TurnPosition.PartOfTurn.NORMAL.equals(game.getTurnPosition().getPartOfTurn())) {
                    return null;
                }
                if (GameUtils.getCurrentUnit(game).getMp() < 3) {
                    return null;
                }
                if (GameUtils.hasOtherPlayerUnitWithIdex(game, action.getUnitIndex())) {
                    return null;
                }
                if (GameUtils.getCurrentUnit(game).getStage() < GameUtils.getOtherPlayer(game).getUnitsList().get(action.getUnitIndex()).getStage()) {
                    return null;
                }
                break;
            case CHOOSE_TRIBUNAL:
                if (!TurnPosition.PartOfTurn.BEFORE_TRIBUNAL.equals(game.getTurnPosition().getPartOfTurn())) {
                    return null;
                }
                if (GameUtils.getCurrentPlayer(game).getEvidences() < GameConsts.TRIBUNAL_POINTS_OF_ONE) {
                    return null;
                }
                break;
            case SEND_TRIBUNAL_UNIT_POINTS:
                if (!TurnPosition.PartOfTurn.TRIBUNAL_POINTS.equals(game.getTurnPosition().getPartOfTurn())) {
                    return null;
                }
                if (action.getPointsList() == null) {
                    return null;
                }
                if (GameUtils.getOtherPlayer(game).getUnitsList().size() != action.getPointsList().size()) {
                    return null;
                }
                int sum = 0;
                for (int value : action.getPointsList()) {
                    if (value < 0) {
                        return null;
                    }
                    sum += value;
                }

                if (sum != GameUtils.getOtherPlayer(game).getUnitsList().size() * GameConsts.TRIBUNAL_POINTS_OF_ONE) {
                    return null;
                }
                break;
            case TRIBUNAL_KILL:
                if (!TurnPosition.PartOfTurn.TRIBUNAL_KILL.equals(game.getTurnPosition().getPartOfTurn())) {
                    return null;
                }
                if (GameUtils.hasOtherPlayerUnitWithIdex(game, action.getUnitIndex())) {
                    return null;
                }
                if (GameUtils.getCurrentPlayer(game).getEvidences() < GameUtils.getOtherPlayer(game).getUnitsList().get(action.getUnitIndex()).getTribunalPoints()) {
                    return null;
                }
                break;
            case NOTHING:
                EnumSet normalParts = EnumSet.of(PartOfTurn.NORMAL, PartOfTurn.BEFORE_TRIBUNAL);
                if (!normalParts.contains(game.getTurnPosition().getPartOfTurn())) {
                    return null;
                }
                break;
            default:
                return null;
        }
        //deep clone
        this.game = GameJsonParser.fromJsonToGame(GameJsonParser.fromGameToJson(game, null));
        doAct(action, playerIndex);
        turnTrigger(action, playerIndex);
        return this.game;
    }

    private void doAct(Action action, int playerIndex) {
        Unit current = GameUtils.getCurrentUnit(game);
        switch (action.getAction()) {
            case NEW_UNIT:
                current.setMp(current.getMp() - UnitsCalculator.getMpForNewUnit(GameUtils.getCurrentPlayer(game).getUnitsList()));
                GameUtils.getCurrentPlayer(game).getUnitsList().add(0, UnitsFactory.createNewUnit());
                game.getTurnPosition().setUnitIndex(game.getTurnPosition().getUnitIndex() + 1);
                break;
            case UP_XP:
                current.setXp(current.getXp() + current.getStage() + 1);
                Player other = GameUtils.getOtherPlayer(game);
                other.setEvidences(other.getEvidences()
                        + EvidencesCalculator.caclulateXpEvidences(GameUtils.getCurrentPlayer(game).getUnitsList(), game.getTurnPosition().getUnitIndex(), other.getUnitsList()));
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
                other = GameUtils.getOtherPlayer(game);
                other.setEvidences(other.getEvidences()
                        + EvidencesCalculator.caclulateEvidences(GameUtils.getCurrentPlayer(game).getUnitsList(), game.getTurnPosition().getUnitIndex(), other.getUnitsList(), action.getUnitIndex()));
                Unit u = GameUtils.getOtherPlayer(game).getUnitsList().get(action.getUnitIndex());
                if (u.getHp() == 1) {
                    GameUtils.getOtherPlayer(game).getUnitsList().remove(action.getUnitIndex());
                } else {
                    u.setHp(u.getHp() - 1);
                }
                break;
            case CHOOSE_TRIBUNAL:
                break;
            case SEND_TRIBUNAL_UNIT_POINTS:
                for (int i = 0; i < action.getPointsList().size(); i++) {
                    GameUtils.getOtherPlayer(game).getUnitsList().get(i).setTribunalPoints(action.getPointsList().get(i));
                }
                break;
            case TRIBUNAL_KILL:
                GameUtils.getCurrentPlayer(game).setEvidences(GameUtils.getCurrentPlayer(game).getEvidences() - GameUtils.getOtherPlayer(game).getUnitsList().get(action.getUnitIndex()).getTribunalPoints());
                GameUtils.getOtherPlayer(game).getUnitsList().remove(action.getUnitIndex());
                break;
            case NOTHING:
                break;
            default:
                throw new AssertionError(action.getAction().name());
        }
    }

    private void turnTrigger(Action action, int playerIndex) {
        if (calcGameFinished()) {
            game.setFinished(true);
            return;
        }
        switch (game.getTurnPosition().getPartOfTurn()) {
            case NORMAL:
                Unit current = GameUtils.getCurrentUnit(game);
                if (current.getStage() > 0 && !EnumSet.of(ActionType.DOWN_STAGE, ActionType.UP_STAGE).contains(action.getAction())) {
                    if (current.getStage() > current.getMp()) {
                        if (current.getHp() < 2) {
                            GameUtils.getCurrentPlayer(game).getUnitsList().remove(current);
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
                    if (GameUtils.getCurrentPlayer(game).getEvidences() < GameConsts.TRIBUNAL_POINTS_OF_ONE) {
                        nextPlayer();
                    } else {
                        game.getTurnPosition().setPartOfTurn(TurnPosition.PartOfTurn.BEFORE_TRIBUNAL);
                    }
                }
                break;
            case BEFORE_TRIBUNAL:
                if (ActionType.NOTHING.equals(action.getAction())) {
                    nextPlayer();
                } else {
                    game.getTurnPosition().setPartOfTurn(TurnPosition.PartOfTurn.TRIBUNAL_POINTS);
                }
                break;
            case TRIBUNAL_POINTS:
                game.getTurnPosition().setPartOfTurn(TurnPosition.PartOfTurn.TRIBUNAL_KILL);
                break;
            case TRIBUNAL_KILL:
                for (Unit u : GameUtils.getOtherPlayer(game).getUnitsList()) {
                    u.setTribunalPoints(null);
                }
                nextPlayer();
                break;
            default:
                throw new AssertionError(game.getTurnPosition().getPartOfTurn().name());
        }
        if (calcGameFinished()) {
            game.setFinished(true);
            return;
        }
    }

    private boolean nextUnitOrFalse() {
        TurnPosition position = game.getTurnPosition();
        if (position.getUnitIndex() < GameUtils.getCurrentPlayer(game).getUnitsList().size() - 1) {
            position.setUnitIndex(position.getUnitIndex() + 1);
            return true;
        } else {
            return false;
        }
    }

    private void nextPlayer() {
        TurnPosition position = game.getTurnPosition();
        position.setPlayerIndex(GameUtils.getOtherPlayerIndex(position.getPlayerIndex()));
        position.setUnitIndex(0);
        position.setPartOfTurn(PartOfTurn.NORMAL);
    }

    public boolean calcGameFinished() {
        for (Player p : game.getPlayers()) {
            if (p.getUnitsList().isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
