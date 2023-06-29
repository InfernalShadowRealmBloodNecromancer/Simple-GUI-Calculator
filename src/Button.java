import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button extends JButton implements ActionListener {
    private final DisplayTextField displayTextField;

    Button(String text, DisplayTextField displayTextField) {
        super(text);
        this.setActionCommand(text);
        this.setFont(new Font("Arial", Font.BOLD, 20));
        this.setBackground(Color.lightGray);
        this.setForeground(Color.black);
        this.setFocusable(false);
        addActionListener(this);
        this.displayTextField = displayTextField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        switch (actionCommand) {
            case "C" -> displayTextField.clearAll();
            case "DEL" -> displayTextField.deleteText();
            case "=" ->  displayTextField.equalsOperator();
            default -> displayTextField.addText(actionCommand);
        }
    }
}
