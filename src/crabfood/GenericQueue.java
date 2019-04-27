package crabfood;

public class GenericQueue<E> {

    private java.util.LinkedList<E> list = new java.util.LinkedList<>();

    public void enqueue(E e) {
        list.addLast(e);
    }
    
    public E dequeue() {
        return list.removeFirst();
    }

    public int getSize() {
        return list.size();
    }
    
    public boolean remove() {
        return list.remove(this);
    }

    @Override
    public String toString() {
        return "Queue: " + list.toString();
    }
}
