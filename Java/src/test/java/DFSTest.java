import org.junit.Test;

import static org.junit.Assert.*;

public class DFSTest {

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
        SudokuSolver solver = new DFS();
        if(SudokuLoader.loadSudokuFromTextFile("Puzzle-4x4-0101")){
            System.out.println("Sudoku puzzle has been loaded.");
            char [] domain= SudokuLoader.getDomain();
            char [][] board=  SudokuLoader.getSudokuBoard();
            solver.executeAlgorithm(board,domain);
        }
//        SudokuSolver solver = new DFS();
//        solver.executeAlgorithm(board,domain);
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

        //   assertEquals(true,solver.executeAlgorithm(board,domain));
    }
}