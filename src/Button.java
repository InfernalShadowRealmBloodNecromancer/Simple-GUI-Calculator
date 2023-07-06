import javax.swing.*;
import java.awt.*;

public class Button extends JButton {
    Button(String text, CalculatorDisplay calculatorDisplay) {
        this.setText(text);
        this.setActionCommand(text);
        this.setFont(new Font("Inter", Font.BOLD, 30));
        this.setBackground(Color.gray);
        this.setForeground(Color.white);
        this.setFocusable(false);
        addActionListener(e -> {
            switch (e.getActionCommand()) {
                case "C" -> {
                    calculatorDisplay.clearAllArrayLists();
                    calculatorDisplay.setText("");
                }
                case "DEL" -> calculatorDisplay.deleteText();
                case "=" -> calculatorDisplay.equals();
                default -> calculatorDisplay.addText(e.getActionCommand());
            }
        });
    }
}