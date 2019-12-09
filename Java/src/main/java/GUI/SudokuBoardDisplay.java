package GUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JComponent;


public class SudokuBoardDisplay  extends JComponent {

    private static  int cellPixels = 65;
    private static  int puzzleSize = 9;
    private static  int subsquare = 3;
    private static  int boardPixels = cellPixels * puzzleSize;
    private static  int textOffset = 15;
    private static  Font textFont = new Font("Calibri", Font.BOLD, 24);


    private SudokuModel model;


    public SudokuBoardDisplay(SudokuModel model) {
        setPreferredSize(new Dimension(boardPixels + 2, boardPixels + 2));
        setBackground(Color.WHITE);
        this.model = model;
    }


    @Override public void paintComponent(Graphics g) {

        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);

        drawGridLines(g);
        drawCellValues(g);
    }

    private void drawGridLines(Graphics g) {

        for (int i = 0; i <= puzzleSize; i++) {
            int acrossOrDown = i * cellPixels;

            g.drawLine(acrossOrDown, 0, acrossOrDown, boardPixels);

            g.drawLine(0, acrossOrDown, boardPixels, acrossOrDown);


            if (i % subsquare == 0) {
                acrossOrDown++;
                g.drawLine(acrossOrDown, 0, acrossOrDown, boardPixels);
                g.drawLine(0, acrossOrDown, boardPixels, acrossOrDown);
            }
        }
    }

    private void drawCellValues(Graphics g) {
        g.setFont(textFont);
        for (int i = 0; i < puzzleSize; i++) {
            int yDisplacement = (i+1) * cellPixels - textOffset;
            for (int j = 0; j < puzzleSize; j++) {



                if (model.getVal(i, j) != 0) {

                    int xDisplacement = j * cellPixels + textOffset;
                    g.setColor(Color.BLUE);
                    g.drawString("" + model.getVal(i, j), xDisplacement, yDisplacement);

                }
            }
        }
    }

}