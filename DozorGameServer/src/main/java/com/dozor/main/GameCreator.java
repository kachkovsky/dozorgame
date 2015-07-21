package com.dozor.main;

import com.dozor.game.GameControllerAndParser;
import com.dozorengine.server.Session;
import com.dozorengine.server.gamecontroller.GameDataReceiver;
import com.dozorengine.server.gamecontroller.GameImplCreator;

/**
 * @author IGOR-K
 */
public class GameCreator implements GameImplCreator {

    @Override
    public GameDataReceiver createGameDataReceiver(Session session) {
        return new GameControllerAndParser(session);
    }

}
