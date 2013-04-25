package se.kth.csc.indafps;

/**
 * Entry point for the game.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public class Program {
    /**
     * The main function. This is called when the game starts up and is
     * responsible for initializing the engine object.
     * 
     * @param args The command line arguments passed to the program
     */
    public static void main(String[] args) {
        Engine engine = new Engine();
        engine.run();
    }
}
