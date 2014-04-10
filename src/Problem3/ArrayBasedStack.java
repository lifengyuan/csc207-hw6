package Problem3;


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A simple array-based stack.
 *
 * @author Samuel A. Rebelsky
 * @author Fengyuan Li
 * 
 * 
 * Citation: code from the CSC207 Linear Structures Lab
 */
public class ArrayBasedStack<T> implements Stack<T> {
    // +--------+----------------------------------------------------------
    // | Fields |
    // +--------+

    /**
     * The values in the stack.
     */
    T[] values;

    /**
     * The number of values in the stack.
     */
    int size;

    // +--------------+----------------------------------------------------
    // | Constructors |
    // +--------------+

    /**
     * Create a new stack that holds N elements.
     */
    @SuppressWarnings({"unchecked"})
    public ArrayBasedStack(int size) throws Exception {
        if (size <= 0) {
            throw new Exception("Stacks must have a positive size.");
        } // if (size <= 0)
        this.values = (T[]) new Object[size];
        size = 0;
    } // ArrayBasedStack(int)

    // +-------------------------+-----------------------------------------
    // | LinearStructure Methods |
    // +-------------------------+

    @Override
    public boolean isEmpty() {
        return this.size <= 0;
    } // isEmpty()

    @Override
    public boolean isFull() {
        return this.size == this.values.length;
    } // isFull()

    @Override
    public T peek() {
        return this.values[this.size-1];
    } // peek()

    @Override
    public void put(T val) throws Exception {
        if (this.isFull()) {
            throw new Exception("full");
        } // if full
        this.values[this.size++] = val;
    } // put(T)

    @Override
    public T get() throws Exception {
        if (this.isEmpty()) {
            throw new Exception("empty");
        } // if empty
        return values[--size];
    } // get()

    @Override
    public Iterator<T> iterator() {
        return new ArrayBasedStackIterator<T>(this);
    } // iterator()

    // +---------------+---------------------------------------------------
    // | Stack Methods |
    // +---------------+

    @Override
    public void push(T val) throws Exception {
        this.put(val);
    } // push(T)

    @Override
    public T pop() throws Exception {
        return this.get();
    } // pop

} // ArrayBasedStack<T>

class ArrayBasedStackIterator<T> implements Iterator<T> {
    // +--------+----------------------------------------------------------
    // | Fields |
    // +--------+

    /**
     * The current position in the iteration.
     */
    int i;  

    /**
     * The array that contains the values in the stack.
     */
    T[] values;


    // +--------------+----------------------------------------------------
    // | Constructors |
    // +--------------+

    /**
     * Create a new iterator.
     */
    public ArrayBasedStackIterator(ArrayBasedStack<T> abs) {
        this.i = abs.size;
        this.values = (T[]) abs.values;
    } // ArrayBasedStackIterator

    // +---------+---------------------------------------------------------
    // | Methods |
    // +---------+

    @Override
    public T next() throws NoSuchElementException {
        if (!this.hasNext()) {
            throw new NoSuchElementException("no elements remain");
        } // if no elements 
        return this.values[--i];
    } // next()

    @Override
    public boolean hasNext() {
        return (i > 0);
    } // hasNext9)

    @Override
    public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    } // remove()
} // ArrayBasedStackIterator<T>