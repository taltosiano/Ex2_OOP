package Ex2_1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;

public class NumOfLinesThreadPool implements Callable <Integer>{
    private String fileName;
    private int numRows;

    public NumOfLinesThreadPool(String fileName){
        this.fileName = fileName;
        this.numRows = 0;
    }

    public int getNumRows() {
        return numRows;
    }

    @Override
    public Integer call(){
        String folderPath = "./TheOutPut_files";
        String filePath = folderPath + "/" + this.fileName;
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader);
            while (reader.readLine() != null){
                numRows++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return numRows;
    }

}