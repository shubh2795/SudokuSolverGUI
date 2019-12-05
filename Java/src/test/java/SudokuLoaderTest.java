import org.junit.Test;

import static org.junit.Assert.*;

public class SudokuLoaderTest {

    @Test
    public void loadSudokuFromTextFile() {
        String fileName="Puzzle-4x4-0001";
        boolean fileLoaded =   SudokuLoader.loadSudokuFromTextFile(fileName);
        assertEquals(fileLoaded,true);
        int boardlength = SudokuLoader.boardSize;
        assertEquals(boardlength,4);


    }

    @Test
    public void getSudokuBoard() {
        String fileName="Puzzle-4x4-0001";
        boolean fileLoaded =   SudokuLoader.loadSudokuFromTextFile(fileName);
        assertEquals(fileLoaded,true);
        char[][] board = new char[][]
                {
                        {'2', '-', '3', '1'},
                        {'1', '3', '-', '4'},
                        {'3','1', '4', '-'},
                        {'4', '2', '-', '3'}
                };
        assertArrayEquals(board,SudokuLoader.getSudokuBoard());
    }

    @Test
    public void getDomain() {
        String fileName="Puzzle-4x4-0001";
        boolean fileLoaded =   SudokuLoader.loadSudokuFromTextFile(fileName);
        assertEquals(fileLoaded,true);
        char[] domain = new char[]{'1', '2', '3', '4'};
        assertArrayEquals(domain,SudokuLoader.getDomain());
    }

    @Test
    public void testPrintHelp() {
        SudokuLoader sudokuLoader= new SudokuLoader();
        sudokuLoader.printHelp();
    }
}