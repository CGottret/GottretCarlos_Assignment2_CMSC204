import java.util.ArrayList;

public class NotationStack<T> implements StackInterface {
    private Object[] array;
    private int top;
    private int maxSize;
    public NotationStack (int size){
        array = new Object[size];
        top = -1;
        maxSize = size;
    }
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean isFull() {
        return top == maxSize - 1;
    }

    @Override
    public Object pop() throws StackUnderflowException {
        if (isEmpty()){
            throw new StackUnderflowException("The stack is empty");
        }
        Object toPop = array[top];
        top -= 1;
        return toPop;
    }

    @Override
    public Object top() throws StackUnderflowException {
        if (isEmpty()){
            throw new StackUnderflowException("The stack is empty");
        }
        return array[top];
    }

    @Override
    public int size() {
        return top + 1;
    }

    @Override
    public boolean push(Object e) throws StackOverflowException {
        if (isFull()){
            throw new StackOverflowException("The Stack is full");
        }
        top += 1;
        array[top] = e;
        return true;
    }

    @Override
    public String toString() {
        return toString("");
    }


    @Override
    public String toString(String delimiter) {
        String result = "";
        for (int i = 0; i < top + 1; i++){
            result += String.valueOf(array[i]);
            if (i != top){
                result += delimiter;
            }
        }
        return result;
    }

    @Override
    public void fill(ArrayList list) throws StackOverflowException{
        Object[] array2 = new Object[list.size()];
        for (int i = 0; i < list.size(); i++){
            array2[i] = list.get(i);
        }
        for (int a = 0; a < list.size(); a++){
            push(array2[a]);
        }
    }
}
