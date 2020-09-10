package sudoku.userInterface;

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
import sudoku.constants.GameState;
import sudoku.problemDomain.Coordinates;
import sudoku.problemDomain.SudokuGame;

import java.util.HashMap;

import static sudoku.constants.Messages.ERROR;
import static sudoku.constants.Messages.GAME_COMPLETE;

public class UserInterface implements IUserInterface.View, EventHandler<KeyEvent> {
    private static final double WINDOW_X = 732;
    private static final double WINDOW_y = 668;
    private static final double BOARD_PADDING = 50;
    private static final double BOARD_X_Y = 50;
    private final String SUDOKU_TITLE = "Sudoku Application";

    private final Color WINDOW_BACKGROUND = Color.rgb(0, 150, 136);
    private final Color BOARD_BACKGROUND = Color.rgb(224, 242, 241);

    private final Stage stage;
    private final Group root;
    private final HashMap<Coordinates, SudokuTextField> sudokuCoordinates;
    private IUserInterface.EventListener listener;

    public UserInterface(Stage stage) {
        this.stage = stage;
        this.root = new Group();
        this.sudokuCoordinates = new HashMap<>();
        intitialiseUserInterface();
    }

    private void intitialiseUserInterface() {
        drawBackground(root);
        drawTitle(root);
        drawSudokuBoard(root);
        drawTextFields(root);
        drawGridLines(root);
        stage.show();
    }

    private void drawSudokuBoard(Group root) {
        Rectangle boardBackground = new Rectangle();
        boardBackground.setX(BOARD_PADDING);
        boardBackground.setY(BOARD_PADDING);
        boardBackground.setWidth(BOARD_X_Y);
        boardBackground.setHeight(BOARD_X_Y);
        boardBackground.setFill(BOARD_BACKGROUND);
        root.getChildren().addAll(boardBackground);


    }

    private void drawTitle(Group root) {

        Text title = new Text(235, 690, SUDOKU_TITLE);
        title.setFill(Color.WHITE);
        Font titleFont = new Font(42);
        title.setFont(titleFont);
        root.getChildren().add(title);

    }

    private void drawTextFields(Group root) {
        final int xOrigin = 50;
        final int yOrigin = 50;
        final int xAndyDelta = 64;

        for (int xIndex = 0; xIndex < 9; xIndex++) {
            for (int yIndex = 0; yIndex < 9; yIndex++) {
                int x = xIndex * xAndyDelta + xOrigin;
                int y = yIndex * xAndyDelta + yOrigin;

                SudokuTextField tile = new SudokuTextField(xIndex, yIndex);
                styleSudokuTile(tile, x, y);
                tile.setOnKeyPressed(this);
                sudokuCoordinates.put(new Coordinates(xIndex, yIndex), tile);
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
        tile.setBackground(Background.EMPTY);//transparency
    }

    private void drawGridLines(Group root) {
        int xAndY = 114;
        int index = 0;
        while ( index < 8 ) {
            int thickness;
            if (index == 2 || index == 5)
                thickness = 3;
            else
                thickness = 2;
            Rectangle verticalLine = getLine(xAndY + 64 * index, BOARD_PADDING, BOARD_X_Y, thickness);
            Rectangle horizontalLine = getLine(BOARD_PADDING, xAndY + 64 * index, thickness, BOARD_X_Y);
            root.getChildren().addAll(verticalLine, horizontalLine);
            index++;
        }


    }

    private Rectangle getLine(double x, double y, double height, double width) {
        Rectangle line = new Rectangle();
        line.setX(x);
        line.setY(y);
        line.setHeight(height);
        line.setWidth(width);
        line.setFill(Color.BLACK);
        return line;
    }

    private void drawBackground(Group root) {
        Scene scene = new Scene(root, WINDOW_X, WINDOW_y);
        scene.setFill(WINDOW_BACKGROUND);
        stage.setScene(scene);
    }

    @Override
    public void setListener(IUserInterface.EventListener listener) {
        this.listener = listener;
    }

    @Override
    public void updateSquare(int x, int y, int input) {
        SudokuTextField tile = sudokuCoordinates.get(new Coordinates(x, y));
        String value = Integer.toString(input);
        if (value.equals("0"))
            value = "";
        tile.textProperty().setValue(value);
    }

    @Override
    public void updateBoard(SudokuGame game) {
        for (int xIndex = 0; xIndex < 9; xIndex++) {
            for (int yIndex = 0; yIndex < 9; yIndex++) {
                TextField tile = sudokuCoordinates.get(new Coordinates(xIndex, yIndex));
                String value = Integer.toString(game.getGridState()[xIndex][yIndex]);
                if (value.equals("0"))
                    value = "";

                tile.setText(value);

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
    public void showDialog(String Message) {
        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION, GAME_COMPLETE, ButtonType.OK);
        dialog.showAndWait();
        if (dialog.getResult() == ButtonType.OK)
            listener.onDialogClick();

    }

    @Override
    public void showError(String Error) {
        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION, ERROR, ButtonType.OK);
        dialog.showAndWait();

    }


    @Override
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED) {
            if (keyEvent.getText().matches("[0-9]")) {
                int value = Integer.parseInt(keyEvent.getText());
                handleInput(value, keyEvent.getSource());
            } else if (keyEvent.getCode() == KeyCode.BACK_SPACE) {
                handleInput(0, keyEvent.getSource());
            } else {
                ((TextField) keyEvent.getSource()).setText("");
            }
        }
        keyEvent.consume();//propagation to rest of app is prevented.
    }

    private void handleInput(int value, Object source) {
        listener.onSudokuInput(
                ((SudokuTextField) source).getX(),
                ((SudokuTextField) source).getY(),
                value
        );
    }


}

