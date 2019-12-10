import algorithms.OnlyOnePossibility;
import algorithms.SudokuSolver;
import common.SudokuLoader;
import common.SudokuToText;
import common.SudokuValidator;
import org.junit.Test;

import static org.junit.Assert.*;

public class OnlyOnePossibilityTest {

    char[][] board = new char[][]
            {
                    {'3', '-', '6', '5', '-', '8', '4', '-','-'},
                    {'5', '2', '-', '-','-', '-', '-', '-', '-'},
                    {'-','8', '7', '-', '-', '-', '-', '3', '1'},
                    {'-', '-', '3', '-', '1', '-', '-', '8', '-'},
                    {'9', '-', '-', '8', '6', '3', '-', '-', '5'},
                    {'-', '5','-', '-', '9', '-', '6', '-', '-'},
                    {'1', '3', '-', '-', '-', '-', '2', '5', '-'},
                    {'-', '-', '-', '-', '-', '-', '-', '7', '4'},
                    {'-', '-', '5', '2', '-', '6', '3', '-', '-'}
            };
    char[] domain = {'1','2','3','4','5','6','7','8','9'};

    @Test
    public void validateSudoku() {
        SudokuValidator sudokuValidator= new SudokuValidator();
        sudokuValidator.checkPerfectSquare(board);
        sudokuValidator.checkSudokuValues(board);
    }

    @Test
    public void solve() {
        SudokuSolver solver = new OnlyOnePossibility();
        SudokuLoader sudokuLoader= new SudokuLoader();
        SudokuValidator sudokuValidator= new SudokuValidator();
        SudokuToText sudokuToText= new SudokuToText();
        if(sudokuLoader.loadSudokuFromTextFile("C:\\Users\\shubh\\OneDrive\\Desktop\\Puzzle-9x9-0001.txt")){
            System.out.println("Sudoku puzzle has been loaded.");
            int size=sudokuLoader.getBoardSize();

            domain= sudokuLoader.getDomain();

            board=  sudokuLoader.getSudokuBoard();
            solver.executeAlgorithm(board,domain,sudokuValidator,sudokuToText);

        }
    }
}