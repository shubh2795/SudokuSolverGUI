package GUI;

import org.junit.Test;

import static org.junit.Assert.*;

public class SudokuBoardDisplayTest {

    @Test
    public void validTest() {
        SudokuGUI sudokuGUI = new SudokuGUI();
        sudokuGUI.setVisible(true);
    }
}