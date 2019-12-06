package common;
import java.io.*;
import java.util.Scanner;

public class SudokuToText {
    public static String outputFileName;

    public void print(char[][] board, int N) {
        // print sudoku
        // System.out.println("Solution:");
        for (int r = 0; r < N; r++) {
            for (int d = 0; d < N; d++) {
                System.out.print(board[r][d]);
                System.out.print(" ");
            }
            System.out.print("\n");

            if ((r + 1) % (int) Math.sqrt(N) == 0) {
                System.out.print("");
            }
        }
    }

    public void outputTextFile(char board[][], int iters, String strategy, double executionTime) throws IOException {

        try {

            File file = new File("A:\\5700CS\\HW4\\Java\\SamplePuzzles\\out\\" + outputFileName + ".txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write("Solution");
            bw.newLine();

            for (int i = 0; i < board.length; i++) {

                for (int j = 0; j < board.length; j++) {
                    String element = Character.toString(board[i][j]);
                    bw.write(element + " ");

                }
                bw.newLine();
            }
            bw.write("Strategy Used: " + strategy + "| Number of Uses: " + iters + "| Execution Time(ms) "
                    + executionTime);
            bw.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
