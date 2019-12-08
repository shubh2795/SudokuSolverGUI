package GUI;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class SudokuGui extends JFrame {
    private static final String INITIAL_BOARD = "123456987";
    private SudokuModel        _sudokuLogic = new SudokuModel(INITIAL_BOARD);
    private SudokuBoardDisplay _sudokuBoard = new SudokuBoardDisplay(_sudokuLogic);
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


        solveBtn.addActionListener(new SolveListener());
        loadBtn.addActionListener(new LoadListener());
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



    class SolveListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {


            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Please enter numeric values.");
            }
        }
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

    class LoadListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
                fileChooser.setFileFilter(filter);
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    System.out.println(selectedFile.getAbsolutePath());
                }

            } catch (NumberFormatException nfe) {
               System.out.println(nfe);
            }
        }
    }

    public static void main(String[] args) {
        new SudokuGui().setVisible(true);
    }
}