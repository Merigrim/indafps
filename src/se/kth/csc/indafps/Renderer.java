package se.kth.csc.indafps;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.opengl.Texture;

/**
 * The Renderer class is responsible for rendering all game components.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-27
 */
public class Renderer {
    Mat4 projection;
    Mat4 view;
    Mat4 world;
    Model cube;

    public Renderer() {
        try {
            cube = Model.loadObj("data/cube.obj");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Renders the specified image.
     * 
     * @param image The image to render
     */
    public void render(Image image) {
        projection = Mat4.ortho(0, 1280, 0, 720, -1.0f, 1.0f);
        world = new Mat4();
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadMatrix(projection.toFloatBuffer());
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadMatrix(world.toFloatBuffer());

        Rect rect = image.getRect();
        Texture tex = image.getTexture();
        tex.bind();
        GL11.glBegin(GL11.GL_TRIANGLE_STRIP);
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex2f(rect.left, rect.bottom);
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex2f(rect.left, rect.top);
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex2f(rect.right, rect.bottom);
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex2f(rect.right, rect.top);
        GL11.glEnd();
        tex.release();
    }

    /**
     * Renders the specified model.
     * 
     * @param model The model to render
     */
    public void render(Model model) {
        GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
        GL11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
        GL11.glEnableClientState(GL11.GL_NORMAL_ARRAY);
        GL11.glEnableClientState(GL11.GL_COLOR_ARRAY);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, model.getBuffer());
        int stride = Float.SIZE * 12;
        GL11.glVertexPointer(3, GL11.GL_FLOAT, stride, 0);
        GL11.glTexCoordPointer(2, GL11.GL_FLOAT, stride, Float.SIZE * 3);
        GL11.glNormalPointer(GL11.GL_FLOAT, stride, Float.SIZE * 5);
        GL11.glColorPointer(4, GL11.GL_FLOAT, stride, Float.SIZE * 8);
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 36);
        // GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);
        GL11.glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
        GL11.glDisableClientState(GL11.GL_NORMAL_ARRAY);
        GL11.glDisableClientState(GL11.GL_COLOR_ARRAY);
        GL11.glBegin(GL11.GL_TRIANGLES);
        GL11.glColor3f(1.0f, 1.0f, 0.0f);
        GL11.glVertex3f(1.0f, 0.0f, 0.0f);
        GL11.glVertex3f(1.0f, 1.0f, 0.0f);
        GL11.glVertex3f(0.0f, 1.0f, 1.0f);
        GL11.glColor3f(0.0f, 1.0f, 1.0f);
        GL11.glVertex3f(-1.0f, 0.0f, 0.0f);
        GL11.glVertex3f(-1.0f, -1.0f, 0.0f);
        GL11.glVertex3f(0.0f, -1.0f, -1.0f);
        GL11.glEnd();
    }

    /**
     * Renders the specified entity.
     * 
     * @param entity The entity to render
     */
    public void render(Entity entity) {
        projection = Mat4.perspective(90.0f, 1280.0f / 720.0f, 0.1f, 1000.0f);
        view = new Mat4();
        world = new Mat4();
        world.translate(new Vec3(0, 0, -10.0f));
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadMatrix(projection.toFloatBuffer());
        // GLU.gluPerspective(90.0f, 1280.0f / 720.0f, 0.1f, 1000.0f);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadMatrix(Mat.mul(view, world).toFloatBuffer());
        render(cube);
    }
}
