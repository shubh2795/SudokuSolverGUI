package  algorithms;
import algorithms.BacktrackingAlgorithm;
import algorithms.SudokuSolver;
import common.SudokuToText;
import common.SudokuValidator;

import java.util.ArrayList;

public class OnlyOnePossibility extends SudokuSolver {

    int iters = 0;
    double start;
    double stop;

    public void validateSudoku(char[][] board, SudokuValidator sudokuValidator) {
        System.out.println("Validating Sudoku Board ");
        sudokuValidator.checkPerfectSquare(board);
        sudokuValidator.checkSudokuValues(board);
    }

    static ArrayList<Integer> list = new ArrayList<Integer>();

    public boolean solve(char[][] board, char[] domain, SudokuValidator sudokuValidator, SudokuToText sudokuToText) {
        start = System.nanoTime();
        iters++;

        int elementSum = 0;
        int domainSum = 0;

        for (int i = 0; i < domain.length; i++) {
            domainSum = domainSum + (Integer.parseInt(Character.toString(domain[i])));
        }

        for (int i = 0; i < board.length; i++) {

            for (int j = 0; j < board[i].length; j++) {

                if (board[i][j] == '-') {
                    elementSum = elementSum + 0;
                } else {
                    elementSum = elementSum + (Integer.parseInt(Character.toString(board[i][j])));
                }
            }
            list.add(elementSum);
            elementSum = 0;
        }

        for (int i = 0; i < board.length; i++) {
            int count = 0;
            for (int j = 0; j < board.length; j++) {

                if (board[i][j] == '-' && count < 2) {
                    int num = domainSum - list.get(i);
                    char possibleNum = Integer.toString(num).charAt(0);
                    board[i][j] = possibleNum;
                    count++;
                }
                if (count > 1) {
                    System.out.println("Cannot Solve this puzzle by OnlyOnePossibility.. BackTracking");
                    SudokuSolver solver = new BacktrackingAlgorithm();
                    solver.executeAlgorithm(board, domain, sudokuValidator, sudokuToText);
                    return false;
                }
            }

        }

        if (start != 0) {
            stop = System.nanoTime();
        }

        System.out.println("Time taken to execute the algorithm is " + (stop - start) / 1000);
        sudokuToText.print(board, board.length);

        try {
            sudokuToText.outputTextFile(board, iters, "Only One Possibility", (stop - start) / 1000);
        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }

}
