package se.kth.csc.indafps;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.util.glu.GLU;

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
    private Texture font;
    private int[] charWidths;
    private int widthOffset;

    public Renderer() {
        BufferedReader br;
        try {
            font = TextureManager.get("data/font.png");
            br = new BufferedReader(new InputStreamReader(new FileInputStream(
                    "data/font.csv")));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return;
        }
        charWidths = new int[95];
        try {
            for (int i = 0; i < 95; ++i) {
                charWidths[i] = Integer.parseInt(br.readLine());
            }
            widthOffset = Integer.parseInt(br.readLine());
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void setLightPosition() {
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, new Vec4(0.0f, 1.0f,
                0.0f, 0.0f).toBuffer());
        GL11.glLight(GL11.GL_LIGHT1, GL11.GL_POSITION, new Vec4(0.0f, -1.0f,
                0.0f, 0.0f).toBuffer());
    }

    /**
     * Sets the camera to use when rendering the scene.
     * 
     * @param camera The camera to use
     */
    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    /**
     * Renders text at the position of subtitles.
     * 
     * @param text The text to render
     */

    public void render(String text) {
        render(text, new Vec2(Display.getWidth() / 2.0f,
                Display.getHeight() / 6.0f * 5.0f), new Vec2(0.5f, 0),
                new Vec4(1.0f, 1.0f, 1.0f, 1.0f));
    }

    /**
     * Renders centered text at the given position.
     * 
     * @param text The text to render
     * @param position The position to render the text at
     */
    public void render(String text, Vec2 position) {
        render(text, position, new Vec2(0, 0), new Vec4(1.0f, 1.0f, 1.0f, 1.0f));
    }

    /**
     * Render a string of text using a bitmap font.
     * 
     * @param text The text to render
     * @param position The position to render the text at
     * @param anchor The anchor position to use when rendering the text
     */
    public void render(String text, Vec2 position, Vec2 anchor, Vec4 color) {
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        if (text.length() == 0) {
            return;
        }
        projection = Mat4.ortho(0, 1280, 0, 720, -1.0f, 1.0f);
        world = new Mat4();
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glPushMatrix();
        GL11.glLoadMatrix(projection.toFloatBuffer());
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glPushMatrix();
        GL11.glLoadMatrix(world.toFloatBuffer());

        GL11.glColor4f(color.getR(), color.getG(), color.getB(), color.getA());
        font.bind();
        GL11.glBegin(GL11.GL_TRIANGLES);
        int totalWidth = -widthOffset;
        int textWidth = 0;
        for (int i = 0; i < text.length(); ++i) {
            int c = text.charAt(i) - 32;
            textWidth += charWidths[c];
        }
        int textHeight = 32;
        int x = (int)(position.getX() - anchor.getX() * textWidth);
        int y = (int)(position.getY() - anchor.getY() * textHeight);
        for (int i = 0; i < text.length(); ++i) {
            int c = text.charAt(i) - 32;
            int charWidth = charWidths[c] + widthOffset * 2;
            float tx = ((c % 8) * 32.0f) / 256.0f;
            float ty = 1.0f - (c / 8) / 16.0f;
            float tw = (float)(charWidth) / font.getImageWidth();
            float th = -1.0f / 16.0f;
            GL11.glTexCoord2f(tx, ty + th);
            GL11.glVertex2f(x + totalWidth, y + 32);
            GL11.glTexCoord2f(tx + tw, ty);
            GL11.glVertex2f(x + totalWidth + charWidth, position.getY());
            GL11.glTexCoord2f(tx, ty);
            GL11.glVertex2f(x + totalWidth, y);

            GL11.glTexCoord2f(tx, ty + th);
            GL11.glVertex2f(x + totalWidth, y + 32);
            GL11.glTexCoord2f(tx + tw, ty + th);
            GL11.glVertex2f(x + totalWidth + charWidth, y + 32);
            GL11.glTexCoord2f(tx + tw, ty);
            GL11.glVertex2f(x + totalWidth + charWidth, y);
            totalWidth += charWidth - widthOffset * 2;
        }
        GL11.glEnd();
        font.release();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

        GL11.glPopMatrix();
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glPopMatrix();
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }

    /**
     * Renders the specified image.
     * 
     * @param image The image to render
     */
    public void render(Image image) {
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
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
        tex.bind();
        GL11.glBegin(GL11.GL_TRIANGLE_STRIP);
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex2f(rect.left, rect.top);
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex2f(rect.left, rect.bottom);
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex2f(rect.right, rect.top);
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex2f(rect.right, rect.bottom);
        GL11.glEnd();
        tex.release();
        GL11.glPopMatrix();
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glPopMatrix();
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }

    /**
     * Renders the specified rectangle.
     * 
     * @param rect The rectangle to be drawn.
     * @param color The color of the rectangle to be drawn.
     */
    public void render(Rect rect, Vec4 color) {
        projection = Mat4.ortho(0, 1280, 0, 720, -1.0f, 1.0f);
        world = new Mat4();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glPushMatrix();
        GL11.glLoadMatrix(projection.toFloatBuffer());
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glPushMatrix();
        GL11.glLoadMatrix(world.toFloatBuffer());

        GL11.glColor4f(color.getR(), color.getG(), color.getB(), color.getA());
        GL11.glBegin(GL11.GL_TRIANGLE_STRIP);
        GL11.glVertex2f(rect.left, rect.top);
        GL11.glVertex2f(rect.left, rect.bottom);
        GL11.glVertex2f(rect.right, rect.top);
        GL11.glVertex2f(rect.right, rect.bottom);
        GL11.glEnd();

        GL11.glPopMatrix();
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glPopMatrix();
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }

    public void render(Model model) {
        render(model, null);
    }

    /**
     * Renders the specified model.
     * 
     * @param model The model to render
     */
    public void render(Model model, Texture texture) {
        GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
        GL11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
        GL11.glEnableClientState(GL11.GL_NORMAL_ARRAY);
        // GL11.glEnableClientState(GL11.GL_COLOR_ARRAY);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, model.getBuffer());
        if (texture != null) {
            texture.bind();
        } else if (model.hasTexture()) {
            model.getTexture().bind();
        }

        int stride = 4 * 12;
        GL11.glVertexPointer(3, GL11.GL_FLOAT, stride, 0);
        GL11.glTexCoordPointer(2, GL11.GL_FLOAT, stride, 4 * 3);
        GL11.glNormalPointer(GL11.GL_FLOAT, stride, 4 * 5);
        // TODO Color pointer disabled
        // GL11.glColorPointer(4, GL11.GL_FLOAT, stride, 4 * 8);
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, model.getFaceCount() * 3);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);
        GL11.glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
        GL11.glDisableClientState(GL11.GL_NORMAL_ARRAY);
        // GL11.glDisableClientState(GL11.GL_COLOR_ARRAY);
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
        Vec3 pos = camera.getPosition();
        Vec3 target = camera.getTarget();
        GLU.gluLookAt(pos.getX(), pos.getY(), pos.getZ(), target.getX(),
                target.getY(), target.getZ(), 0, 1, 0);
        Vec3 epos = entity.getPosition();
        Vec3 erot = entity.getRotation();
        Vec3 escale = entity.getScale();
        Vec4 ecolor = entity.getColor();
        GL11.glTranslatef(epos.getX(), epos.getY(), epos.getZ());
        setLightPosition();
        GL11.glRotatef(erot.getX() / (float)Math.PI * 180.0f, 1, 0, 0);
        GL11.glRotatef(erot.getY() / (float)Math.PI * 180.0f, 0, 1, 0);
        GL11.glRotatef(erot.getZ() / (float)Math.PI * 180.0f, 0, 0, 1);
        GL11.glColor4f(ecolor.getR(), ecolor.getG(), ecolor.getB(),
                ecolor.getA());
        render(entity.getModel(), entity.getTexture());
    }
}
