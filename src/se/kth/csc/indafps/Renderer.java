package se.kth.csc.indafps;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
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
    private Mat4 projection;
    private Mat4 view;
    private Mat4 world;
    private Model cube;
    private Camera camera;

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
        // Test code
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GLU.gluLookAt(5, 0, -10, 0, 0, 0, 0, 1, 0);
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
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
        GL11.glPushMatrix();
        GL11.glLoadMatrix(projection.toFloatBuffer());
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glPushMatrix();
        GL11.glLoadMatrix(world.toFloatBuffer());

        Rect rect = image.getRect();
        Texture tex = image.getTexture();
        GL11.glBegin(GL11.GL_TRIANGLE_STRIP);
        tex.bind();
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex2f(rect.left, rect.top);
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex2f(rect.left, rect.bottom);
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex2f(rect.right, rect.top);
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex2f(rect.right, rect.bottom);
        tex.release();
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glPopMatrix();
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }

    /**
     * Renders the specified model.
     * 
     * @param model The model to render
     */
    public void render(Model model) {
        if (model.hasTexture()) {
            model.getTexture().bind();
        }
        GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
        GL11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
        GL11.glEnableClientState(GL11.GL_NORMAL_ARRAY);
        GL11.glEnableClientState(GL11.GL_COLOR_ARRAY);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, model.getBuffer());

        int stride = 4 * 12;
        GL11.glVertexPointer(3, GL11.GL_FLOAT, stride, 0);
        GL11.glTexCoordPointer(2, GL11.GL_FLOAT, stride, 4 * 3);
        GL11.glNormalPointer(GL11.GL_FLOAT, stride, 4 * 5);
        GL11.glColorPointer(4, GL11.GL_FLOAT, stride, 4 * 8);
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, model.getFaceCount() * 3);
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
        if (entity.getModel() == null) {
            return;
        }
        projection = Mat4.perspective(90.0f, 1280.0f / 720.0f, 0.1f, 1000.0f);
        view = new Mat4();
        world = new Mat4();
        // world.translate(new Vec3(0, 0, -10.0f));
        // view.rotate(5.0f, new Vec3(0, 1, 0));
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadMatrix(projection.toFloatBuffer());
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();

        // Test code
        /*
         * GL11.glRotatef(camera.getRotation().getX(), 1, 1, 0);
         * GL11.glRotatef(camera.getRotation().getY() + 180, 0, 1, 0);
         * GL11.glRotatef(camera.getRotation().getZ(), 0, 0, 1);
         * GL11.glTranslatef(camera.getPosition().getX(), camera.getPosition()
         * .getY(), camera.getPosition().getZ());
         */
        Vec3 pos = camera.getPosition();
        Vec3 target = camera.getTarget();
        GLU.gluLookAt(pos.getX(), pos.getY(), pos.getZ(), target.getX(),
                target.getY(), target.getZ(), 0, 1, 0);
        Vec3 epos = entity.getPosition();
        Vec3 erot = entity.getRotation();
        Vec3 escale = entity.getScale();
        GL11.glScalef(escale.getX(), escale.getY(), escale.getZ());
        GL11.glTranslatef(epos.getX(), epos.getY(), epos.getZ());
        GL11.glRotatef(erot.getX() / (float)Math.PI * 180.0f, 1, 0, 0);
        GL11.glRotatef(erot.getY() / (float)Math.PI * 180.0f, 0, 1, 0);
        GL11.glRotatef(erot.getZ() / (float)Math.PI * 180.0f, 0, 0, 1);
        render(entity.getModel());
    }
}
