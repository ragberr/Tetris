package graphic;

import engine.Window;
import objects.Camera;
import objects.GameObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

public class Renderer {
    private Shader shader;
    private Window window;

    public Renderer(Window window, Shader shader) {
        this.shader = shader;
        this.window = window;
    }


    public void renderObject(GameObject object, Camera camera) {
        GL30.glBindVertexArray(object.getMesh().getVAO());
        GL30.glEnableVertexAttribArray(1);
        GL30.glEnableVertexAttribArray(0);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, object.getMesh().getIBO());
        shader.bind();
        shader.setUniform("model", Matrix4f.transform(object.getPosition(), object.getRotation(), object.getScale()));
        shader.setUniform("view", Matrix4f.view(camera.getPosition(), camera.getRotation()));
        shader.setUniform("projection", window.getProjectionMatrix());
        GL11.glDrawElements(GL11.GL_TRIANGLES, object.getMesh().getIndices().length, GL11.GL_UNSIGNED_INT, 0);
        shader.unbind();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        GL30.glDisableVertexAttribArray(1);
        GL30.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
    }
}