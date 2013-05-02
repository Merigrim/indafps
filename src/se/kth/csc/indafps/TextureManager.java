package se.kth.csc.indafps;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The texture manager keeps track of which textures have already been loaded,
 * to make sure that the same texture is not loaded into memory twice.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-29
 */
public class TextureManager {
    private static Map<String, Texture> textures;

    public static Texture get(String filename) {
        try {
            if (textures == null) {
                textures = new HashMap<String, Texture>();
            }
            if (!textures.containsKey(filename)) {
                textures.put(filename, Texture.load(filename));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return textures.get(filename);
    }
}
