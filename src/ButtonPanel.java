import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    ButtonPanel(DisplayOutput displayOutput) {
        this.setOpaque(true);
        this.setBackground(Color.darkGray);
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weighty = 1;
        constraints.weightx = 1;
        Button button1 = new Button("1", displayOutput);
        Button button4 = new Button("4", displayOutput);
        Button button7 = new Button("7", displayOutput);
        Button button0 = new Button("0", displayOutput);
        Button button2 = new Button("2", displayOutput);
        Button button5 = new Button("5", displayOutput);
        Button button8 = new Button("8", displayOutput);
        Button button3 = new Button("3", displayOutput);
        Button button6 = new Button("6", displayOutput);
        Button button9 = new Button("9", displayOutput);
        Button buttonDecimal = new Button(".", displayOutput);
        Button buttonAdd = new Button("+", displayOutput);
        Button buttonDivide = new Button("/", displayOutput);
        Button buttonMultiply = new Button("*", displayOutput);
        Button buttonSubtract = new Button("-", displayOutput);
        Button buttonClear = new Button("C", displayOutput);
        Button buttonDEL = new Button("DEL", displayOutput);
        Button buttonEquals = new Button("=", displayOutput);

        constraints.gridx = 0; // column 1
        constraints.gridy = 0;
        this.add(button1, constraints);
        constraints.gridy = 1;
        this.add(button4, constraints);
        constraints.gridy = 2;
        this.add(button7, constraints);
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        this.add(button0, constraints);

        constraints.gridx = 1; // column 2
        constraints.gridwidth = 1;
        constraints.gridy = 0;
        this.add(button2, constraints);
        constraints.gridy = 1;
        this.add(button5, constraints);
        constraints.gridy = 2;
        this.add(button8, constraints);

        constraints.gridx = 2; // column 3
        constraints.gridy = 0;
        this.add(button3, constraints);
        constraints.gridy = 1;
        this.add(button6, constraints);
        constraints.gridy = 2;
        this.add(button9, constraints);
        constraints.gridy = 3;
        this.add(buttonDecimal, constraints);

        constraints.gridx = 3; // column 4
        constraints.gridy = 0;
        this.add(buttonDivide, constraints);
        constraints.gridy = 1;
        this.add(buttonMultiply, constraints);
        constraints.gridy = 2;
        this.add(buttonSubtract, constraints);
        constraints.gridy = 3;
        this.add(buttonAdd, constraints);

        constraints.gridx = 4; // column 5
        constraints.gridy = 0;
        this.add(buttonClear, constraints);
        constraints.gridy = 1;
        this.add(buttonDEL, constraints);
        constraints.gridy = 2;
        constraints.gridheight = 2;
        this.add(buttonEquals, constraints);
    }
}