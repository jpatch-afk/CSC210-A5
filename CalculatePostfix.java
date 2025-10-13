import java.util.ArrayDeque;
import java.util.Scanner;

public class CalculatePostfix {

    /**
     * 
     * @param tokens
     * @return 
     */
    public static Double postfixToResult(Queue<Object> tokens) {
        
        //parsing tokens onto a stack 
        ArrayDeque<Object> storage = new ArrayDeque<>(); 
        
        while(!tokens.isEmpty()) {
            Object token = tokens.peek(); 
            storage.push(token);
            tokens.remove();
        }

        //New Stack to hold result
        Stack<Double> calc = new Stack<>();

        //variable to hold result 
        Double result = 0.0; 

        //Calculating the result 
        while(!storage.isEmpty()) {
            Object token = storage.peek();
            if(token instanceof Double) {
                calc.push((Double) token);
            }
            if(token instanceof Character) {
                Double x = (Double) storage.pop();
                Double y = (Double) storage.pop();

                if(x == null || y == null) {
                    throw new IllegalArgumentException("No numbers to operate on. Invalid String.");
                }
                else if(token == "*"){
                    result = x * y; 
                    calc.push(result); 
                }
                else if(token == "+"){
                    result = x + y;
                    calc.push(result); 
                }
                else if(token == "-"){
                    result = x - y;
                    calc.push(result); 
                }
                else if (token == "/"){
                    result = x / y;
                    calc.push(result); 
                }
                else {
                    throw new RuntimeException(token + " is not a valid symbol.");
                }
                x = null;
                y = null;
            }
            storage.remove();
        }
        System.out.println("Result of calculations: \n " + calc.peek().toString());
        return result;
    }
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter the calculation you wish to solve: \n");
    
        String calc = input.nextLine();
    
        Queue<Object> tokens = Tokenizer.readTokens(calc);
        System.out.println(postfixToResult(tokens));

        //Close input 
        input.close();
    }    
}