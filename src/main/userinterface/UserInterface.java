package main.userinterface;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.constants.GameState;
import main.problemdomain.Coordinates;
import main.problemdomain.SudokuGame;

import java.util.HashMap;

public class UserInterface implements IUserInterface.View,
        EventHandler<KeyEvent> {
    //Size of the window
    private static final double WINDOW_Y = 725;
    private static final double WINDOW_X = 650;
    //distance between window and board
    private static final double BOARD_PADDING = 50;
    private static final double BOARD_X_AND_Y = 576;
    private static final Color WINDOW_BACKGROUND_COLOR = Color.rgb(0, 65, 86);
    private static final Color BOARD_BACKGROUND_COLOR = Color.rgb(242, 242, 248);
    private static final String SUDOKU = "Sudoku Solver";
    private static final String BY = "By ~ Shubham Swami";
    private final Stage stage;
    private final Group root;
    private final HashMap<Coordinates, SudokuTextField> textFieldCoordinates;
    private IUserInterface.EventListener listener;


    public UserInterface(Stage stage) {
        this.stage = stage;
        this.root = new Group();
        this.textFieldCoordinates = new HashMap<>();
        initializeUserInterface();
    }


    @Override
    public void setListener(IUserInterface.EventListener listener) {
        this.listener = listener;
    }

    public void initializeUserInterface() {
        drawBackground(root);
        drawTitle(root);
        drawSudokuBoard(root);
        drawTextFields(root);
        drawGridLines(root);
        stage.show();
    }


    private void drawTextFields(Group root) {
        //where to start drawing the numbers
        final int xOrigin = 50;
        final int yOrigin = 50;
        //how much to move the x or y value after each loop
        final int xAndYDelta = 64;


        for (int xIndex = 0; xIndex < 9; xIndex++) {
            for (int yIndex = 0; yIndex < 9; yIndex++) {
                int x = xOrigin + xIndex * xAndYDelta;
                int y = yOrigin + yIndex * xAndYDelta;
                //draw it
                SudokuTextField tile = new SudokuTextField(xIndex, yIndex);


                styleSudokuTile(tile, x, y);
                tile.setOnKeyPressed(this);

                textFieldCoordinates.put(new Coordinates(xIndex, yIndex), tile);

                root.getChildren().add(tile);
            }
        }
    }


    private void styleSudokuTile(SudokuTextField tile, double x, double y) {
        Font numberFont = new Font(32);
        tile.setFont(numberFont);
        tile.setAlignment(Pos.CENTER);

        tile.setLayoutX(x);
        tile.setLayoutY(y);
        tile.setPrefHeight(64);
        tile.setPrefWidth(64);

        tile.setBackground(Background.EMPTY);
    }


    private void drawGridLines(Group root) {
        //draw vertical lines starting at 114x and 114y:
        int xAndY = 114;
        int index = 0;
        while ( index < 8 ) {
            int thickness;
            if (index == 2 || index == 5) {
                thickness = 3;
            } else {
                thickness = 2;
            }

            Rectangle verticalLine = getLine(
                    xAndY + 64 * index,
                    BOARD_PADDING,
                    BOARD_X_AND_Y,
                    thickness
            );

            Rectangle horizontalLine = getLine(
                    BOARD_PADDING,
                    xAndY + 64 * index,
                    thickness,
                    BOARD_X_AND_Y
            );

            root.getChildren().addAll(
                    verticalLine,
                    horizontalLine
            );

            index++;
        }
    }


    public Rectangle getLine(double x, double y, double height, double width) {
        Rectangle line = new Rectangle();

        line.setX(x);
        line.setY(y);

        line.setHeight(height);
        line.setWidth(width);

        line.setFill(Color.BLACK);
        return line;

    }


    private void drawBackground(Group root) {
        Scene scene = new Scene(root, WINDOW_X, WINDOW_Y);
        scene.setFill(WINDOW_BACKGROUND_COLOR);
        stage.setScene(scene);
    }


    private void drawSudokuBoard(Group root) {
        Rectangle boardBackground = new Rectangle();
        boardBackground.setX(BOARD_PADDING);
        boardBackground.setY(BOARD_PADDING);
        boardBackground.setWidth(BOARD_X_AND_Y);
        boardBackground.setHeight(BOARD_X_AND_Y);
        boardBackground.setFill(BOARD_BACKGROUND_COLOR);
        root.getChildren().add(boardBackground);
    }

    private void drawTitle(Group root) {
        Text title = new Text(235, 690, SUDOKU);
        title.setFill(Color.WHITE);
        Font titleFont = new Font(43);
        title.setFont(titleFont);
        root.getChildren().add(title);

        Text by = new Text(245, 705, BY);
        by.setFill(Color.WHITE);

        Font byFont = new Font(25);

        title.setFont(byFont);
        root.getChildren().add(by);
    }


    @Override
    public void updateSquare(int x, int y, int input) {
        SudokuTextField tile = textFieldCoordinates.get(new Coordinates(x, y));
        String value = Integer.toString(
                input
        );

        if (value.equals("0")) value = "";

        tile.textProperty().setValue(value);
    }

    @Override
    public void updateBoard(SudokuGame game) {
        for (int xIndex = 0; xIndex < 9; xIndex++) {
            for (int yIndex = 0; yIndex < 9; yIndex++) {
                TextField tile = textFieldCoordinates.get(new Coordinates(xIndex, yIndex));

                String value = Integer.toString(
                        game.getCopyOfGridState()[xIndex][yIndex]
                );

                if (value.equals("0")) value = "";
                tile.setText(
                        value
                );

                //If a given tile has a non-zero value and the state of the game is GameState.NEW, then mark
                //the tile as read only. Otherwise, ensure that it is NOT read only.
                if (game.getGameState() == GameState.NEW) {
                    if (value.equals("")) {
                        tile.setStyle("-fx-opacity: 1;");
                        tile.setDisable(false);
                    } else {
                        tile.setStyle("-fx-opacity: 0.8;");
                        tile.setDisable(true);
                    }
                }
            }
        }
    }

    @Override
    public void showDialog(String message) {
        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION, message, ButtonType.OK);
        dialog.showAndWait();

        if (dialog.getResult() == ButtonType.OK) listener.onDialogClick();
    }

    @Override
    public void showError(String message) {
        Alert dialog = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        dialog.showAndWait();
    }


    @Override
    public void handle(KeyEvent event) {
        if (event.getEventType() == KeyEvent.KEY_PRESSED) {
            if (event.getText().equals("0")
                    || event.getText().equals("1")
                    || event.getText().equals("2")
                    || event.getText().equals("3")
                    || event.getText().equals("4")
                    || event.getText().equals("5")
                    || event.getText().equals("6")
                    || event.getText().equals("7")
                    || event.getText().equals("8")
                    || event.getText().equals("9")
            ) {
                int value = Integer.parseInt(event.getText());
                handleInput(value, event.getSource());
            } else if (event.getCode() == KeyCode.BACK_SPACE) {
                handleInput(0, event.getSource());
            } else {
                ((TextField) event.getSource()).setText("");
            }
        }

        event.consume();
    }


    private void handleInput(int value, Object source) {
        listener.onSudokuInput(
                ((SudokuTextField) source).getX(),
                ((SudokuTextField) source).getY(),
                value
        );
    }
}
