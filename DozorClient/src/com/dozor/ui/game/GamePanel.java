package com.dozor.ui.game;

import com.dozor.game.beans.Game;
import com.dozor.game.beans.GameUtils;
import com.dozor.game.beans.TurnPosition;
import com.dozor.langs.LocaleBundle;
import com.dozor.utils.PlayerColorUtils;
import java.awt.BorderLayout;

/**
 *
 * @author IGOR-K
 */
public class GamePanel extends javax.swing.JPanel {

    /**
     * Creates new form GamePanel
     */
    public GamePanel() {
        //initComponents();
        initZeroComponents();
    }

    private Game game;
    private int session;
    private int currentPlayerIndex;

    public void setSession(int session) {
        this.session = session;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

    public void setGame(Game game) {
        this.game = game;

        jLabelInfo.setText(String.format(LocaleBundle.getInstance().getString("game_info"),
                session,
                PlayerColorUtils.getColorByIndex(currentPlayerIndex),
                game.getPlayers().get(game.getPosition().getPlayerIndex()).getNick(),
                game.getPosition().getPartOfTurn().name()));
        playerJPanel1.setPlayer(game, 0);
        playerJPanel2.setPlayer(game, 1);
        turnJPanel1.showByGame(game, currentPlayerIndex);
        if(game.getPosition().getPlayerIndex()==currentPlayerIndex){
            if(TurnPosition.PartOfTurn.TRIBUNAL_KILL.equals(game.getPosition().getPartOfTurn())){
                setTribunalableUnits();
            }
        }
    }

    public void setFireable() {
        int stage = GameUtils.getCurrentUnit(game).getStage();
        if (game.getPosition().getPlayerIndex() == 0) {
            playerJPanel2.setFireable(stage);
        } else {
            playerJPanel1.setFireable(stage);
        }
    }

    public void setSendPointsFromPanels(){
        if (game.getPosition().getPlayerIndex() == 0) {
            playerJPanel2.setSendPointsFromPanels(GameUtils.getOtherPlayer(game).getUnitsList().size());
        } else {
            playerJPanel1.setSendPointsFromPanels(GameUtils.getOtherPlayer(game).getUnitsList().size());
        }
    }
    
    public void setTribunalableUnits() {
        if (game.getPosition().getPlayerIndex() == 0) {
            playerJPanel2.setTribunalableUnits();
        } else {
            playerJPanel1.setTribunalableUnits();
        }
    }
    
    private void initZeroComponents() {
        jPanelInfo = new javax.swing.JPanel();
        jLabelInfo = new javax.swing.JLabel();
        jPanelInfo.setLayout(new BorderLayout());
        jPanelInfo.add(jLabelInfo);

        playerJPanel1 = new com.dozor.ui.game.PlayerJPanel();
        playerJPanel2 = new com.dozor.ui.game.PlayerJPanel();
        turnJPanel1 = new com.dozor.ui.game.TurnJPanel();
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));
        add(jPanelInfo);
        add(playerJPanel1);
        add(playerJPanel2);
        add(turnJPanel1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelInfo = new javax.swing.JPanel();
        jLabelInfo = new javax.swing.JLabel();
        playerJPanel1 = new com.dozor.ui.game.PlayerJPanel();
        playerJPanel2 = new com.dozor.ui.game.PlayerJPanel();
        turnJPanel1 = new com.dozor.ui.game.TurnJPanel();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

        jLabelInfo.setText(" ");
        jLabelInfo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanelInfoLayout = new javax.swing.GroupLayout(jPanelInfo);
        jPanelInfo.setLayout(jPanelInfoLayout);
        jPanelInfoLayout.setHorizontalGroup(
            jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelInfoLayout.setVerticalGroup(
            jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelInfo)
                .addContainerGap(89, Short.MAX_VALUE))
        );

        add(jPanelInfo);

        javax.swing.GroupLayout playerJPanel1Layout = new javax.swing.GroupLayout(playerJPanel1);
        playerJPanel1.setLayout(playerJPanel1Layout);
        playerJPanel1Layout.setHorizontalGroup(
            playerJPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 503, Short.MAX_VALUE)
        );
        playerJPanel1Layout.setVerticalGroup(
            playerJPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 162, Short.MAX_VALUE)
        );

        add(playerJPanel1);

        javax.swing.GroupLayout playerJPanel2Layout = new javax.swing.GroupLayout(playerJPanel2);
        playerJPanel2.setLayout(playerJPanel2Layout);
        playerJPanel2Layout.setHorizontalGroup(
            playerJPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 503, Short.MAX_VALUE)
        );
        playerJPanel2Layout.setVerticalGroup(
            playerJPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 219, Short.MAX_VALUE)
        );

        add(playerJPanel2);
        add(turnJPanel1);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelInfo;
    private javax.swing.JPanel jPanelInfo;
    private com.dozor.ui.game.PlayerJPanel playerJPanel1;
    private com.dozor.ui.game.PlayerJPanel playerJPanel2;
    private com.dozor.ui.game.TurnJPanel turnJPanel1;
    // End of variables declaration//GEN-END:variables
}