package GUI.ActionListeners;

import common.SudokuLoader;
import common.SudokuToText;
import common.SudokuValidator;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
        try {
            String puzzleValues="";
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
            fileChooser.setFileFilter(filter);
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                String selectedFilePath = fileChooser.getSelectedFile() + "\\";
                SudokuLoader sudokuLoader = new SudokuLoader();
                SudokuToText sudokuToText = new SudokuToText();
                SudokuValidator sudokuValidator = new SudokuValidator();
                if (sudokuLoader.loadSudokuFromTextFile(selectedFilePath)) {
                    System.out.println("Sudoku puzzle has been loaded.");
                    char[] domain = sudokuLoader.getDomain();
                    char[][] board = sudokuLoader.getSudokuBoard();
                    for(int i =0;i<board.length;i++) {
                        for(int j =0;j<board.length;j++) {
                            puzzleValues=puzzleValues+board[i][j];
                        }
                    }
                    //INITIAL_BOARD = puzzleValues;
                }
            }

        } catch (NumberFormatException nfe) {
            System.out.println(nfe);
        }
    }
}