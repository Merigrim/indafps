package se.kth.csc.indafps;

import org.newdawn.slick.opengl.Texture;

/**
 * A simple image which can be rendered onto the screen.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public class Image extends Widget {
    // The texture to render
    private Texture texture;

    /**
     * Sets this image's texture.
     * 
     * @param texture The new texture to use
     */
    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    /**
     * Returns this image's texture.
     * 
     * @return This image's texture
     */
    public Texture getTexture() {
        return texture;
    }

    @Override
    public void update(float dt) {
        // TODO Auto-generated method stub
    }

    @Override
    public void render(Renderer renderer) {
        // TODO Auto-generated method stub
    }

    @Override
    public void handleInput() {
        // TODO Auto-generated method stub
    }
}
