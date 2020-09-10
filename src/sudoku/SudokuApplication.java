package sudoku;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SudokuApplication extends Application {
    private IUserInterface.View ui;
    @Override
    public void start(Stage primaryStage) throws Exception{
        ui = new UserInterface(primaryStage);
        try {
            SudokuBuildLogic.build(ui);
        }
        catch (IOException e){
            e.printStackTrace();
            throw e;
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
