package GUI;



public class SudokuModel {

    int BOARD_SIZE ;
    char[][] _board;

   public SudokuModel(char [] [] initialBoard) {
       this._board = initialBoard;
       BOARD_SIZE = _board.length;
        initializeFromString(_board);

    }


    public void initializeFromString( char [] [] boardStr) {

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                setVal(row, col, boardStr[row][col]);
            }
        }

    }


    public void setVal(int r, int c, char v) {
        _board[r][c] = v;
    }


    public int getVal(int row, int col) {
        return _board[row][col];
    }


    }
