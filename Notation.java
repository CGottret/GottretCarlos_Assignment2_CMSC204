public class Notation {
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c =='/';
    }

    private static int precedenceLevel(char c) {
        switch (c) {
            case '-':
            case '+':
                return 0;
            case '*':
            case '/':
                return 1;
        }
        return -1;
    }
    public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException{
        NotationStack<String> stack = new NotationStack<>(postfixExpr.length());
        double result = 0.0;
        try {
            for (int i = 0; i < postfixExpr.length(); i++){
                char currentCharacter = postfixExpr.charAt(i);
                if (currentCharacter != ' '){
                    if (Character.isDigit(currentCharacter) || currentCharacter == '('){
                        stack.push(String.valueOf(currentCharacter));
                    }
                    else if (isOperator(currentCharacter)){
                        if (stack.size() < 2){
                            throw new InvalidNotationFormatException("There are less than 2 values in the stack");
                        }

                        Double second = Double.parseDouble((String) stack.pop());
                        Double first = Double.parseDouble((String) stack.pop());
                        if (currentCharacter == '+'){
                            result = first + second;
                        }
                        else if (currentCharacter == '-'){
                            result = first - second;
                        }
                        else if (currentCharacter == '/'){
                            result = first / second;
                        }
                        else if (currentCharacter == '*'){
                            result = first * second;
                        }
                        stack.push(String.valueOf(result));
                    }
                }
            }
            if (stack.size() != 1) {
                throw new InvalidNotationFormatException("There is more than 1 value");
            }

        } catch (StackOverflowException | StackUnderflowException e){
        }
        return result;
    }

    public static double evaluateInfixExpression(String infixExpr) throws InvalidNotationFormatException{
        return 0.0;
    }
    public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException{
        NotationStack<String> stack = new NotationStack<>(postfix.length());
        try {
            for (int i = 0; i <postfix.length(); i++){
                char currentCharacter = postfix.charAt(i);
                if (currentCharacter != ' '){
                    if (Character.isDigit(currentCharacter)){
                        stack.push(String.valueOf(currentCharacter));
                    }
                    else if (isOperator(currentCharacter)){
                        if (stack.size() < 2){
                            throw new InvalidNotationFormatException("Stack has less than 2 characters");
                        }
                        String second = (String) stack.pop();
                        String first = (String) stack.pop();
                        String result = "(" + first + currentCharacter + second + ")";
                        stack.push(result);
                    }
                }
            }
            if (stack.size() != 1) {
                throw new InvalidNotationFormatException("There is more than 1 value");
            }
        } catch (StackUnderflowException | StackOverflowException e){

        }
        return stack.toString();
    }
    public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {
        NotationQueue<Character> solutionQ = new NotationQueue<>(infix.length());
        NotationStack<Character> charS = new NotationStack<>(infix.length());
        try {
            for (int i = 0; i < infix.length(); i++){
                char currentCharacter = infix.charAt(i);
                // if it's not a space
                if (currentCharacter != ' ') {
                    // if it's a digit
                    if (Character.isDigit(currentCharacter)) {
                        solutionQ.enqueue(currentCharacter);
                    } else if (currentCharacter == '(') {
                        charS.push(currentCharacter);
                    } else if (isOperator(currentCharacter)) {
                        char top = (Character) charS.top();
                        while (isOperator(top)) {
                            int topPrecedenceLevel = precedenceLevel(top);
                            int currentPrecedenceLevel = precedenceLevel(currentCharacter);
                            if (topPrecedenceLevel >= currentPrecedenceLevel) {
                                char toPop = (Character) charS.pop();
                                solutionQ.enqueue(toPop);
                            }
                        }
                        charS.push(currentCharacter);
                    } else if (currentCharacter == ')'){
                        while (!charS.isEmpty()){
                            char operator = (Character) charS.top();
                            if (operator == '(') {
                                break;
                            } else {
                                solutionQ.enqueue(charS.pop());
                            }
                        }

                        if (charS.isEmpty() || (Character) charS.top() != '(') {
                            throw new InvalidNotationFormatException("There is no left parenthesis");
                        }
                        // discard left parenthesis
                        charS.pop();
                    }
                }
            }

            while (!charS.isEmpty()) {
                solutionQ.enqueue(charS.pop());
            }
        } catch (StackUnderflowException | StackOverflowException | QueueOverflowException e) {

        }
        return solutionQ.toString();
    }
}
