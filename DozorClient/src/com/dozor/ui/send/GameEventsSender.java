package com.dozor.ui.send;

import com.dozor.game.beans.action.Action;
import com.dozor.langs.LocaleBundle;
import com.dozor.serverinteraction.parsers.FromActionToJsonParser;
import com.dozorengine.serverinteraction.StringSocketClient;
import org.json.JSONException;

import javax.swing.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author IGOR-K
 */
public class GameEventsSender {

    private static GameEventsSender SENDER;

    public static void changeSender(StringSocketClient socket) {
        SENDER = new GameEventsSender(socket);
    }

    public static GameEventsSender getInstance() {
        return SENDER;
    }

    private StringSocketClient socket;

    private GameEventsSender(StringSocketClient socket) {
        this.socket = socket;
    }

    public void upMp() {
        clearActionCache();
        Action a = new Action();
        a.setAction(Action.ActionType.UP_MP);
        send(a);
    }

    public void upXp() {
        clearActionCache();
        Action a = new Action();
        a.setAction(Action.ActionType.UP_XP);
        send(a);
    }

    public void createUnit() {
        clearActionCache();
        Action a = new Action();
        a.setAction(Action.ActionType.NEW_UNIT);
        send(a);
    }

    public void upStage() {
        clearActionCache();
        Action a = new Action();
        a.setAction(Action.ActionType.UP_STAGE);
        send(a);
    }

    public void downStage() {
        clearActionCache();
        Action a = new Action();
        a.setAction(Action.ActionType.DOWN_STAGE);
        send(a);
    }

    public void nothing() {
        clearActionCache();
        Action a = new Action();
        a.setAction(Action.ActionType.NOTHING);
        send(a);
    }

    public void fire(int unitIndex) {
        clearActionCache();
        Action a = new Action();
        a.setAction(Action.ActionType.FIRE);
        a.setUnitIndex(unitIndex);
        send(a);
    }

    public void sendStartTribunal() {
        clearActionCache();
        Action a = new Action();
        a.setAction(Action.ActionType.CHOOSE_TRIBUNAL);
        send(a);
    }

    public void sendTribunalPoints(List<Integer> points) {
        clearActionCache();
        Action a = new Action();
        a.setAction(Action.ActionType.SEND_TRIBUNAL_UNIT_POINTS);
        a.setPointsList(points);
        send(a);
    }

    public void sendTribunalKill(int unitIndex) {
        clearActionCache();
        Action a = new Action();
        a.setAction(Action.ActionType.TRIBUNAL_KILL);
        a.setUnitIndex(unitIndex);
        send(a);
    }

    private void clearActionCache() {
    }

    private void send(Action a) {
        try {
            socket.sendString(FromActionToJsonParser.fromActionToJson(a).toString());
        } catch (JSONException ex) {
            Logger.getLogger(GameEventsSender.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                    LocaleBundle.getInstance().getString("serv_send_error"),
                    LocaleBundle.getInstance().getString("serv_error"),
                    JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }

}
