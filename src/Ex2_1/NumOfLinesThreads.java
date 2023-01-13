package Ex2_1;

import java.io.BufferedReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Thread;

public class NumOfLinesThreads extends Thread {
    private final String fileName;
    private int numRows;


    public NumOfLinesThreads(String fileName){
        this.fileName = fileName;
        this.numRows = 0;
    }

    public int getNumRows() {
        return numRows;
    }

    @Override
    public void run(){
        String folderPath = "./TheOutPut_files";
        String filePath = folderPath + "/" + this.fileName;
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader);
            while (reader.readLine() != null) {
                numRows++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
