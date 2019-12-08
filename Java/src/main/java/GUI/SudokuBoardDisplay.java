package GUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JComponent;


public class SudokuBoardDisplay  extends JComponent {
    char[][] puzzle;
    private static final int CELL_PIXELS = 50;
    int SUBSQUARE ;
    int BOARD_PIXELS;
    int TEXT_OFFSET = 15;
    private static final Font TEXT_FONT  = new Font("Calibri", Font.BOLD, 24);
    private SudokuModel _model;


    public SudokuBoardDisplay(SudokuModel model,char [][]board) {
        puzzle = board;
        setPreferredSize(new Dimension(BOARD_PIXELS + 2, BOARD_PIXELS + 2));
        setBackground(Color.WHITE);
        _model = model;
        SUBSQUARE  = (int)Math.sqrt(puzzle.length);
        BOARD_PIXELS = CELL_PIXELS * puzzle.length;
    }


    @Override public void paintComponent(Graphics g) {

        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);
        drawGridLines(g);
        drawCellValues(g);
    }

    private void drawGridLines(Graphics g) {


        for (int i = 0; i <= puzzle.length; i++) {
            int acrossOrDown = i * CELL_PIXELS;

            g.drawLine(acrossOrDown, 0, acrossOrDown, BOARD_PIXELS);

            g.drawLine(0, acrossOrDown, BOARD_PIXELS, acrossOrDown);

            if (i % SUBSQUARE == 0) {
                acrossOrDown++;
                g.drawLine(acrossOrDown, 0, acrossOrDown, BOARD_PIXELS);
                g.drawLine(0, acrossOrDown, BOARD_PIXELS, acrossOrDown);
            }
        }
    }


    private void drawCellValues(Graphics g) {
        g.setFont(TEXT_FONT);
        for (int i = 0; i < puzzle.length; i++) {
            int yDisplacement = (i+1) * CELL_PIXELS - TEXT_OFFSET;
            for (int j = 0; j < puzzle.length; j++) {
                    _model.getVal(i, j);
                    int xDisplacement = j * CELL_PIXELS + TEXT_OFFSET;
                    g.drawString("" + _model.getVal(i, j), xDisplacement, yDisplacement);

            }
        }
    }

}
