import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class CalculatorDisplay extends JTextField {
    ArrayList<Double> doubleArray = new ArrayList<>();
    ArrayList<String> numbersArray = new ArrayList<>();
    ArrayList<Character> operatorsArray = new ArrayList<>();

    CalculatorDisplay() {
        this.setHorizontalAlignment(JLabel.RIGHT);
        this.setFont(new Font("digital-7", Font.PLAIN, 60));
        this.setBackground(Color.gray);
        this.setForeground(Color.BLACK);
        this.setOpaque(true);
        this.setEditable(false);
        this.setCaretColor(getBackground());
        this.setText("");
        setKeyListener();
    }

    public void addText(String text) {
        this.setText(this.getText().concat(text));
    }

    public void Error(String errorType) {
        if (errorType.equals("syntax")) {
            JOptionPane.showMessageDialog(null, "Syntax Error", "Syntax Error", JOptionPane.ERROR_MESSAGE);
        }
        if (errorType.equals("divide")) {
            JOptionPane.showMessageDialog(null, "Divide by Zero Error", "Divide by Zero Error", JOptionPane.ERROR_MESSAGE);
        }
        clearAll();
    }

    public void clearAll() {
        doubleArray.clear();
        numbersArray.clear();
        operatorsArray.clear();
    }

    public void equals() {
        displayExtractor();
        if (!this.getText().isBlank()) { //only runs calculation logic if display is not blank
            performCalculation();
            if(!doubleArray.isEmpty()) {
                this.setText(String.valueOf(doubleArray.get(0)));
                clearAll();
            }
       }
    }

    public void displayExtractor() {
       String displayText = this.getText();

        if ((displayText.length() > 0))  { //won't run if there's nothing in the display
            char previousChar = ' '; //gives previousChar a value for the first iteration

            for (int i = 0; i < displayText.length(); i++) {
                char currentChar = displayText.charAt(i);
                int lastIndex = numbersArray.size() - 1;
                if (numbersArray.isEmpty()) {
                    numbersArray.add(String.valueOf(currentChar));
                } else {
                    if (Character.isDigit(currentChar) || currentChar == '.') { //if currentChar is a number or decimal point
                        if (!Character.isDigit(previousChar) && (previousChar != '.')) { //if previousChar is an operator
                            if (numbersArray.get(lastIndex).equals("-") || numbersArray.get(lastIndex).equals("+")) { //if the previous value in numbersArray is a + or - operator
                                numbersArray.set(lastIndex, numbersArray.get(lastIndex) + (displayText.charAt(i))); //the currentChar will be concatenated to the operator to create a negative/positive number
                            } else {
                                numbersArray.add(String.valueOf(currentChar));//else adds the currentChar as a new value in the array
                            }
                        } else {
                            numbersArray.set(lastIndex, numbersArray.get(lastIndex) + (displayText.charAt(i))); //else if the previousChar is not an operator, will concatenate currentChar to the last value in numberArray
                        }
                    } else //else the currentChar is an operator
                        if (!Character.isDigit(previousChar) && (previousChar != '.')) { //if the previous character is an operator
                            numbersArray.add(String.valueOf(currentChar)); //if there's two operators in a row, this adds the second operator to the numbersArray
                        } else {
                            operatorsArray.add(currentChar); //adds the operator to operatorArray
                        }
                }
                previousChar = currentChar; //makes currentChar previousChar for the next iteration
            }
        }
        convertArrayList(numbersArray);
    }

    public void convertArrayList(ArrayList<String> numbersArray) {
        for (String str : numbersArray) { //converts <String>numbersArray to doubleArray
            try {
                double value = Double.parseDouble(str);
                doubleArray.add(value);
            } catch (NumberFormatException e) { //throws syntax error if non-numbers are detected in numbersArray
                Error("syntax");
                return;
            }
        }
    }

    public void performCalculation() {
        try {
            for (int i = 0; i < operatorsArray.size(); i++) {
                if (operatorsArray.get(i) == '/') {
                    double divisor = doubleArray.get(i + 1);
                        if (divisor == 0) {
                            Error("divide");
                            return;
                        }
                        double temp = applyOperator('/', doubleArray.get(i), doubleArray.get(i + 1));
                        doubleArray.set(i, temp);
                        doubleArray.remove(i + 1);
                        operatorsArray.remove(i);
                        i--; //Decrement i to account for the removed operator
                }
            }
            for (int i = 0; i < operatorsArray.size(); i++) {
                if (operatorsArray.get(i) == '*') {
                    double temp = applyOperator('*', doubleArray.get(i), doubleArray.get(i + 1));
                    doubleArray.set(i, temp);
                    doubleArray.remove(i + 1);
                    operatorsArray.remove(i);
                    i--;
                }
            }
            for (int i = 0; i < operatorsArray.size(); i++) {
                if (operatorsArray.get(i) == '+') {
                    double temp = applyOperator('+', doubleArray.get(i), doubleArray.get(i + 1));
                    doubleArray.set(i, temp);
                    doubleArray.remove(i + 1);
                    operatorsArray.remove(i);
                    i--;
                }
            }
            for (int i = 0; i < operatorsArray.size(); i++) {
                if (operatorsArray.get(i) == '-') {
                    double temp = applyOperator('-', doubleArray.get(i), doubleArray.get(i + 1));
                    doubleArray.set(i, temp);
                    doubleArray.remove(i + 1);
                    operatorsArray.remove(i);
                    i--;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            Error("syntax");
        }
    }

    public double applyOperator(char operator, double num1, double num2) {
        switch (operator) {
            case '/' -> {
                return num1 / num2;
            }
            case '*' -> {
                return num1 * num2;
            }
            case '+' -> {
                return num1 + num2;
            }
            case '-' -> {
                return num1 - num2;
            }
            default -> {
                Error("syntax");
                throw new IllegalArgumentException("Invalid Operator: " + operator);
            }
        }
    }

    public void deleteText() {
        int textLength = this.getText().length();
        if (textLength > 0) {
            this.setText(this.getText().substring(0, textLength - 1));
        }
    }
    public void setKeyListener(){
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyChar()) {
                    case '1' -> CalculatorDisplay.this.addText("1");
                    case '2' -> CalculatorDisplay.this.addText("2");
                    case '3' -> CalculatorDisplay.this.addText("3");
                    case '4' -> CalculatorDisplay.this.addText("4");
                    case '5' -> CalculatorDisplay.this.addText("5");
                    case '6' -> CalculatorDisplay.this.addText("6");
                    case '7' -> CalculatorDisplay.this.addText("7");
                    case '8' -> CalculatorDisplay.this.addText("8");
                    case '9' -> CalculatorDisplay.this.addText("9");
                    case '0' -> CalculatorDisplay.this.addText("0");
                    case '.' -> CalculatorDisplay.this.addText(".");
                    case '+' -> CalculatorDisplay.this.addText("+");
                    case '-' -> CalculatorDisplay.this.addText("-");
                    case '/', '÷' -> CalculatorDisplay.this.addText("/");
                    case '*', '×' -> CalculatorDisplay.this.addText("*");
                    case KeyEvent.VK_DELETE, KeyEvent.VK_BACK_SPACE -> CalculatorDisplay.this.deleteText();
                    case '=', KeyEvent.VK_ENTER -> CalculatorDisplay.this.equals();
                    case 'C', 'c' -> {
                        CalculatorDisplay.this.clearAll();
                        CalculatorDisplay.this.setText("");
                    }
                }
            }
        });
    }
}