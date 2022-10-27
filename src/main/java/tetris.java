import engine.Window;
import graphic.Mesh;
import graphic.Shader;
import graphic.Vertex;
import graphic.Renderer;
import math.Vector3f;

public class tetris implements Runnable {
    private static final int width = 1280, height = 760;
    private Thread thread;
    private boolean running;
    private Window window;

    private Shader shader;
    private Renderer renderer;

    public Mesh mesh = new Mesh(new Vertex[]{
        new Vertex(new Vector3f(-1f, 1f, 0.0f)),
        new Vertex(new Vector3f(-1f, -1f, 0.0f)),
        new Vertex(new Vector3f( 1f, -1f, 0.0f)),
        new Vertex(new Vector3f( 1f,  1f, 0.0f))
    }, new int[] {
        0, 1, 2,
        0, 3, 2
    });

    private void init() {
        window = new Window(width, height, "Tetris");
        shader = new Shader("C:\\Users\\ragberr\\IdeaProjects\\Tetris\\src\\main\\resources\\mainVertex.glsl", "C:\\Users\\ragberr\\IdeaProjects\\Tetris\\src\\main\\resources\\mainFragment.glsl");
        renderer = new Renderer(shader);
        window.setBackgroundColor(new Vector3f(1.0f,0,0));
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
        window.destroy();
    }

    public static void main(String[] args) {
        new tetris().start();
    }
}
