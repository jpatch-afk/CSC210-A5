import java.util.ArrayDeque;
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

        while(!storage.isEmpty()) {
            Object token = storage.peek();
            if(storage.peek() instanceof Double) {
                calc.push((Double) token);
            }
            if(token instanceof Character) {
                Double x = (Double) storage.pop();
                Double y = (Double) storage.pop();
    
                if(token == "*"){
                    result = x * y; 
                    storage.push(result); 
                }
                else if (token == "+"){
                    result = x + y;
                    storage.push(result); 
                }
                else if(token == "-"){
                    result = x - y;
                    storage.push(result); 
                }
                else if (token == "/"){
                    result = x / y;
                    storage.push(result); 
                }
            }
            storage.remove();
        }
        return result;
    }
}


public static void main(String[] args) {

}

//use readTokens here 
//then a print statement calling the postfix method