package engine;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;

import static org.lwjgl.glfw.GLFW.*;

public class Window {
    private int width, height;
    private String title;
    private long window;
    private int frames;
    private long time;

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
        Input input = new Input();
        window = glfwCreateWindow(this.width, this.height, "Tetris", 0, 0);

        if (window == 0) {
            System.err.println("Error while initializing window2");
            return;
        }

        GLFWVidMode videoMode = GLFW.glfwGetVideoMode(org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor());
        GLFW.glfwSetWindowPos(window,(videoMode.width() - width)/2,(videoMode.height() - height)/2 );
        GLFW.glfwMakeContextCurrent(window);

        GLFW.glfwSetKeyCallback(window, input.getKeyboardCallback());
        GLFW.glfwSetCursorPosCallback(window, input.getMouseMoveCallback());
        GLFW.glfwSetMouseButtonCallback(window, input.getMouseButtonCallback());

        GLFW.glfwShowWindow(window);

        GLFW.glfwSwapInterval(1);
    }

    public void update(){
        GLFW.glfwPollEvents();
        frames++;
        if(System.currentTimeMillis() > time + 1000) {
            GLFW.glfwSetWindowTitle(window, title + "Fps | " + frames);
            time = System.currentTimeMillis();
            frames = 0;
        }
    }
    public void swapBuffers() {
        GLFW.glfwSwapBuffers(window);
    }

    public boolean shouldClose() {
        return GLFW.glfwWindowShouldClose(window);
    }
}
