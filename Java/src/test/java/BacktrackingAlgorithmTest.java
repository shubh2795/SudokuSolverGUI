import algorithms.BacktrackingAlgorithm;
import algorithms.SudokuSolver;
import common.SudokuLoader;
import common.SudokuToText;
import common.SudokuValidator;
import org.junit.Test;

public class BacktrackingAlgorithmTest {

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
        SudokuSolver solver = new BacktrackingAlgorithm();
        SudokuLoader sudokuLoader= new SudokuLoader();
        SudokuValidator sudokuValidator= new SudokuValidator();
        SudokuToText sudokuToText= new SudokuToText();
        if(sudokuLoader.loadSudokuFromTextFile("Puzzle-4x4-0101")){
            System.out.println("Sudoku puzzle has been loaded.");
            char [] domain= sudokuLoader.getDomain();
            char [][] board=  sudokuLoader.getSudokuBoard();
            solver.executeAlgorithm(board,domain,sudokuValidator,sudokuToText);
        }

    char [][] solvedBoard = new char[][]
            {
                    {'3', '1', '6', '5', '7', '8', '4', '9','2'},
                    {'5', '2', '9', '1','3', '4', '7', '6', '8'},
                    {'4','8', '7', '6', '2', '9', '5', '3', '1'},
                    {'2', '6', '3', '4', '1', '5', '9', '8', '7'},
                    {'9', '7', '4', '8', '6', '3', '1', '2', '5'},
                    {'8', '5','1', '7', '9', '2', '6', '4', '3'},
                    {'1', '3', '8', '9', '4', '7', '2', '5', '6'},
                    {'6', '9', '2', '3', '5', '1', '8', '7', '4'},
                    {'7', '4', '5', '2', '8', '6', '3', '1', '9'}
            };


    }


}