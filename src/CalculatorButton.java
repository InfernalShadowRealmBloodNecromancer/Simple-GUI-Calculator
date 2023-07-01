import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorButton extends JButton implements ActionListener {
    CalculatorDisplay calculatorDisplay;

    CalculatorButton(String text, CalculatorDisplay calculatorDisplay) {
        this.setText(text);
        this.setActionCommand(text);
        this.setFont(new Font("Arial", Font.BOLD, 20));
        this.setBackground(Color.lightGray);
        this.setForeground(Color.black);
        this.setFocusable(false);
        addActionListener(this);
        this.calculatorDisplay = calculatorDisplay;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        switch (actionCommand) {
            case "C" -> calculatorDisplay.clearAll();
            case "DEL" -> calculatorDisplay.deleteText();
            case "=" -> calculatorDisplay.equals();
            default -> calculatorDisplay.addText(actionCommand);
        }
    }
}
