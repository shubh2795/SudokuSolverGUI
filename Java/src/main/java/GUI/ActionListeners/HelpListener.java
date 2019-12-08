package GUI.ActionListeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpListener implements ActionListener {
    String helpMessage = "Example:"+"\n"+"\n"+"9 --> (Size of puzzle)"+"\n"+"\n"+"\" 1  2  3  4  5  6  7  8  9  --> (Possible values)"  +"\n"+"\n"+ " -  -  3  1  4  -  7  -  5 " +"\n" +" 4  5  -  2  8  -  -  3  6 "+"\n"+ " 5  -  8  4  -  9   1  -  7 " +"\n" +
            " -  -  4  8  -  6  -  7  3" +"\n"+ " 8  1  -  9  4  -  -  3  - " +"\n"+ " -  -  -  7  6  2  -  -  5  "+"\n" +
            " -   5   4   -  7  -  -  6  8  " +"\n"+ " 6  -  -  -  4  -  1  8  -  " + "\n"+ " 7  -   -  6  -   -  5  -  -"+"\n"+"\n"+ "Replace blank values with '-' and save a file like Puzzle.txt"+"\n"+"Only Txt files accepted";
    public void actionPerformed(ActionEvent event){
        JOptionPane.showMessageDialog(null, helpMessage, "Sudoku Solver Help.", 1);
    }
}
