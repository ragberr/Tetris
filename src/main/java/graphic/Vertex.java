package graphic;

import math.Vector3f;

public class Vertex {
    private Vector3f position;
    private Vector3f color;

    public Vertex(Vector3f postion, Vector3f color) {
        this.position = postion;
        this.color = color;
    }

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getColor() {
        return color;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }
}