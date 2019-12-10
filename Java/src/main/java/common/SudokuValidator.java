package common;
public class SudokuValidator {

    public boolean isChangeValid(char[][] board, int row, int col, char c) {

        int sqrt = (int) Math.sqrt(SudokuLoader.boardSize);

        for (int i = 0; i < SudokuLoader.boardSize; i++) {
            if (board[i][col] != '-' && board[i][col] == c)
                return false;

            if (board[row][i] != '-' && board[row][i] == c)
                return false;

            if (board[sqrt * (row / sqrt) + i / sqrt][sqrt * (col / sqrt) + i % sqrt] != '-'
                    && board[sqrt * (row / sqrt) + i / sqrt][sqrt * (col / sqrt) + i % sqrt] == c)
                return false; // check squares
        }

        return true;
    }

    public void checkPerfectSquare(char[][] board) {
        double sqrt = Math.sqrt(board.length);
        if ((sqrt - Math.floor(sqrt)) != 0) {
            System.out.println("Not a valid puzzle: Input boards of perfect squares");
            System.out.println("Exiting...");
            System.exit(1);
        }
    }

    public void checkSudokuValues(char[][] board) {
        for (int i = 0; i < board.length; i++) {

            for (int j = 0; j < board[0].length; j++) {
                String element = Character.toString(board[i][j]);
                if (element.isEmpty()
                        || ((Character.isLetterOrDigit(element.charAt(0)) == false) && !element.equals("-"))) {
                    System.out.println(
                            "Not a valid puzzle: Input puzzle with Letters or numbers and replace blanks with - ");
                    System.out.println("Exiting...");
                    System.exit(1);
                }

            }

        }
    }

}
