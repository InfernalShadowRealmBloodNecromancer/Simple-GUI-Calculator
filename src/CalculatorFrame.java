import javax.swing.*;
        import java.awt.*;

public class CalculatorFrame extends JFrame {
    CalculatorFrame() {
        this.setPreferredSize(new Dimension(700,700));
        this.getContentPane().setBackground(Color.darkGray);
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Calculator");

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridy = 0;
        constraints.weighty = 0.4;
        constraints.weightx = 1;
        CalculatorDisplay calculatorDisplay = new CalculatorDisplay();
        this.add(calculatorDisplay,constraints);

        constraints.gridy = 1;
        constraints.weighty = 0.6;
        ButtonPanel buttonPanel = new ButtonPanel(calculatorDisplay);
        this.add(buttonPanel,constraints);

        this.pack();
        this.setVisible(true);
    }
}