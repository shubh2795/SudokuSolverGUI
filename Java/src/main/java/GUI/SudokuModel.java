package GUI;



public class SudokuModel {

    private static  int boardSize=9;
    private int[][] _board;

    public SudokuModel() {
        _board = new int[boardSize][boardSize];
    }


    public SudokuModel(String initialBoard) {
        this();
        initializeFromString(initialBoard);
    }

    public void initializeFromString( String boardStr) {
        clear();
        int row = 0;
        int col = 0;

        for (int i = 0; i < boardStr.length(); i++) {
            char c = boardStr.charAt(i);
            if (c >= '1' && c <='9') {
                if (row > boardSize || col > boardSize) {
                    throw new IllegalArgumentException("SudokuModel: "
                            + " Attempt to initialize outside 1-9 "
                            + " at row " + (row+1) + " and col " + (col+1));
                }
                _board[row][col] = c - '0';  // Translate digit to int.
                col++;
            } else if (c == '-') {
                col++;
            } else if (c == '/') {
                row++;
                col = 0;
            } else {
                throw new IllegalArgumentException("SudokuModel: Character '" + c
                        + "' not allowed in board specification");
            }
        }
    }


    public boolean isLegalMove(int row, int col, int val) {
        return row>=0 && row< boardSize && col>=0 && col< boardSize
                && val>0 && val<=9 && _board[row][col]==0;
    }


    public void setVal(int r, int c, int v) {
        _board[r][c] = v;
    }


    public int getVal(int row, int col) {
        return _board[row][col];
    }


    public void clear() {
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                setVal(row, col, 0);
            }
        }
    }
}