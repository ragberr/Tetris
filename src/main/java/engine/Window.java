package engine;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;

import static org.lwjgl.glfw.GLFW.*;

public class Window {
    private int width, height;
    private String title;
    private long window;

    public Window(int width, int height, String title) {
        this.height = height;
        this.width = width;
        this.title = title;

    }

    public void createWindow()
    {
        if(!GLFW.glfwInit()) {
            System.err.println("Error while initializing window");
            return;
        }

        window = glfwCreateWindow(this.width, this.height, "Tetris", 0, 0);

        if (window == 0) {
            System.err.println("Error while initializing window2");
            return;
        }

        GLFWVidMode videoMode = GLFW.glfwGetVideoMode(org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor());
        GLFW.glfwSetWindowPos(window,(videoMode.width() - width)/2,(videoMode.height() - height)/2 );

        GLFW.glfwShowWindow(window);
    }
}
