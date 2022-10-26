package graphic;

import math.Vector3f;

public class Vertex {
    private Vector3f position;

    public Vertex(Vector3f postion) {
        this.position = postion;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }
}
