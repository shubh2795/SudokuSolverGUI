package common;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SudokuLoader {
    public static char[][] board;
    public static char[] domain;
    static int boardSize;

    public boolean loadSudokuFromTextFile(String fileName, SudokuToText sudokuToText) {

        String path = "A:\\5700CS\\HW4\\Java\\SamplePuzzles\\Input\\" + fileName + ".txt";
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
                            printHelp();
                            return false;
                        }

                    }
                }
            }

            sudokuToText.print(board, boardSize);
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println(
                    "File Not Found. Please input the sudoku in a file like Puzzle-4x4-0001.txt in the samplepuzzle folder. ");
            System.out.println("Exiting...");
            printHelp();
        }

        return true;
    }

    public char[][] getSudokuBoard() {

        return board;
    }

    public char[] getDomain() {

        return domain;
    }

    public void printHelp() {
        System.out.println("Sudoku Solver Help.");
        System.out.println("\nUsage: java SudokuAlgorithms [--help|-h|-H|-v|--version] [input ...]");
        System.out.println("\nExample:\n> 9(Size of puzzle)\\\n" + "1 2 3 4 5 6 7 8 9 (Possible values) \\\n"
                + "0 0 3 0 0 0 0 0 0 \\\n" + "4 0 0 0 8 0 0 3 6 \\\n" + "0 0 8 0 0 0 1 0 0 \\\n"
                + "0 4 0 0 6 0 0 7 3 \\\n" + "0 0 0 9 0 0 0 0 0 \\\n" + "0 0 0 0 0 2 0 0 5 \\\n"
                + "0 0 4 0 7 0 0 6 8 \\\n" + "6 0 0 0 0 0 0 0 0 \\\n" + "7 0 0 6 0 0 5 0 0");

    }

}
