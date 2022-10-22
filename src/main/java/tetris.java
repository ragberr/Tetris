
import engine.Window;


public class tetris implements Runnable {
    private static final int width = 1280, height = 760;

    private Thread thread;
    private boolean running;

    private Window window;

    public void start() {
    running = true;
    thread = new Thread(this, "Tetris");
    thread.start();
    }

    private void render() {

    }

    private void update() {

    }

    private void init() {
        window = new Window(width, height, "Tetris");
        window.createWindow();
    }

    @Override
    public void run() {
        init();
        while(running) {
            update();
            render();
        }

    }

    public static void main(String[] args) {
        new tetris().start();
    }
}
