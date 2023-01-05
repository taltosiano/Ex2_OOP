import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;

public class Ex2_1 {

    public static void main(String[] args) {
        String[] fileNames = createTextFiles(1000, 12, 100);

            System.out.print("Total number of lines without thread: ");
            long startTime = System.currentTimeMillis();
            System.out.print(getNumOfLines(fileNames) + ".   ");
            long endTime = System.currentTimeMillis();
            NumberFormat formatter = new DecimalFormat("#0.00000");
            System.out.print("Time: " + formatter.format((endTime - startTime) / 1000d) + " seconds" + "\n");

            System.out.print("Total number of lines with thread: ");
            startTime = System.currentTimeMillis();
            System.out.print(getNumOfLinesThreads(fileNames) + ".      ");
            endTime = System.currentTimeMillis();
            System.out.print("Time: " + formatter.format((endTime - startTime) / 1000d) + " seconds" + "\n");

            System.out.print("Total number of lines with threadPool: ");
            startTime = System.currentTimeMillis();
            System.out.print(getNumOfLinesThreadPool(fileNames) + ".  ");
            endTime = System.currentTimeMillis();System.out.print("Time: " + formatter.format((endTime - startTime) / 1000d) + " seconds" + "\n");
        }


    public static String[] createTextFiles(int n, int seed, int bound) {  // n files,
        Random rand = new Random(seed);
        String[] file_names = new String[n];      // Initializing a String array that will be returned with size of: number of files.

        String folderPath = "./TheOutPut_files";//name of folder to store the files in it
        File folder = new File(folderPath);
        folder.mkdir();

        try {
            for (int i = 1; i <= n; i++) {   // writing n files
                String fileName = "file_" + i + ".txt";
                String filePath = folderPath + "/" + fileName;
                int rows_num = rand.nextInt(bound);

                FileWriter fileW = new FileWriter(filePath);
                //       FileWriter fileW = new FileWriter("./src/files_i/file_" + i + ".txt");
                file_names[i - 1] = "file_" + i + ".txt";

                for (int j = 0; j < rows_num; j++)
                    fileW.write("Hello World\n");
                fileW.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file_names;
    }

    public static int getNumOfLines(String[] fileNames) {
    int ans = 0;
        for (int i=0; i < fileNames.length; i++) {
        File file = new File(fileNames[i]);
            String folderPath = "./TheOutPut_files";
            String filePath = folderPath + "/" + file;
        try {
            FileReader fileReader = new FileReader(filePath);
            Scanner scanner = new Scanner(fileReader);
            while(scanner.hasNextLine()){
                scanner.nextLine();
                ans++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
        return ans;
}

    public static int getNumOfLinesThreads(String[] fileNames) {
    //  NumOfLinesThreads thread = new NumOfLinesThreads(fileNames.toString());
        NumOfLinesThreads[] threads = new NumOfLinesThreads[fileNames.length];
        int numOfLines = 0;
        try {
        for (int i = 0; i < fileNames.length; i++) {
            threads[i] = new NumOfLinesThreads(fileNames[i]);
            threads[i].start();
            threads[i].join();
        }
        for (NumOfLinesThreads thread : threads) {
                numOfLines += thread.getNumRows();
            }
        } catch (InterruptedException e) {
                e.printStackTrace();
            }

        return numOfLines;
    }

//        int counter = 0;
//        for (int i = 0; i < fileNames.length; i++) {
//            NumOfLinesThreads l = new NumOfLinesThreads(fileNames[i]);
//            l.start();
//            try {
//                l.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            counter += l.getNumRows();
//        }
//
//        return counter;
//    }

    public static int getNumOfLinesThreadPool(String[] fileNames) {
        ExecutorService executorService = Executors.newFixedThreadPool(fileNames.length);
        List<Callable<Integer>> tasks = new ArrayList<>();
        int numOfLines = 0;
            for (String fileName : fileNames) {
                tasks.add(new NumOfLinesThreadPool(fileName));
            }
            List<Future<Integer>> numInOneCall = null;
        try {
            numInOneCall = executorService.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Future<Integer> rows : numInOneCall)
            try {
                numOfLines += rows.get();
            } catch (InterruptedException | ExecutionException e){
                e.printStackTrace();
            }

        executorService.shutdown();
        return numOfLines;
    }
}