package algorithms;
import common.SudokuToText;
import common.SudokuValidator;

import java.util.ArrayList;
import java.util.Random;

public class StochasticSearchAlgorithm extends SudokuSolver {

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
        Random random = new Random();
        ArrayList<Character> domainList = new ArrayList<>();
        for (char c : domain) {
            domainList.add(c);
        }

        for (int i = 0; i < board.length; i++) {

            for (int j = 0; j < board[0].length; j++) {

                if (board[i][j] == '-') {

                    while (domainList.size() > 0) {
                        int randomIndex = random.nextInt(domainList.size());
                        char c = domainList.get(randomIndex);
                        if (sudokuValidator.isChangeValid(board, i, j, c)) {
                            board[i][j] = c;

                            if (solve(board, domain, sudokuValidator, sudokuToText)) {
                                return true;
                            } else {
                                board[i][j] = '-';
                            }
                        }
                        domainList.remove(domainList.indexOf(c));
                    }

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
            sudokuToText.outputTextFile(board, iters, "Stochastic Search Algorithm", (stop - start) / 1000);
        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }

}
