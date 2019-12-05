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
        SudokuValidator.checkPerfectSquare(board);
        SudokuValidator.checkSudokuValues(board);
    }

    @Test
    public void solve() {
        SudokuSolver solver = new OnlyOnePossibility();
        if(SudokuLoader.loadSudokuFromTextFile("Puzzle-4x4-0101")){
            System.out.println("Sudoku puzzle has been loaded.");
            char [] domain= SudokuLoader.getDomain();
            char [][] board=  SudokuLoader.getSudokuBoard();
            solver.executeAlgorithm(board,domain);

        }


    }
}