import javax.swing.*;
import java.awt.*;

public class Calculator extends JFrame {
    Calculator() {
        this.setPreferredSize(new Dimension(500,500));
        this.getContentPane().setBackground(Color.darkGray);
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets= new Insets(5,5,5,5);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Calculator");

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridy = 0;
        constraints.weighty = 0.4;
        constraints.weightx = 1;
        DisplayTextField displayTextField = new DisplayTextField();
        this.add(displayTextField,constraints);

        constraints.gridy = 1;
        constraints.weighty = 0.6;
        ButtonPanel buttonPanel = new ButtonPanel(displayTextField);
        this.add(buttonPanel,constraints);

        this.pack();
        this.setVisible(true);
    }
}
