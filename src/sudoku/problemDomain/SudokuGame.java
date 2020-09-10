package sudoku.problemDomain;

import java.io.Serializable;

public class SudokuGame implements Serializable {
    //enum
    private final  GameState gameState;
    private final int [][] gridState;
    public static  final int GRID_BOUNDARY=9;
    public GameState getGameState() {
        return gameState;
    }

    public int[][] getGridState() {
        return SudokuUtilities.copyToNewArray(gridState);
        //to prevent original array from being changed
    }



    public SudokuGame(GameState gameState, int[][] gridState) {
        this.gameState = gameState;
        this.gridState = gridState;
    }

}
