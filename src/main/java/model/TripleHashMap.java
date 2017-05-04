/*
* Requirements mandating inclusion:
*
* This is a utility class used to store fields on the GameCreation mode interface.
* It is indirectly needed by all requirements related to GameCreation mode.
* */

package model;


import java.util.*;

public class TripleHashMap<T, U, V> implements Iterable {

    private HashMap<T, ArrayList<Object>> map = new LinkedHashMap<>();

    public TripleHashMap() {

    }

    public void put(T key, U value1, V value2) {
        ArrayList<Object> values = new ArrayList<>();
        values.add(value1);
        values.add(value2);
        map.put(key, values);
    }

    public U getValue1(T key) {
        return (U) map.get(key).get(0);
    }

    public V getValue2(T key) {
        return (V) map.get(key).get(1);
    }

    public ArrayList<T> keys() {
        return new ArrayList<>(map.keySet());
    }

    public Set<T> keySet() {
        return map.keySet();
    }


    @Override
    public Iterator<T> iterator() {
        return map.keySet().iterator();
    }
}
