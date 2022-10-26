import engine.Window;
import graphic.Mesh;
import graphic.Vertex;
import graphic.Renderer;
import math.Vector3f;

public class tetris implements Runnable {
    private static final int width = 1280, height = 760;
    private Thread thread;
    private boolean running;
    private Window window;
    private Renderer renderer;

    public Mesh mesh = new Mesh(new Vertex[]{
        new Vertex(new Vector3f(-0.5f, 0.5f, 0.0f)),
        new Vertex(new Vector3f( 0.5f, 0.5f, 0.0f)),
        new Vertex(new Vector3f( 0.5f,-0.5f, 0.0f)),
        new Vertex(new Vector3f(-0.5f,-0.5f, 0.0f))
    }, new int[] {
        0, 1, 2,
        0, 3, 2
    });

    private void init() {
        window = new Window(width, height, "Tetris");
        renderer = new Renderer();
        window.createWindow();
        mesh.create();
    }
    public void start() {
    running = true;
    thread = new Thread(this, "Tetris");
    thread.start();
    }

    private void render() {
        renderer.renderMesh(mesh);
        window.swapBuffers();
    }

    private void update() {
        window.update();

    }

    @Override
    public void run() {
        init();
        while(!window.shouldClose()) {
            update();
            render();
        }

    }

    public static void main(String[] args) {
        new tetris().start();
    }
}
