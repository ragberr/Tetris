package engine;

import graphic.Matrix4f;
import math.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.glfw.GLFW.*;

public class Window {
    private int width, height;
    private String title;
    private long window;
    private int frames;
    private long time;
    private Input input;
    private Vector3f backgroundColor;
    private Matrix4f projection;

    public Window(int width, int height, String title) {
        this.height = height;
        this.width = width;
        this.title = title;
        projection = Matrix4f.projection(70.0f, (float) width / (float) height, 0.1f, 1000.0f);


        float maxX = 1.0f - 0.005f;
        float minX = 0.005f;
        float maxY = maxX / (width/height);
        float minY = minX / (width/height);

    }

    public void createWindow()
    {
        if(!GLFW.glfwInit()) {
            System.err.println("Error while initializing window");
            return;
        }
        input = new Input();
        window = glfwCreateWindow(this.width, this.height, "Tetris", 0, 0);

        if (window == 0) {
            System.err.println("Error while initializing window2");
            return;
        }

        GLFWVidMode videoMode = GLFW.glfwGetVideoMode(org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor());
        GLFW.glfwSetWindowPos(window,(videoMode.width() - width)/2,(videoMode.height() - height)/2 );
        GLFW.glfwMakeContextCurrent(window);

        GL.createCapabilities();
        GL11.glEnable(GL11.GL_DEPTH_TEST);

        GLFW.glfwSetKeyCallback(window, input.getKeyboardCallback());
        GLFW.glfwSetCursorPosCallback(window, input.getMouseMoveCallback());
        GLFW.glfwSetMouseButtonCallback(window, input.getMouseButtonsCallback());
        GLFW.glfwSetScrollCallback(window, input.getMouseScrollCallback());

        GLFW.glfwShowWindow(window);

        GLFW.glfwSwapInterval(1);
    }

    public void destroy() {
        input.destroy();
        GLFW.glfwWindowShouldClose(window);
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }

    public void update(){
        GL11.glClearColor(backgroundColor.getX(), backgroundColor.getY(), backgroundColor.getZ(), 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GLFW.glfwPollEvents();
        frames++;
        if(System.currentTimeMillis() > time + 1000) {
            GLFW.glfwSetWindowTitle(window, title + "Fps | " + frames);
            time = System.currentTimeMillis();
            frames = 0;
        }
    }

    public void mouseState(boolean lock) {
        GLFW.glfwSetInputMode(window, GLFW_CURSOR, lock ? GLFW_CURSOR_DISABLED:GLFW_CURSOR_NORMAL);
    }

    public void swapBuffers() {
        GLFW.glfwSwapBuffers(window);
    }

    public boolean shouldClose() {
        return GLFW.glfwWindowShouldClose(window);
    }

    public void setBackgroundColor(Vector3f color) {
        this.backgroundColor = color;
    }

    public Matrix4f getProjectionMatrix() {
        return projection;
    }
}