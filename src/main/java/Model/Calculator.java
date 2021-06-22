package Model;

public class Calculator {

    private String operator;
    private Double num1;
    private Double num2;


    public Calculator(Double num1, Double num2, String operator) {
        this.operator = operator;
        this.num1 = num1;
        this.num2 = num2;
    }

    public Double operation() {
        if(num1 == null || num2 == null || operator==null){
            return null;
        }
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "/":
                return num1 / num2;
            case "*":
                return num1 * num2;
            default:
                return null;
        }

    }

    public String getOperator() {
        return operator;
    }

    public Double getNum1() {
        return num1;
    }

    public Double getNum2() {
        return num2;
    }
}