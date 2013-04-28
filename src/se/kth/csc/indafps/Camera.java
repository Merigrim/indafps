package se.kth.csc.indafps;

public class Camera {
    private Vec3 position;
    private Vec3 target;
    private float theta, phi;

    public Camera() {
        position = new Vec3(0.0f, 0.0f, 0.0f);
        target = new Vec3(0.0f, 0.0f, 0.0f);
        theta = 0.0f;
        phi = (float)Math.PI * 0.5f;
    }

    public Vec3 getPosition() {
        return new Vec3(position);
    }

    public void setPosition(Vec3 position) {
        this.position = position;
    }

    public Vec3 getViewDirection() {
        return target.sub(position);
    }

    public Vec3 getTarget() {
        return new Vec3(target);
    }

    public void move(float dx, float dy) {
        theta += dx * 0.01f;
        phi += dy * 0.01f;
        if (phi < (float)Math.PI * 0.01f) {
            phi = (float)Math.PI * 0.01f;
        } else if (phi > (float)Math.PI * 0.99f) {
            phi = (float)Math.PI * 0.99f;
        }
        target.setX((float)Math.cos(theta) * (float)Math.sin(phi));
        target.setY((float)Math.cos(phi));
        target.setZ((float)Math.sin(theta) * (float)Math.sin(phi));
        target.copy(target.add(position));
        System.out.println("" + target + ", " + theta + ", " + phi);
    }
}
