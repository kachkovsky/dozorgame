package com.dozor.main;

import com.dozorengine.server.gamecontroller.GameImplFactory;
import com.dozorengine.server.socket.ServerStarter;

/**
 * @author IGOR-K
 */
public class DozorServerGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        PrintStream st;
//        try {
//            st = new PrintStream(new FileOutputStream("output.txt"));
//            System.setErr(st);
//            System.setOut(st);
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(DozorServerGame.class.getName()).log(Level.SEVERE, null, ex);
//        }


//        try {
//            //Logger.getGlobal().addHandler(new FileHandler("logFile.log", 10 * 1024 * 1024, 10, true));
//        } catch (IOException ex) {
//            Logger.getLogger(DozorServerGame.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SecurityException ex) {
//            Logger.getLogger(DozorServerGame.class.getName()).log(Level.SEVERE, null, ex);
//        }
        GameImplFactory.getInstance().setGameImplCreator(new GameCreator());
        ServerStarter.startServer();
    }

}
