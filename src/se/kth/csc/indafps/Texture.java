package se.kth.csc.indafps;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class Texture {
    // The OpenGL texture ID
    private int id;
    private int imageWidth;
    private int imageHeight;

    private Texture() {
        id = 0;
        imageWidth = 0;
        imageHeight = 0;
    }

    public int getID() {
        return id;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void bind() {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
    }

    public void release() {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
    }

    public static Texture load(String filename) throws IOException {
        BufferedImage img = ImageIO.read(new File(filename));

        int[] pixels = new int[img.getWidth() * img.getHeight()];
        img.getRGB(0, 0, img.getWidth(), img.getHeight(), pixels, 0,
                img.getWidth());
        ByteBuffer buffer = BufferUtils.createByteBuffer(img.getWidth()
                * img.getHeight() * 4);
        for (int y = img.getHeight() - 1; y >= 0; --y) {
            for (int x = 0; x < img.getWidth(); ++x) {
                int pixel = pixels[y * img.getWidth() + x];
                buffer.put((byte)((pixel >> 16) & 0xff));
                buffer.put((byte)((pixel >> 8) & 0xff));
                buffer.put((byte)(pixel & 0xff));
                buffer.put((byte)((pixel >> 24) & 0xff));
            }
        }
        buffer.flip();

        Texture tex = new Texture();
        tex.id = GL11.glGenTextures();
        tex.imageWidth = img.getWidth();
        tex.imageHeight = img.getHeight();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, tex.id);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S,
                GL12.GL_CLAMP_TO_EDGE);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T,
                GL12.GL_CLAMP_TO_EDGE);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER,
                GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER,
                GL11.GL_LINEAR);
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, tex.imageWidth,
                tex.imageHeight, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
        return tex;
    }
}
