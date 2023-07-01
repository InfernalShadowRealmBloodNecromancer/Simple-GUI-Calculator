import javax.swing.*;
import java.awt.*;

public class Button extends JButton {
    Button(String text, CalculatorDisplay calculatorDisplay) {
        this.setText(text);
        this.setActionCommand(text);
        this.setFont(new Font("Arial", Font.BOLD, 20));
        this.setBackground(Color.lightGray);
        this.setForeground(Color.black);
        this.setFocusable(false);
        addActionListener(e -> {
            switch (e.getActionCommand()) {
                case "C" -> calculatorDisplay.clearAll();
                case "DEL" -> calculatorDisplay.deleteText();
                case "=" -> calculatorDisplay.equals();
                default -> calculatorDisplay.addText(e.getActionCommand());
            }
        });
    }
}
