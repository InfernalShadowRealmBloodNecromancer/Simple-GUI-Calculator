import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    ButtonPanel(CalculatorDisplay calculatorDisplay) {
        this.setOpaque(true);
        this.setBackground(Color.darkGray);
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weighty = 1;
        constraints.weightx = 1;
        CalculatorButton calculatorButton1 = new CalculatorButton("1", calculatorDisplay);
        CalculatorButton calculatorButton4 = new CalculatorButton("4", calculatorDisplay);
        CalculatorButton calculatorButton7 = new CalculatorButton("7", calculatorDisplay);
        CalculatorButton calculatorButton0 = new CalculatorButton("0", calculatorDisplay);
        CalculatorButton calculatorButton2 = new CalculatorButton("2", calculatorDisplay);
        CalculatorButton calculatorButton5 = new CalculatorButton("5", calculatorDisplay);
        CalculatorButton calculatorButton8 = new CalculatorButton("8", calculatorDisplay);
        CalculatorButton calculatorButton3 = new CalculatorButton("3", calculatorDisplay);
        CalculatorButton calculatorButton6 = new CalculatorButton("6", calculatorDisplay);
        CalculatorButton calculatorButton9 = new CalculatorButton("9", calculatorDisplay);
        CalculatorButton calculatorButtonDecimal = new CalculatorButton(".", calculatorDisplay);
        CalculatorButton calculatorButtonAdd = new CalculatorButton("+", calculatorDisplay);
        CalculatorButton calculatorButtonDivide = new CalculatorButton("/", calculatorDisplay);
        CalculatorButton calculatorButtonMultiply = new CalculatorButton("*", calculatorDisplay);
        CalculatorButton calculatorButtonSubtract = new CalculatorButton("-", calculatorDisplay);
        CalculatorButton calculatorButtonClear = new CalculatorButton("C", calculatorDisplay);
        CalculatorButton calculatorButtonDEL = new CalculatorButton("DEL", calculatorDisplay);
        CalculatorButton calculatorButtonEquals = new CalculatorButton("=", calculatorDisplay);

        constraints.gridx = 0; // column 1
        constraints.gridy = 0;
        this.add(calculatorButton1, constraints);
        constraints.gridy = 1;
        this.add(calculatorButton4, constraints);
        constraints.gridy = 2;
        this.add(calculatorButton7, constraints);
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        this.add(calculatorButton0, constraints);

        constraints.gridx = 1; // column 2
        constraints.gridwidth = 1;
        constraints.gridy = 0;
        this.add(calculatorButton2, constraints);
        constraints.gridy = 1;
        this.add(calculatorButton5, constraints);
        constraints.gridy = 2;
        this.add(calculatorButton8, constraints);

        constraints.gridx = 2; // column 3
        constraints.gridy = 0;
        this.add(calculatorButton3, constraints);
        constraints.gridy = 1;
        this.add(calculatorButton6, constraints);
        constraints.gridy = 2;
        this.add(calculatorButton9, constraints);
        constraints.gridy = 3;
        this.add(calculatorButtonDecimal, constraints);

        constraints.gridx = 3; // column 4
        constraints.gridy = 0;
        this.add(calculatorButtonDivide, constraints);
        constraints.gridy = 1;
        this.add(calculatorButtonMultiply, constraints);
        constraints.gridy = 2;
        this.add(calculatorButtonSubtract, constraints);
        constraints.gridy = 3;
        this.add(calculatorButtonAdd, constraints);

        constraints.gridx = 4; // column 5
        constraints.gridy = 0;
        this.add(calculatorButtonClear, constraints);
        constraints.gridy = 1;
        this.add(calculatorButtonDEL, constraints);
        constraints.gridy = 2;
        constraints.gridheight = 2;
        this.add(calculatorButtonEquals, constraints);
    }
}