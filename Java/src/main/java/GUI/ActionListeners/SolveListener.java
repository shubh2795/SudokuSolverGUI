package GUI.ActionListeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SolveListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
        try {


        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Please enter numeric values.");
        }
    }
}
