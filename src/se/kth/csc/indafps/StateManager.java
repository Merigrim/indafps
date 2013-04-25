package se.kth.csc.indafps;

import java.util.Stack;

/**
 * The StateManager class holds a stack of states which have been pushed to the
 * manager. Only the current state is active at one time, but by maintaining
 * a stack, it is easy to backtrack through states. For example, this can be 
 * used for a chain of menus.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public class StateManager implements GameComponent {
    // The state stack. The topmost state is the currently active one
    private Stack<State> states;
    
    public StateManager() {
        states = new Stack<State>();
        // TODO Possible additional constructor work
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
