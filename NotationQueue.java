import java.util.ArrayList;

public class NotationQueue<T> implements QueueInterface {
    private Object[] array;
    private int front;
    private int end;
    private int maxSize;
    private int currentSize;
    public NotationQueue (int size){
        array = new Object[size];
        maxSize = size;
        front = 0;
        end = -1;
        currentSize = 0;
    }
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean isFull() {
        return size() == maxSize;
    }

    @Override
    public Object dequeue() throws QueueUnderflowException {
        Object toRemove;
        if (isEmpty()){
            throw new QueueUnderflowException("Unable to dequeue");
        }
        toRemove = array[front];
        front = (front + 1) % maxSize;
        currentSize -= 1;
        return  toRemove;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean enqueue(Object e) throws QueueOverflowException {
        if (isFull()){
            throw new QueueOverflowException("Unable to enqueue");
        }
        end = (end + 1) % maxSize;
        array[end] = e;
        currentSize += 1;
        return true;
    }

    @Override
    public String toString() {
        return toString("");
    }

    @Override
    public String toString(String delimiter) {
        String result = "";
        for (int i = front; i < currentSize; i++){
            result += String.valueOf(array[i]);
            if (i != currentSize - 1){
                result += delimiter;
            }
        }
        return result;
    }

    @Override
    public void fill(ArrayList list) throws QueueOverflowException{
        Object[] array2 = new Object[list.size()];
        for (int i = 0; i < list.size(); i++){
            array2[i] = list.get(i);
        }
        for (int a = 0; a < list.size(); a++){
            enqueue(array2[a]);
        }
    }
}
