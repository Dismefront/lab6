package collection;

import java.util.ArrayList;

/**
 * Interface containing basic collection functions
 * @param <T> will be used Worker in further program
 */

public interface CollectionManager<T> {

    long generateId();

    boolean add(T element);

    String updateId(T element);

    String removeById(long id);

    void clear();

    void removeFirst();

    void sort();

    boolean isEmpty();

    ArrayList<T> getCollection();

}
