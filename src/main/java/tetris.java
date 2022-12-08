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

import java.rmi.AccessException;

public class tetris implements Runnable {
    private static final int width = 1280, height = 760;
    private Thread thread;
    private boolean running;
    private Window window;
    private GameState gameState;

    private Shader shader;
    private Renderer renderer;

    private Vector3f redColor = new Vector3f(1f, 0f, 0f);
    private Vector3f blueColor = new Vector3f(0f, 0f, 1f);
    private Vector3f greenColor = new Vector3f(0f, 1f, 0f);

    public Mesh mesh = new Mesh(new Vertex[] {
            //Back face
            new Vertex(new Vector3f(-0.5f,  0.5f, -0.5f), redColor),
            new Vertex(new Vector3f(-0.5f, -0.5f, -0.5f), redColor),
            new Vertex(new Vector3f( 0.5f, -0.5f, -0.5f), redColor),
            new Vertex(new Vector3f( 0.5f,  0.5f, -0.5f), redColor),

            //Front face
            new Vertex(new Vector3f(-0.5f,  0.5f,  0.5f), redColor),
            new Vertex(new Vector3f(-0.5f, -0.5f,  0.5f), redColor),
            new Vertex(new Vector3f( 0.5f, -0.5f,  0.5f), redColor),
            new Vertex(new Vector3f( 0.5f,  0.5f,  0.5f), redColor),

            //Right face
            new Vertex(new Vector3f( 0.5f,  0.5f, -0.5f), redColor),
            new Vertex(new Vector3f( 0.5f, -0.5f, -0.5f), redColor),
            new Vertex(new Vector3f( 0.5f, -0.5f,  0.5f), redColor),
            new Vertex(new Vector3f( 0.5f,  0.5f,  0.5f), redColor),

            //Left face
            new Vertex(new Vector3f(-0.5f,  0.5f, -0.5f), redColor),
            new Vertex(new Vector3f(-0.5f, -0.5f, -0.5f), redColor),
            new Vertex(new Vector3f(-0.5f, -0.5f,  0.5f), redColor),
            new Vertex(new Vector3f(-0.5f,  0.5f,  0.5f), redColor),

            //Top face
            new Vertex(new Vector3f(-0.5f,  0.5f,  0.5f), redColor),
            new Vertex(new Vector3f(-0.5f,  0.5f, -0.5f), redColor),
            new Vertex(new Vector3f( 0.5f,  0.5f, -0.5f), redColor),
            new Vertex(new Vector3f( 0.5f,  0.5f,  0.5f), redColor),

            //Bottom face
            new Vertex(new Vector3f(-0.5f, -0.5f,  0.5f), redColor),
            new Vertex(new Vector3f(-0.5f, -0.5f, -0.5f), redColor),
            new Vertex(new Vector3f( 0.5f, -0.5f, -0.5f), redColor),
            new Vertex(new Vector3f( 0.5f, -0.5f,  0.5f), redColor),
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



    public Camera camera = new Camera(new Vector3f(-10f,5f,1f), new Vector3f(0f,0f,0f));


    private void init() {
        window = new Window(width, height, "Tetris");
        shader = new Shader("src\\main\\resources\\mainVertex.glsl", "src\\main\\resources\\mainFragment.glsl");
        renderer = new Renderer(window ,shader);
        gameState = new GameState();
        window.setBackgroundColor(new Vector3f(0.169f,0.169f,0.169f));
        window.createWindow();
        shader.create();
        mesh.create();
    }

    public void start() {
        running = true;
        thread = new Thread(this, "Tetris");
        thread.start();
    }

    private void RenderBoard() {
        for(int x = 0; x < gameState.gameBoard.getWidth();x++) {
            for(int y = 0; y < gameState.gameBoard.getHeigth(); y++) {
                for(int z = 0; z < gameState.gameBoard.getDepth(); z++) {
                    if(gameState.getGameBoard()[x][y][z] == 1) {
                        renderer.renderObject(new GameObject(new Vector3f(x,y,z),new Vector3f(0f,0f,0f), new Vector3f(1f,1f,1f), mesh),camera);
                    }
                }
            }
        }
    }

    private void RenderBlock() {
        for(int x = 0; x < gameState.currentBlock.getWidth();x++) {
            for(int y = 0; y < gameState.currentBlock.getHeigth(); y++) {
                for(int z = 0; z < gameState.currentBlock.getDepth(); z++) {
                    if(gameState.currentBlock.pieces[x][y][z] == 1) {
                        renderer.renderObject(new GameObject(new Vector3f(gameState.currentBlockPosition.getX() + x,gameState.currentBlockPosition.getY() - y,gameState.currentBlockPosition.getZ() + z),new Vector3f(0f,0f,0f), new Vector3f(1f,1f,1f), mesh),camera);
                    }
                }
            }
        }
    }
    private void render() {
        RenderBoard();
        RenderBlock();

        window.swapBuffers();
    }

    private void close() {
        window.destroy();
        shader.destroy();
    }

    private void update() {
        window.update();
        camera.update();
        gameState.update();
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
