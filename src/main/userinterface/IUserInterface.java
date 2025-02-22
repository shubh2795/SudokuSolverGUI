package main.userinterface;

import main.problemdomain.SudokuGame;


public interface IUserInterface {


    interface EventListener {
        void onSudokuInput(int x, int y, int input);
        void onDialogClick();
    }


    //View refers to what the user can "View", or "See". In English, the word is both a noun and a verb.
    interface View {
        void setListener(IUserInterface.EventListener listener);
        //update a single square after user input
        void updateSquare(int x, int y, int input);

        //update the entire board, such as after game completion or initial execution of the program
        void updateBoard(SudokuGame game);
        void showDialog(String message);
        void showError(String message);
    }
}