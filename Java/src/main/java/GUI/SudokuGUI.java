package GUI;
import GUI.ActionListeners.HelpListener;

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

    private static  String INITIAL_BOARD =
                    "8156----4/" +
                    "6---75-8-/" +
                    "----9----/" +
                    "9---417---/" +
                    "-4-----2-/" +
                    "--623---8/" +
                    "----5----/" +
                    "-5-91---6/" +
                    "1----7895";


    private SudokuModel sudokuLogic = new SudokuModel(INITIAL_BOARD);
    private SudokuBoardDisplay boardDisplay = new SudokuBoardDisplay(sudokuLogic);

    private JTextField rowTextField = new JTextField(2);
    private JTextField colTextField = new JTextField(2);
    private JTextField valTextField = new JTextField(2);

    JRadioButton backTracking = new JRadioButton("BackTracking");
    JRadioButton stch = new JRadioButton("Stochastic Search Algorithm");
    JRadioButton onePossibility = new JRadioButton("1-Possibility");
    JButton generatePuzzleBtn = new JButton("New Puzzle");
    JButton solveBtn = new JButton("Solve");
    JButton helpBtn = new JButton("Help ?" );
    JButton setValue = new JButton("Set Value");
    JButton loadBtn = new JButton("Load File" );
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
        radioPanel.add(generatePuzzleBtn);

        pack();

        setValue.addActionListener(new SetValueListener());
        loadBtn.addActionListener(new LoadListener());
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
                char solvedBoard [][];
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

        }
    }


    public static void main(String[] args) {
        new SudokuGUI().setVisible(true);
    }
}