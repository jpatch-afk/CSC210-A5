import java.util.Scanner;

/**
 * Class to calculate a result based on user-inputed tokens
 */
public class CalculatePostfix {

    /**
     * Uses postfix calculation as calculator
     * @param tokens calculation that the user wishes to solve
     * @return result of the calculation
     */
    public static Double postfixToResult(Queue<Object> tokens) {
        
        //New Stack to hold calculations and result
        Stack<Double> calc = new Stack<>();

        //Calculating the result 
        while(!tokens.isEmpty()) {
            Object token = tokens.remove();
            if(token instanceof Double) {
                Double num = (Double) token;
                calc.push(num);
            }
            else if(token instanceof Character) {
                Double x = (Double) calc.pop();
                Double y = (Double) calc.pop();
                
                Character op = (Character) token;

                if(x == null || y == null) {
                    throw new IllegalArgumentException("No numbers to operate on. Invalid String.");
                }
                else if(op == '*'){
                    calc.push(x * y); 
                }
                else if(op == '+'){
                    calc.push(x + y); 
                }
                else if(op == '-'){
                    calc.push(y - x); 
                }
                else if (op == '/'){
                    calc.push(y / x); 
                }
                else if (op == '^') {
                    calc.push(Math.pow(y, x));
                }
                else {
                    throw new RuntimeException(token + " is not a valid symbol.");
                }
                if(calc.isEmpty()) {
                    throw new IllegalArgumentException("Not enough numbers to calculate with the operator.");
                }
            }
        }
        return calc.pop();
    }
    
    /** 
    * main method
    * @param args list of arguments 
    */
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter the calculation you wish to solve: \n");
    
        String calc = input.nextLine();

        //Line to create space
        System.out.println();
    
        Queue<Object> tokens = Tokenizer.readTokens(calc);
        Double result = postfixToResult(tokens);
        System.out.println("Result of calculations: \n " + result.toString());

        //Close input 
        input.close();
    }    
}