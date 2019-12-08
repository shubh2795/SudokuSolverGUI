package common;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SudokuLoader {
    public static char[][] board;
    public static char[] domain;
    public static int boardSize;

    public boolean loadSudokuFromTextFile(String filePath) {

        String path = filePath;
        String sizeOfSudoku;

        try {

            Scanner scan = new Scanner(new File(path));

            if (!scan.hasNextLine()) {
                System.out.println("Empty File. Exiting...");
                scan.close();
                return false;
            }

            sizeOfSudoku = scan.next();
            boardSize = Integer.parseInt(sizeOfSudoku);
            board = new char[boardSize][boardSize];
            domain = new char[boardSize];
            System.out.println("Value of sizeOfSudoku " + sizeOfSudoku);
            System.out.println("Value of boardSize " + boardSize);

            for (int i = 0; i < boardSize; i++) {
                String newNum = scan.next();
                char c = newNum.charAt(0);
                domain[i] = c;
            }

            while (scan.hasNext()) {
                for (int i = 0; i < boardSize; i++) {

                    for (int j = 0; j < boardSize; j++) {
                        try {
                            String newNum = scan.next();
                            char c = newNum.charAt(0);
                            board[i][j] = c;
                        } catch (Exception e) {
                            System.out.println(e);
                            System.out.println("Invalid Puzzle: Improper Format");

                            return false;
                        }

                    }
                }
            }


            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println(
                    "File Not Found. Please input the sudoku in a file like Puzzle-4x4-0001.txt in the samplepuzzle folder. ");
            System.out.println("Exiting...");

        }

        return true;
    }

    public char[][] getSudokuBoard() {

        return board;
    }

    public char[] getDomain() {

        return domain;
    }


}
