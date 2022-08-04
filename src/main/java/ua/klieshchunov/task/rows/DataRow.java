package ua.klieshchunov.task.rows;

import java.util.Objects;

public class DataRow <K, V> {
    public K key;
    public V value;

    public DataRow(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataRow<?, ?> dataRow = (DataRow<?, ?>) o;
        return key.equals(dataRow.key) && Objects.equals(value, dataRow.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @Override
    public String toString() {
        return "DataRow{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
