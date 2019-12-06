package algorithms;

import common.SudokuToText;
import common.SudokuValidator;

public class BacktrackingAlgorithm extends SudokuSolver {

    int iters = 0;

    double stop;

    public void validateSudoku(char[][] board, SudokuValidator sudokuValidator) {
        System.out.println("Validating Sudoku Board ");
        sudokuValidator.checkPerfectSquare(board);
        sudokuValidator.checkSudokuValues(board);
    }

    public boolean solve(char[][] board, char[] domain, SudokuValidator sudokuValidator, SudokuToText sudokuToText) {
        double start = System.nanoTime();
        iters++;

        for (int i = 0; i < board.length; i++) {

            for (int j = 0; j < board[0].length; j++) {

                if (board[i][j] == '-') {

                    for (char c : domain) {

                        if (sudokuValidator.isChangeValid(board, i, j, c)) {
                            board[i][j] = c;

                            if (solve(board, domain, sudokuValidator, sudokuToText)) {
                                return true;
                            } else {
                                board[i][j] = '-';
                            }
                        }
                    }

                    return false;
                }
            }
        }

        stop = System.nanoTime();

        System.out.println("Time taken to execute the algorithm is (ms) " + (stop - start) / 1000);

        sudokuToText.print(board, board.length);
        try {
            sudokuToText.outputTextFile(board, iters, "Backtracking Algorithm", (stop - start) / 1000);
        } catch (Exception e) {
            System.out.println(e);
        }

        return true;
    }

}
