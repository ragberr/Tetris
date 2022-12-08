import math.Vector3f;
import objects.GameObject;

public class Block {
    private int width = 4, height = 4, depth = 4;
    public int[][][] pieces;
    Block(int[][][] pieces) {
        this.pieces = pieces;
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

    public void rotateByXAxis() {
        int[][][] temp = new int[getWidth()][getHeigth()][getDepth()];
        for(int x = 0; x < getWidth();x++) {
            for(int y = 0; y < getHeigth(); y++) {
                for(int z = 0; z < getDepth(); z++) {
                    temp[x][y][z] = pieces[x][height - 1 - z][y];
                }
            }
        }
        pieces = temp;
    }

    public void rotateByReverseXAxis() {
        int[][][] temp = new int[getWidth()][getHeigth()][getDepth()];
        for(int x = 0; x < getWidth();x++) {
            for(int y = 0; y < getHeigth(); y++) {
                for(int z = 0; z < getDepth(); z++) {
                    temp[x][y][z] = pieces[x][z][depth - 1 - y];
                }
            }
        }
        pieces = temp;
    }

    public void rotateByYAxis() {
        int[][][] temp = new int[getWidth()][getHeigth()][getDepth()];
        for(int x = 0; x < getWidth();x++) {
            for(int y = 0; y < getHeigth(); y++) {
                for(int z = 0; z < getDepth(); z++) {
                    temp[x][y][z] = pieces[width - 1 - z][y][x];
                }
            }
        }
        pieces = temp;
    }

    public void rotateByReverseYAxis() {
        int[][][] temp = new int[getWidth()][getHeigth()][getDepth()];
        for(int x = 0; x < getWidth();x++) {
            for(int y = 0; y < getHeigth(); y++) {
                for(int z = 0; z < getDepth(); z++) {
                    temp[x][y][z] = pieces[z][y][depth - 1 - x];
                }
            }
        }
        pieces = temp;
    }
}
