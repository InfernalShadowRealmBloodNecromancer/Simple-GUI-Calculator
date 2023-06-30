import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button extends JButton implements ActionListener {
    DisplayOutput displayOutput;

    Button(String text, DisplayOutput displayOutput) {
        super(text);
        this.setActionCommand(text);
        this.setFont(new Font("Arial", Font.BOLD, 20));
        this.setBackground(Color.lightGray);
        this.setForeground(Color.black);
        this.setFocusable(false);
        addActionListener(this);
        this.displayOutput = displayOutput;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        switch (actionCommand) {
            case "C" -> displayOutput.clearAll();
            case "DEL" -> displayOutput.deleteText();
            case "=" ->  displayOutput.equals();
            default -> displayOutput.addText(actionCommand);
        }
    }
}
