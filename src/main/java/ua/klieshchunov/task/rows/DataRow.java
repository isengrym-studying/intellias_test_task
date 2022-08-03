package ua.klieshchunov.task.rows;

public class DataRow <K, V> {
    public K key;
    public V value;

    public DataRow(K key, V value) {
        this.key = key;
        this.value = value;
    }
}
