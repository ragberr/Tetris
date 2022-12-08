import engine.util.FileReader;
import graphic.Mesh;
import graphic.Vertex;
import math.Vector3f;
import objects.GameObject;

import java.util.LinkedList;
import java.util.List;

public class RandomBlockGenerator {
    private List<Block> blockList = new LinkedList<>();
    private FileReader fileReader = new FileReader();



    RandomBlockGenerator() {
    }

    Block CreateNewBlock(int type) {
        int[][][] pieces = new int[4][4][4];
        pieces = fileReader.readTo3dArray("src\\main\\blocks\\" + type + ".txt");

        Block block = new Block(pieces);
        return block;
    }

    void GenerateNewList() {
        List<Block> list = new LinkedList<>();
        for(int i = 0; i <= 2; i++) {
            list.add(CreateNewBlock(i));
        }
        this.blockList = list;
    }

    Block GetNextBlock() {
        if(this.blockList.isEmpty()){
            GenerateNewList();
        }
        Block block = this.blockList.get(0);
        this.blockList.remove(0);
        return block;
    }


}
