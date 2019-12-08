package GUI;
import GUI.ActionListeners.HelpListener;
import GUI.ActionListeners.LoadListener;
import GUI.ActionListeners.SolveListener;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SudokuGui extends JFrame {

    char[][] INITIAL_BOARD = new char[][]
            {
                    {'3', '-', '6', '5', '-', '8', '4', '-','-'},
                    {'5', '2', '-', '-','-', '-', '-', '-', '-'},
                    {'-','8', '7', '-', '-', '-', '-', '3', '1'},
                    {'-', '-', '3', '-', '1', '-', '-', '8', '-'},
                    {'9', '-', '-', '8', '6', '3', '-', '-', '5'},
                    {'-', '5','-', '-', '9', '-', '6', '-', '-'},
                    {'1', '3', '-', '-', '-', '-', '2', '5', '-'},
                    {'-', '-', '-', '-', '-', '-', '-', '7', '4'},
                    {'-', '-', '5', '2', '-', '6', '3', '-', '-'}
            };

    char[] domain = {'1','2','3','4','5','6','7','8','9'};


    private SudokuModel   _sudokuLogic = new SudokuModel(INITIAL_BOARD);
    private SudokuBoardDisplay _sudokuBoard = new SudokuBoardDisplay(_sudokuLogic,INITIAL_BOARD);

    JRadioButton backTracking = new JRadioButton("BackTracking");
    JRadioButton dfs = new JRadioButton("DFS");
    JRadioButton stch = new JRadioButton("Stochastic Search Algorithm");
    JRadioButton onePossibility = new JRadioButton("1-Possibility");


    public SudokuGui() {

        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(backTracking);
        radioGroup.add(dfs);
        radioGroup.add(stch);
        radioGroup.add(onePossibility);

        JButton solveBtn = new JButton("Solve");
        JButton loadBtn = new JButton("Load File" );
        JButton helpBtn = new JButton("Help ?" );

        JPanel radioPanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        radioPanel.setLayout(new FlowLayout());

        radioPanel.add(stch);
        radioPanel.add(backTracking);
        radioPanel.add(onePossibility);
        radioPanel.add(dfs);
        pack();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(loadBtn);
        buttonPanel.add(solveBtn);
        buttonPanel.add(helpBtn);


        solveBtn.addActionListener(new SolveListener());
        loadBtn.addActionListener(new LoadListener());
        helpBtn.addActionListener(new HelpListener());

        backTracking.addActionListener(new BackTrackListener());

        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());


        content.add(radioPanel, BorderLayout.NORTH);
        content.add(buttonPanel, BorderLayout.SOUTH);
        content.add(_sudokuBoard, BorderLayout.CENTER);


        setContentPane(content);
        setTitle("Sudoku Solver by Shubham");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }





    class BackTrackListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                boolean isBackTrackingSelected = backTracking.isSelected();
                if (isBackTrackingSelected) {

                    // create object of backtracking algo here

                }

            } catch (NumberFormatException nfe) {

            }
        }
    }



    public static void main(String[] args) {
        new SudokuGui().setVisible(true);
    }
}