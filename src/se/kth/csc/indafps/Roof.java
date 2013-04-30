package se.kth.csc.indafps;

import java.io.IOException;

public class Roof extends Entity {
    public Roof(Vec3 position) {
        super(position);
        try {
            model = ModelManager.get("data/roof.obj");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}