package GUI;
import GUI.ActionListeners.HelpListener;
import GUI.ActionListeners.SolveListener;
import common.SudokuLoader;
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


    private SudokuModel        _sudokuLogic = new SudokuModel(INITIAL_BOARD);
    private SudokuBoardDisplay _sudokuBoard = new SudokuBoardDisplay(_sudokuLogic);

    private JTextField _rowTF = new JTextField(2);
    private JTextField _colTF = new JTextField(2);
    private JTextField _valTF = new JTextField(2);
    JRadioButton backTracking = new JRadioButton("BackTracking");
    JRadioButton dfs = new JRadioButton("DFS");
    JRadioButton stch = new JRadioButton("Stochastic Search Algorithm");
    JRadioButton onePossibility = new JRadioButton("1-Possibility");


    public SudokuGUI() {
        JButton solveBtn = new JButton("Solve");
        JButton helpBtn = new JButton("Help ?" );

        JButton setValue = new JButton("Set Value");
        JButton loadBtn = new JButton("Load File" );
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.add(new JLabel("Row (1-N):"));
        controlPanel.add(_rowTF);
        controlPanel.add(new JLabel("Col (1-N):"));
        controlPanel.add(_colTF);
        controlPanel.add(new JLabel("Val:"));
        controlPanel.add(_valTF);
        controlPanel.add(setValue);
        controlPanel.add(loadBtn);
        controlPanel.add(solveBtn);
        controlPanel.add(helpBtn);

        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(backTracking);
        radioGroup.add(dfs);
        radioGroup.add(stch);
        radioGroup.add(onePossibility);

        JPanel radioPanel = new JPanel();


        radioPanel.setLayout(new FlowLayout());

        radioPanel.add(stch);
        radioPanel.add(backTracking);
        radioPanel.add(onePossibility);
        radioPanel.add(dfs);
        pack();


        setValue.addActionListener(new MoveListener());
        loadBtn.addActionListener(new LoadListener());
        solveBtn.addActionListener(new SolveListener());
        helpBtn.addActionListener(new HelpListener());
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());


        content.add(controlPanel, BorderLayout.NORTH);
        content.add(_sudokuBoard, BorderLayout.CENTER);

        content.add(radioPanel, BorderLayout.AFTER_LAST_LINE);
        setContentPane(content);
        setTitle("Sudoku Game and Solver by Shubham");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }



    class MoveListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            try {

                int row = Integer.parseInt(_rowTF.getText().trim()) - 1;
                int col = Integer.parseInt(_colTF.getText().trim()) - 1;
                int val = Integer.parseInt(_valTF.getText().trim());
                if (_sudokuLogic.isLegalMove(row, col, val)) {
                    _sudokuLogic.setVal(row, col, val);
                    _sudokuBoard.repaint();
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
                        System.out.println("Sudoku puzzle has been loaded.");
                        char[][] board = sudokuLoader.getSudokuBoard();
                        int val =0;
                        for(int i=0;i<board.length;i++){
                            for(int j=0;j<board.length;j++){
                                val= Integer.parseInt(Character.toString(board[i][j]));
                                _sudokuLogic.setVal(i, j, val);
                                _sudokuBoard.repaint();
                            }

                        }

                    }
                }

            } catch (NumberFormatException nfe) {
                System.out.println(nfe);
            }
        }
    }

    public static void main(String[] args) {
        new SudokuGUI().setVisible(true);
    }
}