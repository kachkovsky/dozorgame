package com.dozor.ui.game;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author IGOR-K
 */
public class JPanelLevel extends JPanel {

    private int level;
    private Color color;

    public void setLevelAndColor(int level, Color color) {
        this.level = level;
        this.color = color;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        int len = 40;
        int flen = 50;
        g.setColor(color);
        for (int i = 0; i < level; i++) {
            g.fillOval(flen * (i / 2) + 5, flen * (i % 2) + 5, len, len);
        }
    }

}
