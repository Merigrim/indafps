package se.kth.csc.indafps;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Arrays;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;

public class Model {
    private class Face {
        public int[] v;
        public int[] vn;
        public int[] vt;

        public Face() {
            v = new int[3];
            vn = new int[3];
            vt = new int[3];
        }
    }

    int buffer;

    private Model() {
        buffer = 0;
    }

    public int getBuffer() {
        return buffer;
    }

    /**
     * Loads a model from an OBJ-file.
     * 
     * @param filename The OBJ-file to load.
     * @return The model loaded from the file
     * @throws IOException
     */
    public static Model loadObj(String filename) throws IOException,
            FileNotFoundException {
        Model model = new Model();
        model.buffer = GL15.glGenBuffers();

        ArrayList<Vec3> verts = new ArrayList<Vec3>();
        ArrayList<Vec2> texcs = new ArrayList<Vec2>();
        ArrayList<Vec3> norms = new ArrayList<Vec3>();
        ArrayList<Face> faces = new ArrayList<Face>();

        File obj = new File(filename);
        BufferedReader r = new BufferedReader(new InputStreamReader(
                new FileInputStream(obj)));
        try {
            String line;
            while ((line = r.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("v ")) {
                    String[] parts = line.split(" ");
                    if (parts.length != 4) {
                        throw new IOException(
                                "Malformed input. Vertex coordinates must be a 3 tuple.");
                    }
                    verts.add(new Vec3(Float.parseFloat(parts[1]), Float
                            .parseFloat(parts[2]), Float.parseFloat(parts[3])));
                } else if (line.startsWith("vt ")) {
                    String[] parts = line.split(" ");
                    if (parts.length != 3) {
                        throw new IOException(
                                "Malformed input. Texcoord coordinates must be a 2 tuple.");
                    }
                    texcs.add(new Vec2(Float.parseFloat(parts[1]), Float
                            .parseFloat(parts[2])));
                } else if (line.startsWith("vn ")) {
                    String[] parts = line.split(" ");
                    if (parts.length != 4) {
                        throw new IOException(
                                "Malformed input. Normal coordinates must be a 3 tuple.");
                    }
                    norms.add(new Vec3(Float.parseFloat(parts[1]), Float
                            .parseFloat(parts[2]), Float.parseFloat(parts[3])));
                } else if (line.startsWith("f ")) {
                    String[] parts = line.split(" ");
                    if (parts.length != 4) {
                        throw new IOException(
                                "Malformed input. Face definitions must be a 3 tuple.");
                    }
                    Face f = model.new Face();
                    for (int i = 0; i < 3; ++i) {
                        String[] indices = parts[i + 1].split("/");
                        if (indices.length < 3) {
                            throw new IOException(
                                    "Sorry, models must be exported with both normals and texcoords.");
                        }
                        f.v[i] = Integer.parseInt(indices[0]) - 1;
                        f.vt[i] = Integer.parseInt(indices[1]) - 1;
                        f.vn[i] = Integer.parseInt(indices[2]) - 1;
                    }
                    System.out.printf("Face: %s %s %s\n", Arrays.toString(f.v),
                            Arrays.toString(f.vt), Arrays.toString(f.vn));
                    faces.add(f);
                }
            }
            FloatBuffer data = BufferUtils
                    .createFloatBuffer(faces.size() * 12 * 3);
            for (Face f : faces) {
                for (int i = 0; i < 3; ++i) {
                    Vec3 v = verts.get(f.v[i]);
                    data.put(v.getX());
                    data.put(v.getY());
                    data.put(v.getZ());
                    Vec2 vt = texcs.get(f.vt[i]);
                    data.put(vt.getX());
                    data.put(vt.getY());
                    Vec3 vn = norms.get(f.vn[i]);
                    data.put(vn.getX());
                    data.put(vn.getY());
                    data.put(vn.getZ());
                    for (int j = 0; j < 4; ++j) {
                        data.put((faces.indexOf(f) / 2)
                                / (float)(faces.size() / 2));
                    }
                }
            }
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, model.buffer);
            data.flip();
            GL15.glBufferData(GL15.GL_ARRAY_BUFFER, data, GL15.GL_STATIC_DRAW);
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        } finally {
            r.close();
        }
        return model;
    }
}
