import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class CalculatorDisplay extends JTextField {
    ArrayList<Double> numbersAsDoubles = new ArrayList<>();
    ArrayList<String> numbersAsStrings = new ArrayList<>();
    ArrayList<Character> operators = new ArrayList<>();

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
        clearAllArrayLists();
    }

    public void clearAllArrayLists() {
        numbersAsDoubles.clear();
        numbersAsStrings.clear();
        operators.clear();
    }

    public void equals() {
        displayTextExtractor();
        if (!this.getText().isBlank()) { //only runs calculation logic if display is not blank
            performCalculation();
            if(!numbersAsDoubles.isEmpty()) {
                this.setText(String.valueOf(numbersAsDoubles.get(0)));
                clearAllArrayLists();
            }
       }
    }

    public void performBracketsCalculation() {
        String display = this.getText();
        while (display.contains("(") || display.contains(")")) {
            int openingBracketIndex = display.lastIndexOf("(");
            int closingBracketIndex = display.indexOf(")", openingBracketIndex); //starts the index for indexOf() from the last "(", where "(" will be i=0

            if (closingBracketIndex == -1 || openingBracketIndex == -1) {
                Error("syntax");
                return;
            }
        }
    }


    public void displayTextExtractor() {
       String display = this.getText();

        if ((display.length() > 0))  { //won't run if there's nothing in the display
            char previousChar = ' '; //gives previousChar a value for the first iteration

            for (int i = 0; i < display.length(); i++) {
                char currentChar = display.charAt(i);
                int lastIndex = numbersAsStrings.size() - 1;
                if (numbersAsStrings.isEmpty()) {
                    numbersAsStrings.add(String.valueOf(currentChar));
                } else {
                    if (Character.isDigit(currentChar) || currentChar == '.') { //if currentChar is a number or decimal point
                        if (!Character.isDigit(previousChar) && (previousChar != '.')) { //if previousChar is an operator
                            if (numbersAsStrings.get(lastIndex).equals("-") || numbersAsStrings.get(lastIndex).equals("+")) { //if the previous value in numbersArray is a + or - operator
                                numbersAsStrings.set(lastIndex, numbersAsStrings.get(lastIndex) + (display.charAt(i))); //the currentChar will be concatenated to the operator to create a negative/positive number
                            } else {
                                numbersAsStrings.add(String.valueOf(currentChar));//else adds the currentChar as a new value in the array
                            }
                        } else {
                            numbersAsStrings.set(lastIndex, numbersAsStrings.get(lastIndex) + (display.charAt(i))); //else if the previousChar is not an operator, will concatenate currentChar to the last value in numberArray
                        }
                    } else //else the currentChar is an operator
                        if (!Character.isDigit(previousChar) && (previousChar != '.')) { //if the previous character is an operator
                            numbersAsStrings.add(String.valueOf(currentChar)); //if there's two operators in a row, this adds the second operator to the numbersArray
                        } else {
                            operators.add(currentChar); //adds the operator to operatorArray
                        }
                }
                previousChar = currentChar; //makes currentChar previousChar for the next iteration
            }
        }
        convertArrayList(numbersAsStrings);
    }

    public void convertArrayList(ArrayList<String> numbersArray) {
        for (String str : numbersArray) { //converts <String>numbersArray to doubleArray
            try {
                double value = Double.parseDouble(str);
                numbersAsDoubles.add(value);
            } catch (NumberFormatException e) { //throws syntax error if non-numbers are detected in numbersArray
                Error("syntax");
                return;
            }
        }
    }

    public void performCalculation() {
        try {
            for (int i = 0; i < operators.size(); i++) {
                if (operators.get(i) == '/') {
                    double divisor = numbersAsDoubles.get(i + 1);
                        if (divisor == 0) {
                            Error("divide");
                            return;
                        }
                        double temp = applyOperator('/', numbersAsDoubles.get(i), numbersAsDoubles.get(i + 1));
                        numbersAsDoubles.set(i, temp);
                        numbersAsDoubles.remove(i + 1);
                        operators.remove(i);
                        i--; //Decrement i to account for the removed operator
                }
            }
            for (int i = 0; i < operators.size(); i++) {
                if (operators.get(i) == '*') {
                    double temp = applyOperator('*', numbersAsDoubles.get(i), numbersAsDoubles.get(i + 1));
                    numbersAsDoubles.set(i, temp);
                    numbersAsDoubles.remove(i + 1);
                    operators.remove(i);
                    i--;
                }
            }
            for (int i = 0; i < operators.size(); i++) {
                if (operators.get(i) == '+') {
                    double temp = applyOperator('+', numbersAsDoubles.get(i), numbersAsDoubles.get(i + 1));
                    numbersAsDoubles.set(i, temp);
                    numbersAsDoubles.remove(i + 1);
                    operators.remove(i);
                    i--;
                }
            }
            for (int i = 0; i < operators.size(); i++) {
                if (operators.get(i) == '-') {
                    double temp = applyOperator('-', numbersAsDoubles.get(i), numbersAsDoubles.get(i + 1));
                    numbersAsDoubles.set(i, temp);
                    numbersAsDoubles.remove(i + 1);
                    operators.remove(i);
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

    public void setKeyListener() {
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
                        CalculatorDisplay.this.clearAllArrayLists();
                        CalculatorDisplay.this.setText("");
                    }
                }
            }
        });
    }
}