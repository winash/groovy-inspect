package console;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayIterator implements Iterator {

    private final Object[] array;
    private int count = 0;

    public ArrayIterator(final Object[] array) {
        this.array = array;
    }

    public ArrayIterator(final Object value) {
        this.array = new Object[]{value};
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    public Object next() {
        if (count > array.length)
            throw new NoSuchElementException();

        return array[count++];
    }

    public boolean hasNext() {
        return count < array.length;
    }
}