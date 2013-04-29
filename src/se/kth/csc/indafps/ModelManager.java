package se.kth.csc.indafps;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The model manager keeps track of which models have already been loaded, to
 * make sure that the same model is not loaded into memory twice.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-29
 */
public class ModelManager {
    private static Map<String, Model> models;

    public static Model get(String filename) throws IOException {
        if (models == null) {
            models = new HashMap<String, Model>();
        }
        if (!models.containsKey(filename)) {
            models.put(filename, Model.loadObj(filename));
        }
        return models.get(filename);
    }
}
