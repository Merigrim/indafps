package se.kth.csc.indafps;

public class Floor extends Entity {
    public Floor(Vec3 position) {
        super(position);
		model = ModelManager.get("data/plane.obj");
    }
}
