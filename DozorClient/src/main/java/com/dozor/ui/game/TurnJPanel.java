package com.dozor.ui.game;

import com.dozor.game.beans.GameState;
import com.dozor.game.beans.TurnPosition;
import com.dozor.game.beans.TurnPosition.PartOfTurn;
import com.dozor.langs.LocaleBundle;
import com.dozor.ui.DozorClient;
import com.dozor.ui.send.GameEventsSender;
import javax.swing.SwingUtilities;

/**
 *
 * @author IGOR-K
 */
public class TurnJPanel extends javax.swing.JPanel {

    /**
     * Creates new form TurnJPanel
     */
    public TurnJPanel() {
        initComponents();
        jButtonNewUnit.setText(LocaleBundle.getInstance().getString("NewUnit"));
        jButtonManaUp.setText(LocaleBundle.getInstance().getString("ManaUp"));
        jButtonXpUp.setText(LocaleBundle.getInstance().getString("XpUp"));
        jButtonFire.setText(LocaleBundle.getInstance().getString("Fire"));
        jButtonTribunal.setText(LocaleBundle.getInstance().getString("Tribunal"));
        jButtonSkip.setText(LocaleBundle.getInstance().getString("Skip"));
    }

    private GameState gameState;

    public void showByGame(GameState gameState, int currentPlayerIndex) {
        this.gameState = gameState;
        hideAll();
        if (gameState.getTurnPosition().getPlayerIndex() == currentPlayerIndex) {
            TurnPosition.PartOfTurn partOfTurn = gameState.getTurnPosition().getPartOfTurn();
            switch (partOfTurn) {
                case NORMAL:
                    jButtonFire.setEnabled(true);
                    jButtonManaUp.setEnabled(true);
                    jButtonNewUnit.setEnabled(true);
                    jButtonXpUp.setEnabled(true);
                    upDownJPanel1.setEnabled(true);
                    jButtonSkip.setEnabled(true);
                    break;
                case BEFORE_TRIBUNAL:
                    jButtonSkip.setEnabled(true);
                    jButtonTribunal.setEnabled(true);
                    break;
                case TRIBUNAL_POINTS:
                    //jButtonTribunal.setEnabled(true);
                    break;
                case TRIBUNAL_KILL:
                    break;
                default:
                    throw new AssertionError(partOfTurn.name());
            }
        } else if (PartOfTurn.TRIBUNAL_POINTS.equals(gameState.getTurnPosition().getPartOfTurn())) {
            jButtonTribunal.setEnabled(true);
        }
    }

    public void hideAll() {
        jButtonFire.setEnabled(false);
        jButtonManaUp.setEnabled(false);
        jButtonNewUnit.setEnabled(false);
        jButtonXpUp.setEnabled(false);
        upDownJPanel1.setEnabled(false);
        jButtonTribunal.setEnabled(false);
        jButtonSkip.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        upDownJPanel1 = new com.dozor.ui.game.UpDownJPanel();
        jButtonNewUnit = new javax.swing.JButton();
        jButtonManaUp = new javax.swing.JButton();
        jButtonXpUp = new javax.swing.JButton();
        jButtonFire = new javax.swing.JButton();
        jButtonTribunal = new javax.swing.JButton();
        jButtonSkip = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        jButtonNewUnit.setText("Нов. юнит");
        jButtonNewUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewUnitActionPerformed(evt);
            }
        });

        jButtonManaUp.setText("Ув. ману");
        jButtonManaUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonManaUpActionPerformed(evt);
            }
        });

        jButtonXpUp.setText("Ув. опыт");
        jButtonXpUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonXpUpActionPerformed(evt);
            }
        });

        jButtonFire.setText("Огонь");
        jButtonFire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFireActionPerformed(evt);
            }
        });

        jButtonTribunal.setText("Трибунал");
        jButtonTribunal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTribunalActionPerformed(evt);
            }
        });

        jButtonSkip.setText("Пропуск");
        jButtonSkip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSkipActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(upDownJPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonManaUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonNewUnit, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonFire, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                            .addComponent(jButtonXpUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonTribunal, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                            .addComponent(jButtonSkip, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(upDownJPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButtonXpUp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonManaUp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonTribunal, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonNewUnit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonFire, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonSkip, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonXpUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonXpUpActionPerformed
        GameEventsSender.getInstance().upXp();
    }//GEN-LAST:event_jButtonXpUpActionPerformed

    private void jButtonManaUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonManaUpActionPerformed

        GameEventsSender.getInstance().upMp();

    }//GEN-LAST:event_jButtonManaUpActionPerformed

    private void jButtonNewUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewUnitActionPerformed
        GameEventsSender.getInstance().createUnit();
    }//GEN-LAST:event_jButtonNewUnitActionPerformed

    private void jButtonTribunalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTribunalActionPerformed

        switch (gameState.getTurnPosition().getPartOfTurn()) {
            case NORMAL:
                break;
            case BEFORE_TRIBUNAL:
                GameEventsSender.getInstance().sendStartTribunal();
                break;
            case TRIBUNAL_POINTS:
                DozorClient topFrame = (DozorClient) SwingUtilities.getWindowAncestor(this);
                topFrame.setSendPointsFromPanels();
                break;
            case TRIBUNAL_KILL:
                break;
            default:
                throw new AssertionError(gameState.getTurnPosition().getPartOfTurn().name());
        }

    }//GEN-LAST:event_jButtonTribunalActionPerformed

    private void jButtonSkipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSkipActionPerformed
        GameEventsSender.getInstance().nothing();
    }//GEN-LAST:event_jButtonSkipActionPerformed

    private void jButtonFireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFireActionPerformed
        DozorClient topFrame = (DozorClient) SwingUtilities.getWindowAncestor(this);
        topFrame.setPanelsFireable();
    }//GEN-LAST:event_jButtonFireActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonFire;
    private javax.swing.JButton jButtonManaUp;
    private javax.swing.JButton jButtonNewUnit;
    private javax.swing.JButton jButtonSkip;
    private javax.swing.JButton jButtonTribunal;
    private javax.swing.JButton jButtonXpUp;
    private javax.swing.JPanel jPanel1;
    private com.dozor.ui.game.UpDownJPanel upDownJPanel1;
    // End of variables declaration//GEN-END:variables
}
