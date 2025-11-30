import java.util.Scanner;
/**
 * Class to convert infix to postfix
 */
public class CalculateInfix {
    /**
     * Convert infix to postfix and calls postfix method to return result
     * @param tokens numbers and characters to calculate 
     * @return Double result
     */
    public static Double infixToPostfix(Queue<Object> tokens) {

        Queue<Object> queue = new Queue<>(); //Numbers
        Stack<Object> stack = new Stack<>(); //Operators

        while(!tokens.isEmpty()) {
            Object token = tokens.remove();
            if(token instanceof Double) {
                queue.add((Double) token);
            }
            if(token instanceof Character) {
                Character c = (Character) token;
                if(isOperator(c)) {
                    while(!stack.isEmpty() && stack.peek() instanceof Character){
                        Character first = (Character) stack.peek(); 

                        if(isOperator(first)){
                            if(!isRightAssociative(c) && precedence(first) >= precedence(c)){ //left-associative
                                queue.add(stack.pop());
                                continue;
                            }
                            else if(isRightAssociative(c) && precedence(first) > precedence(c)){ //right-associative 
                                queue.add(stack.pop()); 
                                continue;
                            }
                        }
                        break; //break if no condition matches
                    }
                    stack.push(c);
                }
                else if(c.equals('(')){
                    stack.push(c);   
                }
                else if(token.equals(')')) {
                    while(!stack.peek().equals('(' ) && !stack.isEmpty()){
                        queue.add(stack.pop());
                        if(stack.isEmpty()){
                            throw new RuntimeException("Mismatched parentheses. Please fix in order to complete your computation.");
                        }
                    }
                    stack.pop(); //removes left parentheses 
                }
            }
        }
        
        while(!stack.isEmpty()) {
            Character c = (Character) stack.pop();
            if(c.equals('(') || c.equals(')')) {
                throw new RuntimeException("Mismatched parentheses. Please fix in order to complete your computation.");
            }
            if(isOperator(c)){
                queue.add(c);
            }
        }   
        return CalculatePostfix.postfixToResult(queue); //runs the calculations
    }

    /**
     * Returns the precedence of a character to determine its location on the stack (PEMDAS)
     * @param token character to find the importance of 
     * @return numerical importance of the character given
     */
    public static int precedence(Object token) {
        if (token.equals('^')) {
            return 3; 
        }
        else if(token.equals('*')) {
            return 2;
        }
        else if(token.equals('/')) {
            return 2;
        }
        else if(token.equals('+')) {
            return 1; 
        }
        else if(token.equals('-')) {
            return 1; 
        }
        else {
            return 0; //if token is an instance of a Double and not a character
        }
            
    }

    /**
     * Tests if the character token is right-associative 
     * @param token character to test whether if it is right-associative 
     * @return whether the character is left- or right-associative 
     */
    public static boolean isRightAssociative(Object token) {
        return token.equals('^');
    }

    /**
     * Tests whether the token Object is an operator character to distinguish between operators and parentheses
     * @param token character to be tested
     * @return whether the character is an operator or not 
     */
    public static boolean isOperator(Object token) {
        return token.equals('*') || token.equals('/') || token.equals('+') || token.equals('-') || token.equals('^');
    }

    /** 
    * main method
    * @param args list of arguments 
    */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the calculation you wish to solve: \n");
    

        String calc = input.nextLine();
        Queue<Object> tokens = Tokenizer.readTokens(calc);


        Double result = infixToPostfix(tokens);
        System.out.println("Result of calculations: " + result.toString());

        //Close input
        input.close();
    }
}