import math.Vector3f;

public class GameBoard {
    private int width, height, depth;

    public int[][][] frame;

    GameBoard(int width, int height, int depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        frame = new int[width][height][depth];

    }

    public void fillGameBoard() {
        for(int x = 0; x < width;x++) {
            for(int y = 0; y < height; y++){
                for(int z = 0; z < depth; z++){
                    frame[x][y][z] = 0;
                }
            }
        }
    }

    public boolean checkBorderXBack(Vector3f blockPosition, Block block) {
        for(int x = 0; x < block.getWidth();x++) {
            for(int y = 0; y < block.getHeigth(); y++){
                for(int z = 0; z < block.getDepth(); z++){
                    if(block.pieces[x][y][z] == 1 && blockPosition.getX() + x - 1 < 0)
                        return true;
                }
            }
        }
        return false;
    }

    public boolean checkBorderZBack(Vector3f blockPosition, Block block) {
        for(int x = 0; x < block.getWidth();x++) {
            for(int y = 0; y < block.getHeigth(); y++){
                for(int z = 0; z < block.getDepth(); z++){
                    if(block.pieces[x][y][z] == 1 && blockPosition.getZ() + z - 1 < 0)
                        return true;
                }
            }
        }
        return false;
    }


    public boolean checkBorderZ(Vector3f blockPosition, Block block) {
        for(int x = 0; x < block.getWidth();x++) {
            for(int y = 0; y < block.getHeigth(); y++) {
                for(int z = 0; z < block.getDepth(); z++) {
                    if(block.pieces[x][y][z] == 1 && blockPosition.getZ() + z + 1 >= depth) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkBorderX(Vector3f blockPosition, Block block) {
        for(int x = 0; x < block.getWidth();x++) {
            for(int y = 0; y < block.getHeigth(); y++) {
                for(int z = 0; z < block.getDepth(); z++) {
                    if(block.pieces[x][y][z] == 1 && blockPosition.getX() + x + 1 >= width) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkCollision(Vector3f blockPosition, Block block, Vector3f vector) {
        Vector3f temp = blockPosition;
        temp = Vector3f.add(temp, vector);
        for(int x = 0; x < block.getWidth();x++) {
            for(int y = 0; y < block.getHeigth(); y++){
                for(int z = 0; z < block.getDepth(); z++){
                    if(block.pieces[x][y][z] == 1 && block.pieces[x][y][z] == frame[(int)temp.getX() + x][(int)temp.getY() - y - 1][(int)temp.getZ() + z]){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean checkCollisionOnFall(Vector3f blockPosition, Block block) {
        for(int x = 0; x < block.getWidth();x++) {
            for(int y = 0; y < block.getHeigth(); y++){
                for(int z = 0; z < block.getDepth(); z++){
                    if(block.pieces[x][y][z] == 1 && blockPosition.getY() - y - 1 <= 0) {
                        return true;
                    }
                    if(block.pieces[x][y][z] == 1 && block.pieces[x][y][z] == frame[(int)blockPosition.getX() + x][(int)blockPosition.getY() - y - 1][(int)blockPosition.getZ() + z]){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void clearRow(int y) {
        for(int x = 0; x < getWidth(); x++){
            for(int z = 0; z < getDepth(); z++){
                frame[x][y][z] = 0;
            }
        }
    }

    public void moveDownBoardFromAboveY(int givenHeight) {
        for(int x = 0; x < width;x++) {
            for(int y = givenHeight; y < height; y++){
                for(int z = 0; z < depth; z++){
                    if(y+1 > 11){
                        frame[x][y][z] = 0;
                    }
                    else{
                        frame[x][y][z] = frame[x][y+1][z];
                    }
                }
            }
        }
    }

    public boolean isRowEqual(int y, int value) {
        for(int x = 0; x < getWidth(); x++){
            for(int z = 0; z < getDepth(); z++){
                if(frame[x][y][z] != value){
                    return false;
                }
            }
        }
        return true;
    }

    public void checkForGameEnd() {
        if(!isRowEqual(13, 0)){
            System.exit(0);
        }
    }

    public void checkAndRemoveLayers() {
        for (int y = 0; y < height-1; y++) {
            if (isRowEqual(y, 1)) {
                System.out.println("Wyczyść mnie plis" + y);
                clearRow(y);
                moveDownBoardFromAboveY(y);
            }
        }
    }

    public void merge(Vector3f blockPosition, Block block) {
        for(int x = 0; x < block.getWidth();x++) {
            for(int y = 0; y < block.getHeigth(); y++){
                for(int z = 0; z < block.getDepth(); z++){
                    if(block.pieces[x][y][z] == 1){
                        frame[(int)blockPosition.getX() + x][(int)blockPosition.getY() - y][(int)blockPosition.getZ() + z] = block.pieces[x][y][z];
                    }
                }
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeigth() {
        return height;
    }

    public int getDepth() {
        return depth;
    }


}
