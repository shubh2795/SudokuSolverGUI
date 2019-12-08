import java.util.Scanner;
import algorithms.*;
import common.SudokuLoader;
import common.SudokuToText;
import common.SudokuValidator;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the input sudoku puzzle file name");
        String fileName = scanner.nextLine();
        System.out.println("Please enter the output sudoku puzzle file name");
        common.SudokuToText.outputFileName = scanner.nextLine();
        System.out.println("Select the algorithm for solving Sudoku ");
        System.out.println("Enter 1 for Backtracking Algorithm");
        System.out.println("Enter 2 Only One Possibility ");
        System.out.println("Enter 3 DFS ");
        System.out.println("Enter 4 for Stochastic Search Algorithm ");
        int algorithmType = scanner.nextInt();
        scanner.close();
        SudokuLoader sudokuLoader = new SudokuLoader();
        SudokuToText sudokuToText = new SudokuToText();
        SudokuValidator sudokuValidator = new SudokuValidator();

        if (algorithmType == 1) {
            SudokuSolver solver = new BacktrackingAlgorithm();
            if (sudokuLoader.loadSudokuFromTextFile(fileName)) {
                System.out.println("Sudoku puzzle has been loaded.");
                char[] domain = sudokuLoader.getDomain();
                char[][] board = sudokuLoader.getSudokuBoard();
                solver.executeAlgorithm(board, domain, sudokuValidator, sudokuToText);
            }
        } else if (algorithmType == 2) {
            SudokuSolver solver = new OnlyOnePossibility();
            if (sudokuLoader.loadSudokuFromTextFile(fileName)) {
                System.out.println("Sudoku puzzle has been loaded.");
                char[] domain = sudokuLoader.getDomain();
                char[][] board = sudokuLoader.getSudokuBoard();
                solver.executeAlgorithm(board, domain, sudokuValidator, sudokuToText);
            }
        } else if (algorithmType == 3) {
            SudokuSolver solver = new DFS();
            if (sudokuLoader.loadSudokuFromTextFile(fileName)) {
                System.out.println("Sudoku puzzle has been loaded.");
                char[] domain = sudokuLoader.getDomain();
                char[][] board = sudokuLoader.getSudokuBoard();
                solver.executeAlgorithm(board, domain, sudokuValidator, sudokuToText);
            }
        } else if (algorithmType == 4) {
            SudokuSolver solver = new StochasticSearchAlgorithm();
            if (sudokuLoader.loadSudokuFromTextFile(fileName)) {
                System.out.println("Sudoku puzzle has been loaded.");
                char[] domain = sudokuLoader.getDomain();
                char[][] board = sudokuLoader.getSudokuBoard();
                solver.executeAlgorithm(board, domain, sudokuValidator, sudokuToText);
            }
        }

    }
}
