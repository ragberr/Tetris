package engine.util;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class FileReader {

    public FileReader() {

    }

    public static int[][][] readTo3dArray(String filePath) {
        int[][][] pieces = new int[4][4][4];
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()) {
                for(int x = 0; x < 4;x++) {
                    for(int y = 0; y < 4; y++){
                        for(int z = 0; z < 4; z++){
                            pieces[y][x][z] = scanner.nextInt();
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return pieces;
    }
}
