package se.kth.csc.indafps;

/**
 * A simple square 4x4 matrix class.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public class Mat4 extends Mat {
    public Mat4() {
        super.init(4);
    }

    /**
     * An ugly hack, but we don't want to change the dimensions of this matrix.
     * 
     * @param n The new width/height (ignored)
     */
    @Override
    public void init(int n) {
        return;
    }
}
