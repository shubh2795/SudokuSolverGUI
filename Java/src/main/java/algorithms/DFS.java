package algorithms;

import common.SudokuToText;
import common.SudokuValidator;

public class DFS extends SudokuSolver {
    int iters = 0;
    double start;
    double stop;

    public void validateSudoku(char[][] board, SudokuValidator sudokuValidator) {
        System.out.println("Validating Sudoku Board ");
        sudokuValidator.checkPerfectSquare(board);
        sudokuValidator.checkSudokuValues(board);
    }

    public boolean solve(char[][] board, char[] domain, SudokuValidator sudokuValidator, SudokuToText sudokuToText) {
        start = System.nanoTime();
        iters++;
        for (int col = 0; col < board.length; col++) {

            for (int row = 0; row < board[0].length; row++) {

                if (board[row][col] == '-') {

                    for (char c : domain) {

                        if (sudokuValidator.isChangeValid(board, row, col, c)) {
                            board[row][col] = c;
                            if (solve(board, domain, sudokuValidator, sudokuToText)) {
                                return true;
                            } else {
                                board[row][col] = '-';
                            }
                        }
                    }

                    return false;
                }
            }
        }

        stop = System.nanoTime();

        System.out.println("Time taken to execute the algorithm is " + (stop - start));
        sudokuToText.print(board, board.length);
        try {
            sudokuToText.outputTextFile(board, iters, "Depth First Search", (stop - start));
        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }

}
