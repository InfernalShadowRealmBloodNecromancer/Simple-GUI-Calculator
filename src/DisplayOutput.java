import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DisplayOutput extends JTextField {
    ArrayList<Double> doubleArray;
    ArrayList<String> numbersArray;
    ArrayList<Character> operatorsArray;

    DisplayOutput() {
        this.setHorizontalAlignment(JLabel.RIGHT);
        this.setFont(new Font("digital-7", Font.PLAIN, 60));
        this.setBackground(Color.gray);
        this.setForeground(Color.BLACK);
        this.setOpaque(true);
        this.setEditable(false);
        this.setCaretColor(getBackground());
    }

    public void addText(String text) {
        this.setText(this.getText().concat(text));
    }

    public void syntaxError() {
        JOptionPane.showMessageDialog(null, "Syntax Error", "Syntax Error", JOptionPane.ERROR_MESSAGE);
    }

    public void divideError() {
        JOptionPane.showMessageDialog(null, "Divide by Zero Error", "Divide by Zero Error", JOptionPane.ERROR_MESSAGE);
    }

    public void clearAll() {
        this.setText("");
    }

    public void equals() {
        displayExtractor();
        if ((!this.getText().isBlank()) && !this.getText().equals("Infinity")){
            this.setText(String.valueOf(performCalculation()));
       }
    }

    public void displayExtractor() {
        numbersArray = new ArrayList<>();
        operatorsArray = new ArrayList<>();
        String display = this.getText();

        if ((display.length() > 0))  { //won't run if there's nothing in the display
            char previousChar = ' '; //gives previousChar a value for the first iteration

            for (int i = 0; i < display.length(); i++) {
                char currentChar = display.charAt(i);
                int lastIndex = numbersArray.size() - 1;
                if (numbersArray.isEmpty()) {
                    numbersArray.add(String.valueOf(currentChar));
                } else {
                    if (Character.isDigit(currentChar) || currentChar == '.') { //if currentChar is a number or decimal point
                        if (!Character.isDigit(previousChar) && (previousChar != '.')) { //if previousChar is an operator
                            if (numbersArray.get(lastIndex).equals("-") || numbersArray.get(lastIndex).equals("+")) { //if the previous value in numbersArray is a + or - operator
                                numbersArray.set(lastIndex, numbersArray.get(lastIndex) + (display.charAt(i))); //the currentChar will be concatenated to the operator to create a negative/positive number
                            } else {
                                numbersArray.add(String.valueOf(currentChar));//else adds the currentChar as a new value in the array
                            }
                        } else {
                            numbersArray.set(lastIndex, numbersArray.get(lastIndex) + (display.charAt(i))); //else if the previousChar is not an operator, will concatenate currentChar to the last value in numberArray
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
        doubleArray = new ArrayList<>();
        try {
             for (String str : numbersArray) { //converts <String>numbersArray to doubleArray
                double value = Double.parseDouble(str);
                doubleArray.add(value);
            }
        } catch (NumberFormatException e) {
             syntaxError();
         }
    }

    public double performCalculation() {
        for (int i = 0; i < operatorsArray.size(); i++) {
            if (operatorsArray.get(i) == '/') {
                double divisor = doubleArray.get(i+1);
                if (divisor == 0){
                    divideError();
                }
                double temp = divide(doubleArray.get(i), doubleArray.get(i + 1));
                doubleArray.set(i, temp);
                doubleArray.remove(i + 1);
                operatorsArray.remove(i);
                i--;
            }
        }
        for (int i = 0; i < operatorsArray.size(); i++) {
            if (operatorsArray.get(i) == '*') {
                double temp = multiply(doubleArray.get(i), doubleArray.get(i + 1));
                doubleArray.set(i, temp);
                doubleArray.remove(i + 1);
                operatorsArray.remove(i);
                i--;
            }
        }
        for (int i = 0; i < operatorsArray.size(); i++) {
            if (operatorsArray.get(i) == '+') {
                double temp = add(doubleArray.get(i), doubleArray.get(i + 1));
                doubleArray.set(i, temp);
                doubleArray.remove(i + 1);
                operatorsArray.remove(i);
                i--;
            }
        }
        for (int i = 0; i < operatorsArray.size(); i++) {
            if (operatorsArray.get(i) == '-') {
                double temp = subtract(doubleArray.get(i), doubleArray.get(i + 1));
                doubleArray.set(i, temp);
                doubleArray.remove(i + 1);
                operatorsArray.remove(i);
                i--;
            }
        }
        return (doubleArray.get(0));
    }

    public double divide(double num1, double num2) {
        return num1/num2;
    }

    public double multiply(double num1, double num2) {
        return num1*num2;
    }

    public double add(double num1, double num2) {
        return num1+num2;
    }

    public double subtract(double num1, double num2) {
        return num1-num2;
    }

    public void deleteText() {
        int textLength = this.getText().length();
        if (textLength > 0) {
            this.setText(this.getText().substring(0, textLength - 1));
        }
    }
}