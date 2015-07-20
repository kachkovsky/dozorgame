package com.dozor.ui;

import com.dozor.game.bean.parser.GameJsonParser;
import com.dozor.game.beans.Game;
import com.dozor.langs.LocaleBundle;
import com.dozorengine.serverinteraction.StringSocketClient;
import com.dozor.serverinteraction.bean.Errors;
import com.dozor.serverinteraction.bean.ResponseTypes;
import com.dozorengine.serverinteraction.bean.SessionResultBean;
import com.dozor.serverinteraction.parsers.JsonToGameResultBeanParser;
import com.dozor.serverinteraction.parsers.ResultSessionBeanToParser;
import com.dozor.ui.send.GameEventsSender;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Font;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import org.json.JSONObject;

/**
 *
 * @author IGOR-K
 */
public class DozorClient extends javax.swing.JFrame {

    private StringSocketClient socket = null;

    private volatile boolean finished = false;

    /**
     * Creates new form MainFrame
     */
    public DozorClient() {
        initComponents();
        loginPanel.setCallback(new LoginPanel.Callback() {

            @Override
            public void onSocketFirstDataSent(StringSocketClient socket) {
                DozorClient.this.socket = socket;
                showProgress();
                Thread t;
                t = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        boolean firstReceive = true;
                        GameEventsSender.changeSender(socket);
                        while (!finished && !socket.isClosed()) {
                            try {
                                String receiveString = socket.receiveString();
                                JSONObject object = new JSONObject(receiveString);
                                ResponseTypes responseType = JsonToGameResultBeanParser.getResponseType(object);
                                if (responseType != null) {
                                    Errors errors = JsonToGameResultBeanParser.getErrors(object);
                                    Dialogs.showServErrDialogIfNeeded(DozorClient.this, errors);
                                    switch (responseType) {
                                        case WAITING_FOR_USERS:
                                            showProgress();
                                            SessionResultBean result = ResultSessionBeanToParser.resultSessionFromJson(object);
                                            if(result.getSessionPlayerIndex()>1){
                                                result.setSessionPlayerIndex(-1);
                                            }
                                            busyPanel.setSessionResultBean(result);
                                            gamePanel.setSession(result.getSessionNumber());
                                            gamePanel.setCurrentPlayerIndex(result.getSessionPlayerIndex());
                                            break;
                                        case TURN:
                                            if (firstReceive) {
                                                firstReceive = false;
                                                DozorClient.this.setExtendedState(DozorClient.this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
                                            }
                                            Game game = GameJsonParser.fromJsonToGame(object);
                                            if (game.isFinished()) {
                                                showFinishedDialog(LocaleBundle.getInstance().getString("info_game_finish"));
                                                socket.closeAll();
                                                System.exit(0);
                                            } else {
                                                gamePanel.setGame(game);
                                                showGame();
                                            }
                                            break;
                                        default:
                                            throw new AssertionError(responseType.name());
                                    }
                                } else {
                                    Dialogs.showServErrDialog(DozorClient.this, JsonToGameResultBeanParser.getErrors(object));
                                }
                                //busyPanel.setToolTipText(receiveString);

                                //TODO: startPlay
                                //receiveString = socket.receiveString();
                                //busyPanel.setToolTipText(receiveString);
                            } catch (Exception ex) {
                                Logger.getLogger(DozorClient.class.getName()).log(Level.SEVERE, null, ex);
                                Dialogs.showServErrDialog(DozorClient.this, ex.toString());
                            }
                        }
                        socket.closeAll();
                    }
                });
                t.start();
            }
        });
    }

    public void setPanelsFireable() {
        gamePanel.setFireable();
    }

    public void setSendPointsFromPanels() {
        gamePanel.setSendPointsFromPanels();
    }

    private void initComponents() {
        setTitle(LocaleBundle.getInstance().getString("title"));
        loginPanel = new com.dozor.ui.LoginPanel();
        busyPanel = new com.dozor.ui.BusyJPanel();
        gamePanel = new com.dozor.ui.game.GamePanel();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());
        getContentPane().add(loginPanel, "loginPanel");
        getContentPane().add(busyPanel, "busyPanel");
        getContentPane().add(new JScrollPane(gamePanel), "gameScrollPane");
        pack();
    }

    public void showProgress() {
        Container contentPane = getContentPane();
        CardLayout cl = (CardLayout) (contentPane.getLayout());
        cl.show(contentPane, "busyPanel");
        //gamePanel.setVisible(false);
        //loginPanel.setVisible(false);
        busyPanel.clearText();
//        busyPanel.setVisible(true);
    }

    public void showGame() {
        Container contentPane = getContentPane();
        CardLayout cl = (CardLayout) (contentPane.getLayout());
        cl.show(contentPane, "gameScrollPane");
//        busyPanel.setVisible(false);
//        loginPanel.setVisible(false);
//        gamePanel.setVisible(true);
    }

    public void showFinishedDialog(String text) {
        JOptionPane.showMessageDialog(this,
                text,
                LocaleBundle.getInstance().getString("info_game"),
                JOptionPane.ERROR_MESSAGE);
    }

    public static void setApplicationFont(float size)//just changes the font size
    {
        Enumeration enumer = UIManager.getDefaults().keys();
        while (enumer.hasMoreElements()) {
            Object key = enumer.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof Font) {
                UIManager.put(key, new javax.swing.plaf.FontUIResource(((Font) value).deriveFont(size)));
            }
        }
    }

    public static void main(String args[]) {
        try {

            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    setApplicationFont(16.f);
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DozorClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DozorClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DozorClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DozorClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DozorClient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private com.dozor.ui.LoginPanel loginPanel;
    private com.dozor.ui.BusyJPanel busyPanel;
    private com.dozor.ui.game.GamePanel gamePanel;
    // End of variables declaration                   
}
