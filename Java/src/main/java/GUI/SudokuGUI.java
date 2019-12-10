package GUI;


import algorithms.BacktrackingAlgorithm;
import algorithms.OnlyOnePossibility;
import algorithms.StochasticSearchAlgorithm;
import algorithms.SudokuSolver;
import common.SudokuLoader;
import common.SudokuToText;
import common.SudokuValidator;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class SudokuGUI extends JFrame {
    int size = 0;
    private static  String initialBoard =
                    "8156----4/" +
                    "6---75-8-/" +
                    "----9----/" +
                    "9---417---/" +
                    "-4-----2-/" +
                    "--623---8/" +
                    "----5----/" +
                    "-5-91---6/" +
                    "1----7895";


    private SudokuModel sudokuLogic = new SudokuModel(initialBoard);
    private SudokuBoardDisplay boardDisplay = new SudokuBoardDisplay(sudokuLogic);

    private JTextField rowTextField = new JTextField(2);
    private JTextField colTextField = new JTextField(2);
    private JTextField valTextField = new JTextField(2);

    JRadioButton backTracking = new JRadioButton("BackTracking");
    JRadioButton stch = new JRadioButton("Stochastic Search Algorithm");
    JRadioButton onePossibility = new JRadioButton("1-Possibility");
    JButton solveBtn = new JButton("Solve");
    JButton helpBtn = new JButton("Help ?" );
    JButton setValue = new JButton("Set Value");
    JButton loadBtn = new JButton("Load File" );
    JButton resetBtn = new JButton("Reset" );
    boolean isFileSelected=false;

    public SudokuGUI() {


        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.add(new JLabel("Row (1-N):"));
        controlPanel.add(rowTextField);
        controlPanel.add(new JLabel("Col (1-N):"));
        controlPanel.add(colTextField);
        controlPanel.add(new JLabel("Val:"));
        controlPanel.add(valTextField);
        controlPanel.add(setValue);
        controlPanel.add(loadBtn);
        controlPanel.add(solveBtn);
        controlPanel.add(helpBtn);

        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(backTracking);
        radioGroup.add(stch);
        radioGroup.add(onePossibility);

        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new FlowLayout());

        radioPanel.add(stch);
        radioPanel.add(backTracking);
        radioPanel.add(onePossibility);
        radioPanel.add(resetBtn);


        pack();

        setValue.addActionListener(new SetValueListener());
        loadBtn.addActionListener(new LoadListener());
        resetBtn.addActionListener(new ResetListener());
        solveBtn.addActionListener(new SolveListener());
        helpBtn.addActionListener(new HelpListener());
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());


        content.add(controlPanel, BorderLayout.NORTH);
        content.add(boardDisplay, BorderLayout.CENTER);

        content.add(radioPanel, BorderLayout.AFTER_LAST_LINE);
        setContentPane(content);
        setTitle("Sudoku Game by Shubham");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }



    class SetValueListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            try {

                int row = Integer.parseInt(rowTextField.getText().trim()) - 1;
                int col = Integer.parseInt(colTextField.getText().trim()) - 1;
                int val = Integer.parseInt(valTextField.getText().trim());
                if (sudokuLogic.isLegalMove(row, col, val)) {
                    sudokuLogic.setVal(row, col, val);
                    boardDisplay.repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Illegal row, col, or value.");
                }

            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Please enter numeric values.");
            }
        }
    }

    class ResetListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            try {
            int length = 9;
                for(int i=0;i<length;i++){
                    for(int j=0;j<length;j++){

                        sudokuLogic.setVal(i, j, 0);
                        boardDisplay.repaint();

                    }

                }

            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Please enter numeric values.");
            }
        }
    }



    public class HelpListener implements ActionListener {
        String helpMessage = "Example:"+"\n"+"\n"+"9 --> (Size of puzzle)"+"\n"+"\n"+"\" 1  2  3  4  5  6  7  8  9  --> (Possible values)"  +"\n"+"\n"+ " -  -  3  1  4  -  7  -  5 " +"\n" +" 4  5  -  2  8  -  -  3  6 "+"\n"+ " 5  -  8  4  -  9   1  -  7 " +"\n" +
                " -  -  4  8  -  6  -  7  3" +"\n"+ " 8  1  -  9  4  -  -  3  - " +"\n"+ " -  -  -  7  6  2  -  -  5  "+"\n" +
                " -   5   4   -  7  -  -  6  8  " +"\n"+ " 6  -  -  -  4  -  1  8  -  " + "\n"+ " 7  -   -  6  -   -  5  -  -"+"\n"+"\n"+ "Replace blank values with '-' and save a file like Puzzle.txt"+"\n"+"Only Txt files accepted";
        public void actionPerformed(ActionEvent event){
            JOptionPane.showMessageDialog(null, helpMessage, "Sudoku Solver Help.", 1);
        }
    }

    public class LoadListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            try {

                JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
                fileChooser.setFileFilter(filter);
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    String selectedFilePath = fileChooser.getSelectedFile() + "\\";
                    SudokuLoader sudokuLoader = new SudokuLoader();

                    if (sudokuLoader.loadSudokuFromTextFile(selectedFilePath)) {
                        isFileSelected = true;
                        System.out.println("Sudoku puzzle has been loaded.");
                        char[][] board = sudokuLoader.getSudokuBoard();
                        updateSolvedSudoku(board);

                    }
                }

            } catch (NumberFormatException nfe) {
                System.out.println(nfe);
            }
        }
    }

    public  void updateSolvedSudoku(char[][] board){
        int val =0;
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board.length;j++){
                if((board[i][j])=='-'){
                    val= 0;
                }
                else{
                    val= Integer.parseInt(Character.toString(board[i][j]));
                }
                sudokuLogic.setVal(i, j, val);
                boardDisplay.repaint();

            }

        }
    }

    public class SolveListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            if(isFileSelected = true){
                SudokuLoader sudokuLoader = new SudokuLoader();
                char board [][]= sudokuLoader.getSudokuBoard();
                char[] domain = sudokuLoader.getDomain();
                boolean isBackTrackingSelected= backTracking.isSelected();
                boolean isStochasticSearchSelected = stch.isSelected();
                boolean isOnlyOnePossibilitySelected = onePossibility.isSelected();
                SudokuToText sudokuToText = new SudokuToText();
                SudokuValidator sudokuValidator = new SudokuValidator();

                if(isBackTrackingSelected){
                    SudokuSolver solver = new BacktrackingAlgorithm();
                    solver.executeAlgorithm(board, domain, sudokuValidator, sudokuToText);
                    updateSolvedSudoku(board);

                }
                if(isStochasticSearchSelected){
                    SudokuSolver solver = new StochasticSearchAlgorithm();
                    solver.executeAlgorithm(board, domain, sudokuValidator, sudokuToText);
                    updateSolvedSudoku(board);
                }
                if(isOnlyOnePossibilitySelected ){
                    SudokuSolver solver = new OnlyOnePossibility();
                    solver.executeAlgorithm(board, domain, sudokuValidator, sudokuToText);
                    updateSolvedSudoku(board);
                }


            }
            else{

                    JOptionPane.showMessageDialog(null, "Invalid Puzzle Entered. Please upload a new puzzle and select an algorithm");
                }

            }


    }


    public static void main(String[] args) {
        new SudokuGUI().setVisible(true);
    }
}