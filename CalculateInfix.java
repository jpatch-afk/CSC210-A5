import java.util.ArrayDeque;

public class CalculateInfix {
    public static Double infixToPostfix(ArrayDeque<Object> tokens) {

        
        ArrayDeque<Object> queue = new ArrayDeque<>();
        ArrayDeque<Object> stack = new ArrayDeque<>();

        while(!tokens.isEmpty()) {

            Object token = tokens.getFirst();
            if(token instanceof Double) {
                queue.add(token);
            }
            if(token instanceof Character) {
                if(token == "(") {
                    queue.addFirst(token);
                }
            }





        }






    }
}
