import engine.Input;
import engine.Window;
import graphic.Mesh;
import graphic.Shader;
import graphic.Vertex;
import graphic.Renderer;
import math.Vector3f;
import objects.Camera;
import objects.GameObject;
import org.lwjgl.glfw.GLFW;

public class tetris implements Runnable {
    private static final int width = 1280, height = 760;
    private Thread thread;
    private boolean running;
    private Window window;

    private Shader shader;
    private Renderer renderer;

    public Mesh mesh = new Mesh(new Vertex[] {
            //Back face
            new Vertex(new Vector3f(-0.5f,  0.5f, -0.5f), new Vector3f(-0.5f,  0.5f, -0.5f)),
            new Vertex(new Vector3f(-0.5f, -0.5f, -0.5f), new Vector3f(-0.5f, -0.5f, -0.5f)),
            new Vertex(new Vector3f( 0.5f, -0.5f, -0.5f), new Vector3f(0.5f, -0.5f, -0.5f)),
            new Vertex(new Vector3f( 0.5f,  0.5f, -0.5f), new Vector3f(0.5f,  0.5f, -0.5f)),

            //Front face
            new Vertex(new Vector3f(-0.5f,  0.5f,  0.5f), new Vector3f(-0.5f,  0.5f,  0.5f)),
            new Vertex(new Vector3f(-0.5f, -0.5f,  0.5f), new Vector3f(-0.5f, -0.5f,  0.5f)),
            new Vertex(new Vector3f( 0.5f, -0.5f,  0.5f), new Vector3f(0.5f, -0.5f,  0.5f)),
            new Vertex(new Vector3f( 0.5f,  0.5f,  0.5f), new Vector3f(0.5f,  0.5f,  0.5f)),

            //Right face
            new Vertex(new Vector3f( 0.5f,  0.5f, -0.5f), new Vector3f(0.5f,  0.5f, -0.5f)),
            new Vertex(new Vector3f( 0.5f, -0.5f, -0.5f), new Vector3f(0.5f, -0.5f, -0.5f)),
            new Vertex(new Vector3f( 0.5f, -0.5f,  0.5f), new Vector3f(0.5f, -0.5f,  0.5f)),
            new Vertex(new Vector3f( 0.5f,  0.5f,  0.5f), new Vector3f(0.5f,  0.5f,  0.5f)),

            //Left face
            new Vertex(new Vector3f(-0.5f,  0.5f, -0.5f), new Vector3f(-0.5f,  0.5f, -0.5f)),
            new Vertex(new Vector3f(-0.5f, -0.5f, -0.5f), new Vector3f(-0.5f, -0.5f, -0.5f)),
            new Vertex(new Vector3f(-0.5f, -0.5f,  0.5f), new Vector3f(-0.5f, -0.5f,  0.5f)),
            new Vertex(new Vector3f(-0.5f,  0.5f,  0.5f), new Vector3f(-0.5f,  0.5f,  0.5f)),

            //Top face
            new Vertex(new Vector3f(-0.5f,  0.5f,  0.5f), new Vector3f(-0.5f,  0.5f,  0.5f)),
            new Vertex(new Vector3f(-0.5f,  0.5f, -0.5f), new Vector3f(-0.5f,  0.5f, -0.5f)),
            new Vertex(new Vector3f( 0.5f,  0.5f, -0.5f), new Vector3f(0.5f,  0.5f, -0.5f)),
            new Vertex(new Vector3f( 0.5f,  0.5f,  0.5f), new Vector3f(0.5f,  0.5f,  0.5f)),

            //Bottom face
            new Vertex(new Vector3f(-0.5f, -0.5f,  0.5f), new Vector3f(-0.5f, -0.5f,  0.5f)),
            new Vertex(new Vector3f(-0.5f, -0.5f, -0.5f), new Vector3f(-0.5f, -0.5f, -0.5f)),
            new Vertex(new Vector3f( 0.5f, -0.5f, -0.5f), new Vector3f(0.5f, -0.5f, -0.5f)),
            new Vertex(new Vector3f( 0.5f, -0.5f,  0.5f), new Vector3f(0.5f, -0.5f,  0.5f)),
    }, new int[] {
            //Back face
            0, 1, 3,
            3, 1, 2,

            //Front face
            4, 5, 7,
            7, 5, 6,

            //Right face
            8, 9, 11,
            11, 9, 10,

            //Left face
            12, 13, 15,
            15, 13, 14,

            //Top face
            16, 17, 19,
            19, 17, 18,

            //Bottom face
            20, 21, 23,
            23, 21, 22
    });

    public GameObject object = new GameObject(new Vector3f(0f,0f,0f),new Vector3f(0f,0f,0f), new Vector3f(1f,1f,1f), mesh);

    public Camera camera = new Camera(new Vector3f(0f,0f,0f), new Vector3f(0f,0f,0f));

    private void init() {
        window = new Window(width, height, "Tetris");
        shader = new Shader("src\\main\\resources\\mainVertex.glsl", "src\\main\\resources\\mainFragment.glsl");
        renderer = new Renderer(window ,shader);
        window.setBackgroundColor(new Vector3f(1.0f,0f,0f));
        window.createWindow();
        mesh.create();
        shader.create();
    }

    public void start() {
    running = true;
    thread = new Thread(this, "Tetris");
    thread.start();
    }

    private void render() {
        renderer.renderObject(object, camera);
        window.swapBuffers();
    }

    private void close() {
        window.destroy();
        shader.destroy();
    }

    private void update() {
        window.update();
        camera.update();
        object.update();
    }

    @Override
    public void run() {
        init();
        while(!window.shouldClose()) {
            update();
            render();
            if(Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)) window.mouseState(true);
        }
        close();
    }

    public static void main(String[] args) {
        new tetris().start();
    }
}
