import engine.Input;
import math.Vector3f;
import org.lwjgl.glfw.GLFW;

public class GameState {
    public GameBoard gameBoard;
    private RandomBlockGenerator blockGenerator;
    private int timer;
    public Block currentBlock;
    public Vector3f currentBlockPosition;
    private boolean isFrozen;
    private Input input;

    GameState() {
        gameBoard = new GameBoard(5, 20, 5);
        gameBoard.fillGameBoard();
        blockGenerator = new RandomBlockGenerator();
        currentBlock = blockGenerator.GetNextBlock();
        currentBlockPosition = new Vector3f(gameBoard.getWidth() - 4f, gameBoard.getHeigth() - 4f ,gameBoard.getDepth() - 4f);
        timer = 0;
        isFrozen = true;
    }

    public int[][][] getGameBoard() {
        return this.gameBoard.frame;
    }

    public void recalculateBoundaries(Block block, Vector3f position) {
        for(int x = 0; x < block.getWidth();x++) {
            for(int y = 0; y < block.getHeigth(); y++){
                for(int z = 0; z < block.getDepth(); z++){
                    if(block.pieces[x][y][z] == 1 && (int)position.getX() + x < 0){
                        position.setX(position.getX() - (position.getX() + x));
                    }
                    else if(block.pieces[x][y][z] == 1 && (int)position.getZ() + z < 0) {
                        position.setZ(position.getZ() - (position.getZ() + z));
                    }
                    else if(block.pieces[x][y][z] == 1 && (int)position.getX() + x > gameBoard.getWidth()) {
                        position.setX((position.getX() + x) - ((position.getX() + x) - gameBoard.getWidth()));
                    }
                    else if(block.pieces[x][y][z] == 1 && (int)position.getZ() + z > gameBoard.getDepth()) {
                        position.setZ((position.getZ() + z) - ((position.getZ() + z) - gameBoard.getDepth()));
                    }
                }
            }
        }
    }

    void update() {
        if (Input.isKeyDown(GLFW.GLFW_KEY_P)) {
            isFrozen = !isFrozen;
            input.keys[GLFW.GLFW_KEY_P] = false;
        }
        if(isFrozen) {
        }
        else {
            System.out.println(currentBlockPosition.getX() + " " + currentBlockPosition.getY() + " " + currentBlockPosition.getZ() + " ");
            if (Input.isKeyDown(GLFW.GLFW_KEY_LEFT)) {
                if (!gameBoard.checkBorderX(currentBlockPosition, currentBlock)) {
                    if (gameBoard.checkCollision(currentBlockPosition, currentBlock, new Vector3f(1, 0, 0))) {
                        currentBlockPosition = Vector3f.add(currentBlockPosition, new Vector3f(1, 0, 0));
                        input.keys[GLFW.GLFW_KEY_LEFT] = false;
                    }
                }
            }
            if (Input.isKeyDown(GLFW.GLFW_KEY_RIGHT)) {
                if (!gameBoard.checkBorderXBack(currentBlockPosition, currentBlock)) {
                    if (gameBoard.checkCollision(currentBlockPosition, currentBlock, new Vector3f(-1, 0, 0))) {
                        currentBlockPosition = Vector3f.add(currentBlockPosition, new Vector3f(-1, 0, 0));
                        input.keys[GLFW.GLFW_KEY_RIGHT] = false;
                    }
                }
            }
            if (Input.isKeyDown(GLFW.GLFW_KEY_UP)) {
                if (!gameBoard.checkBorderZ(currentBlockPosition, currentBlock)) {
                    if (gameBoard.checkCollision(currentBlockPosition, currentBlock, new Vector3f(0, 0, 1))) {
                        currentBlockPosition = Vector3f.add(currentBlockPosition, new Vector3f(0, 0, 1));
                        input.keys[GLFW.GLFW_KEY_UP] = false;
                    }
                }
            }
            if (Input.isKeyDown(GLFW.GLFW_KEY_DOWN)) {
                if (!gameBoard.checkBorderZBack(currentBlockPosition, currentBlock)) {
                    if (gameBoard.checkCollision(currentBlockPosition, currentBlock, new Vector3f(0, 0, -1))) {
                        currentBlockPosition = Vector3f.add(currentBlockPosition, new Vector3f(0, 0, -1));
                        input.keys[GLFW.GLFW_KEY_DOWN] = false;
                    }
                }
            }
            //Rotacje
            if (Input.isKeyDown(GLFW.GLFW_KEY_F)){
                currentBlock.rotateByReverseYAxis();
                recalculateBoundaries(currentBlock, currentBlockPosition);
                input.keys[GLFW.GLFW_KEY_F] = false;
            }
            if (Input.isKeyDown(GLFW.GLFW_KEY_H)){
                currentBlock.rotateByYAxis();
                recalculateBoundaries(currentBlock, currentBlockPosition);
                input.keys[GLFW.GLFW_KEY_H] = false;
            }
            if (Input.isKeyDown(GLFW.GLFW_KEY_T)){
                currentBlock.rotateByXAxis();
                recalculateBoundaries(currentBlock, currentBlockPosition);
                input.keys[GLFW.GLFW_KEY_T] = false;
            }
            if (Input.isKeyDown(GLFW.GLFW_KEY_G)){
                currentBlock.rotateByReverseXAxis();
                recalculateBoundaries(currentBlock, currentBlockPosition);
                input.keys[GLFW.GLFW_KEY_G] = false;
            }

            if (timer == 144) {
                if (gameBoard.checkCollisionOnFall(currentBlockPosition, currentBlock)) {
                    gameBoard.merge(currentBlockPosition, currentBlock);
                    gameBoard.checkForGameEnd();
                    gameBoard.checkAndRemoveLayers();
                    currentBlockPosition.set(1f, 16f, 1f);
                    currentBlock = blockGenerator.GetNextBlock();
                } else {
                    currentBlockPosition.setY(currentBlockPosition.getY() - 1);
                }
                timer = 0;
            }
            timer += 1;
        }
    }

}
