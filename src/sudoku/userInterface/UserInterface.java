package sudoku.userInterface;

import sudoku.problemDomain.SudokuGame;

import java.awt.event.KeyEvent;
import java.beans.EventHandler;

public class UserInterface implements IUserInterface.View {
    @Override
    public void setListener(IUserInterface.EventListener listener) {
        
    }

    @Override
    public void updateSquare(int x, int y, int input) {

    }

    @Override
    public void updateBoard(SudokuGame game) {

    }

    @Override
    public void showDialog(String Message) {

    }

    @Override
    public void showError(String Error) {

    }

    EventHandler<KeyEvent>
}
