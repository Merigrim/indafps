package se.kth.csc.indafps;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;

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
        /*
         * cubeBuffer = GL15.glGenBuffers();
         * GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, cubeBuffer); FloatBuffer
         * cubeData = BufferUtils.createFloatBuffer(324); cubeData.put(0.0f;
         * cubeData[0 * 9 + 1] = 0.0f; GL15.glBufferData(GL15.GL_ARRAY_BUFFER,
         * cubeData, GL15.GL_STATIC_DRAW);
         */
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

    public void render(Model model) {
        GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
        GL11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
        GL11.glEnableClientState(GL11.GL_NORMAL_ARRAY);
        GL11.glEnableClientState(GL11.GL_COLOR_ARRAY);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, model.getBuffer());
        GL11.glVertexPointer(3, GL11.GL_FLOAT, Float.SIZE * 12, 0);
        GL11.glTexCoordPointer(2, GL11.GL_FLOAT, Float.SIZE * 12,
                Float.SIZE * 3);
        GL11.glNormalPointer(GL11.GL_FLOAT, Float.SIZE * 12, Float.SIZE * 5);
        GL11.glColorPointer(4, GL11.GL_FLOAT, Float.SIZE * 12, Float.SIZE * 8);
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 36);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);
        GL11.glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
        GL11.glDisableClientState(GL11.GL_NORMAL_ARRAY);
        GL11.glDisableClientState(GL11.GL_COLOR_ARRAY);
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
        world.translate(new Vec3(0, 0, 10.0f));
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL13.glLoadTransposeMatrix(projection.toFloatBuffer());
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL13.glLoadTransposeMatrix(Mat.mul(view, world).toFloatBuffer());
        render(cube);
    }
}
