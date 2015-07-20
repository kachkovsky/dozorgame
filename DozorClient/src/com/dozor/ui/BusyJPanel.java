/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dozor.ui;

import com.dozor.langs.LocaleBundle;
import com.dozorengine.serverinteraction.bean.SessionResultBean;
import com.dozor.utils.PlayerColorUtils;

/**
 *
 * @author IGOR-K
 */
public class BusyJPanel extends javax.swing.JPanel {

    /**
     * Creates new form BusyJPanel2
     */
    public BusyJPanel() {
        initComponents();
        jLabelColor.setText(LocaleBundle.getInstance().getString("color_"));
        jLabelNick.setText(LocaleBundle.getInstance().getString("nick_"));
        jLabelSession.setText(LocaleBundle.getInstance().getString("session_number_"));

    }

    public void clearText() {
        jLabelColorText.setText("");
        jLabelNickText.setText("");
        jLabelSessionText.setText("");
    }

    public void setSessionResultBean(SessionResultBean sessionFromJson) {
        jLabelColorText.setText("" + PlayerColorUtils.getColorByIndex(sessionFromJson.getSessionPlayerIndex()));
        if(sessionFromJson.getSessionPlayerIndex()==-1){
            jLabelNickText.setText(LocaleBundle.getInstance().getString("spectator"));
        } else  {
            jLabelNickText.setText("" + sessionFromJson.getNicks().get(sessionFromJson.getSessionPlayerIndex()));
        }
        jLabelSessionText.setText("" + sessionFromJson.getSessionNumber());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelWaiting = new javax.swing.JLabel();
        jLabelSession = new javax.swing.JLabel();
        jLabelSessionText = new javax.swing.JLabel();
        jLabelNick = new javax.swing.JLabel();
        jLabelNickText = new javax.swing.JLabel();
        jLabelColor = new javax.swing.JLabel();
        jLabelColorText = new javax.swing.JLabel();

        jLabelWaiting.setText("Waiting ...");

        jLabelSession.setText("jLabel2");

        jLabelSessionText.setText("jLabel1");

        jLabelNick.setText("jLabel2");

        jLabelNickText.setText("jLabel3");

        jLabelColor.setText("jLabel4");

        jLabelColorText.setText("jLabel5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(jLabelWaiting))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNick)
                            .addComponent(jLabelColor)
                            .addComponent(jLabelSession))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNickText)
                            .addComponent(jLabelColorText)
                            .addComponent(jLabelSessionText))))
                .addContainerGap(200, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSession)
                    .addComponent(jLabelSessionText))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNick)
                    .addComponent(jLabelNickText))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelColor)
                    .addComponent(jLabelColorText))
                .addGap(57, 57, 57)
                .addComponent(jLabelWaiting)
                .addContainerGap(164, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelColor;
    private javax.swing.JLabel jLabelColorText;
    private javax.swing.JLabel jLabelNick;
    private javax.swing.JLabel jLabelNickText;
    private javax.swing.JLabel jLabelSession;
    private javax.swing.JLabel jLabelSessionText;
    private javax.swing.JLabel jLabelWaiting;
    // End of variables declaration//GEN-END:variables
}
