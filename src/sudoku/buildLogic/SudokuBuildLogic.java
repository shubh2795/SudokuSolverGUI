package sudoku.buildLogic;

import sudoku.computationalLogic.GameLogic;
import sudoku.persistence.LocalStorage;
import sudoku.problemDomain.Storage;
import sudoku.problemDomain.SudokuGame;
import sudoku.userInterface.IUserInterface;
import sudoku.userInterface.logic.ControlLogic;

import java.io.IOException;

public class SudokuBuildLogic {
    public static void build(IUserInterface.View userInterface) throws IOException {
        SudokuGame initialState;
        Storage storage = new LocalStorage();
        try {
            initialState = storage.getGameData();
        } catch (IOException e) {
            initialState = GameLogic.getNewGame();
            storage.updateGameData(initialState);
        }
        IUserInterface.EventListener uiLogic = new ControlLogic(storage, userInterface);
        userInterface.setListener(uiLogic);
        userInterface.updateBoard(initialState);
    }
}
