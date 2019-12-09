package algorithms;
import common.SudokuToText;
import common.SudokuValidator;

public abstract class SudokuSolver {

    abstract boolean solve(char[][] board, char[] domain, SudokuValidator sudokuValidator, SudokuToText sudokuToText);

    abstract void validateSudoku(char[][] board, SudokuValidator sudokuValidator);



    public final void executeAlgorithm(char[][] board, char[] domain, SudokuValidator sudokuValidator, SudokuToText sudokuToText) {
            validateSudoku(board, sudokuValidator);
            solve(board, domain, sudokuValidator, sudokuToText);

    }

}
