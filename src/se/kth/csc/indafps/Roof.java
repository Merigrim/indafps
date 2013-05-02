package se.kth.csc.indafps;

public class Roof extends Entity {
    public Roof(Vec3 position) {
        super(position);
		model = ModelManager.get("data/roof.obj");
    }
}
