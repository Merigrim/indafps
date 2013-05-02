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

	/**
	 * Returns a reference to the model inside the specified file. If there
	 * was an error accessing the model, an error message will be printed to
	 * the stderr and null is returned.
	 *
	 * @param filename The name of the file containing the model.
	 * @return A reference to the requested model. Null is returned if there
	 * was an error accessing the file.
	 */
    public static Model get(String filename) {
        try {
			if (models == null) {
				models = new HashMap<String, Model>();
			}
			if (!models.containsKey(filename)) {
				models.put(filename, Model.loadObj(filename));
			}
			return models.get(filename);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return null;
    }
}
