package com.dozor.game.utils;

import com.dozor.game.beans.Unit;

import java.util.List;

/**
 * @author IGOR-K
 */
public class EvidencesCalculator {

    public static int caclulateEvidences(List<Unit> yourUnits, int killer, List<Unit> theirUnits, int victim) {
        int killerStage = yourUnits.get(killer).getStage();
        int evidences = 0;
        for (int i = 0; i < theirUnits.size(); i++) {
            Unit u = theirUnits.get(i);
            if (u.getStage() < killerStage) {
                continue;
            }
            if (victim == i && u.getHp() == 1) {
                continue;
            }
            evidences += 5;
        }
        return evidences;
    }

    public static int caclulateXpEvidences(List<Unit> yourUnits, int farmer, List<Unit> theirUnits) {
        int farmerStage = yourUnits.get(farmer).getStage();
        int evidences = 0;
        for (int i = 0; i < theirUnits.size(); i++) {
            Unit u = theirUnits.get(i);
            if (u.getStage() < farmerStage) {
                continue;
            }
            evidences += 1;
        }
        return evidences;
    }
}
