package se.kth.csc.indafps;

import java.io.IOException;

public class Floor extends Entity {
    public Floor(Vec3 position) {
        super(position);
        try {
            model = ModelManager.get("data/plane.obj");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
