package engine;

import org.lwjgl.glfw.*;

public class Input {
    private static boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];
    private static boolean[] buttons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];

    private double mouseX, mouseY;
    private double scrollX, scrollY;

    private GLFWCursorPosCallback mouseMove;
    private GLFWMouseButtonCallback mouseButton;
    private GLFWKeyCallback keyboard;
    private GLFWScrollCallback mouseScroll;

    public static boolean IsKetDown(int key) {
        return keys[key];
    }

    public static boolean IsButtonDown(int button) {
        return buttons[button];
    }

    public void destroy() {
        keyboard.free();
        mouseMove.free();
        mouseButton.free();
        mouseScroll.free();
    }

    public double getMouseX() {
        return mouseX;
    }

    public void setMouseX(double mouseX) {
        this.mouseX = mouseX;
    }

    public double getMouseY() {
        return mouseY;
    }

    public void setMouseY(double mouseY) {
        this.mouseY = mouseY;
    }

    public double getScrollY() {
        return scrollY;
    }

    public double getScrollX() {
        return scrollX;
    }

    public GLFWCursorPosCallback getMouseMoveCallback() {
        return mouseMove;
    }

    public void setMouseMoveCallback(GLFWCursorPosCallback mouseMove) {
        this.mouseMove = mouseMove;
    }

    public GLFWMouseButtonCallback getMouseButtonCallback() {
        return mouseButton;
    }

    public void setMouseButtonCallback(GLFWMouseButtonCallback mouseButton) {
        this.mouseButton = mouseButton;
    }

    public GLFWKeyCallback getKeyboardCallback() {
        return keyboard;
    }

    public void setKeyboardCallback(GLFWKeyCallback keyboard) {
        this.keyboard = keyboard;
    }

    public GLFWScrollCallback getMouseScrollCallback() {
        return mouseScroll;
    }

    public Input() {
        keyboard = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                keys[key] = (action != GLFW.GLFW_RELEASE);
            }
        };

        mouseButton = new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                buttons[button] = (action != GLFW.GLFW_RELEASE);
            }
        };

        mouseMove = new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xpos, double ypos) {
                mouseX = xpos;
                mouseY = ypos;
            }
        };

        mouseScroll = new GLFWScrollCallback() {
            @Override
            public void invoke(long window, double xoffset, double yoffset) {
                scrollX += xoffset;
                scrollY += yoffset;
            }
        };

    }
}
