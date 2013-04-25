package se.kth.csc.indafps;

import java.io.IOException;

/**
 * The main game state. Handles the level which in turn handles all game
 * objects and their interactions.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public class GameState extends State {
    // The current game level
    private Level level;
    
    // The heads-up display, which shows info about health, etc.
    private Hud hud;
    
    private void init(String levelFilename) {
        level = new Level();
        try {
            level.importLevel(levelFilename);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        hud = new Hud(null);
        // TODO
    }
    
    @Override
    public void update(float dt) {
        // TODO Auto-generated method stub
    }

    @Override
    public void render() {
        // TODO Auto-generated method stub
    }

    @Override
    public void handleInput() {
        // TODO Auto-generated method stub
    }
}
