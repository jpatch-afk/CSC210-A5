import java.util.ArrayDeque;

public class CalculateInfix {
    public static Double infixToPostfix(ArrayDeque<Object> tokens) {

        
        Queue<Object> queue = new Queue<>(); //Numbers
        ArrayDeque<Object> stack = new ArrayDeque<>(); //Operators

        while(!tokens.isEmpty()) {
            tokens.remove();

            Object token = tokens.getFirst();
            if(token instanceof Double) {
                queue.add(token);
            }
            if(token instanceof Character) {
                if(isOperator(token)) {
                    while(stack.size() > 0 && stack.peekLast() instanceof Character && (((precedence(stack.peekLast()) - precedence(token)) == 0) && !isRightAssociative(token) || (precedence(stack.peekLast())-precedence(token))>0)) {
                        Object obj = stack.removeLast();
                        queue.add(obj);
                    }
                }
                else if(token.equals('(')){
                    stack.push(token);
                    
                }
                else if(token.equals(')')) {
                    while(!stack.peekLast().equals(')')){
                        Object obj = stack.removeLast();
                        queue.add(obj);
                        if(stack.isEmpty()){
                            throw new RuntimeException("Mismatched parentheses. Please fix in order to complete your computation.");
                        }
                    }
                    stack.pop();
                }
            }
        }
        
        while(!stack.isEmpty()) {
            if(stack.peek().equals('(') || stack.peek().equals(')')) {
                throw new RuntimeException("Mismatched parentheses. Please fix in order to complete your computation.");
            }
            if(isOperator(stack.peek())){
                Object obj = stack.pop();
                queue.add(obj);
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
            return -1; //if token is an instance of a Double and not a character
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
        return token.equals('*') || token.equals('/') || token.equals('+') || token.equals('-');
    }

}