import common.SudokuLoader;
import org.junit.Test;

import static org.junit.Assert.*;

public class SudokuLoaderTest {

    @Test
    public void loadSudokuFromTextFile() {
        SudokuLoader sudokuLoader= new SudokuLoader();
        String fileName="C:\\Users\\shubh\\OneDrive\\Desktop\\Puzzle-9x9-0001.txt";
        boolean fileLoaded =   sudokuLoader.loadSudokuFromTextFile(fileName);
        assertEquals(fileLoaded,true);
        int boardlength = SudokuLoader.boardSize;
        assertEquals(boardlength,9);


    }

    @Test
    public void getSudokuBoard() {
        String fileName="C:\\Users\\shubh\\OneDrive\\Desktop\\Puzzle-9x9-0001.txt";
        SudokuLoader sudokuLoader= new SudokuLoader();
        boolean fileLoaded =   sudokuLoader.loadSudokuFromTextFile(fileName);
        assertEquals(fileLoaded,true);
        char[][] board = new char[][]
                {
                        {'4','9','-','1','3','6','7','-','8'},
                        {'-','6','3','5','-','-','-','9','-'},
                        {'5','-','-','-','2','9','3','6','4'},
                        {'-','2','-','3','1','-','-','4','-'},
                        {'-','7','4','-','-','-','2','1','-'},
                        {'-','-','1','-','6','4','-','8','-'},
                        {'1','8','6','9','-','-','-','2','5'},
                        {'-','4','-','-','5','1','8','3','-'},
                        {'3','-','9','4','8','-','-','7','-'}
                };
        assertArrayEquals(board,sudokuLoader.getSudokuBoard());
    }

    @Test
    public void getDomain() {
        String fileName="C:\\Users\\shubh\\OneDrive\\Desktop\\Puzzle-9x9-0001.txt";
        SudokuLoader sudokuLoader= new SudokuLoader();
        boolean fileLoaded =   sudokuLoader.loadSudokuFromTextFile(fileName);
        assertEquals(fileLoaded,true);
        char[] domain = new char[]{'1', '2', '3', '4','5','6','7','8','9'};
        assertArrayEquals(domain,sudokuLoader.getDomain());
    }


}