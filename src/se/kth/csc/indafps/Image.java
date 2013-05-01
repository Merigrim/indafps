package se.kth.csc.indafps;

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
    // The image's position
    private Vec2 position;

    public Image() {
        rect = new Rect(0, 0, 0, 0);
    }

    /**
     * Sets this image's texture.
     * 
     * @param texture The new texture to use
     */
    public void setTexture(Texture texture) {
        this.texture = texture;
        rect.right = rect.left + this.texture.getImageWidth();
        rect.bottom = rect.top + this.texture.getImageHeight();
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

    /**
     * Sets this image's position on the screen.
     * 
     * @param position The position on the screen
     */
    public void setPosition(Vec2 position) {
        this.position = position;
        rect.left = (int)this.position.getX();
        rect.top = (int)this.position.getY();
        if (texture != null) {
            rect.right = rect.left + this.texture.getImageWidth();
            rect.bottom = rect.top + this.texture.getImageHeight();
        }
    }

    /**
     * Returns this image's position on the screen.
     * 
     * @return This image's position on the screen
     */
    public Vec2 getPosition() {
        return new Vec2(position.getX(), position.getY());
    }
}
