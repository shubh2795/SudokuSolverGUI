package main.buildLogic;

import main.computationLogic.GameLogic;
import main.persistence.LocalStorage;
import main.problemdomain.Storage;
import main.problemdomain.SudokuGame;
import main.userinterface.IUserInterface;
import main.userinterface.logic.ControlLogic;

import java.io.IOException;

public class SudokuBuildLogic {


    public static void build(IUserInterface.View userInterface) throws IOException {
        SudokuGame initialState;
        Storage storage = new LocalStorage();

        try {
            //will throw if no game data is found in local storage

            initialState = storage.getGameData();
        } catch (IOException e) {

            initialState = GameLogic.getNewGame();
            //this method below will also throw an IOException
            //if we cannot update the game data. At this point
            //the application is considered unrecoverable
            storage.updateGameData(initialState);
        }

        IUserInterface.EventListener uiLogic = new ControlLogic(storage, userInterface);
        userInterface.setListener(uiLogic);
        userInterface.updateBoard(initialState);
    }
}
