package ua.klieshchunov.task.rows;

import java.util.Objects;

public class JoinedDataRow <K, V1, V2> {
    public K key;
    public V1 firstValue;
    public V2 secondValue;

    public JoinedDataRow(K key, V1 firstValue, V2 secondValue) {
        this.key = key;
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JoinedDataRow<?, ?, ?> that = (JoinedDataRow<?, ?, ?>) o;
        return key.equals(that.key) && Objects.equals(firstValue, that.firstValue) && Objects.equals(secondValue, that.secondValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, firstValue, secondValue);
    }

    @Override
    public String toString() {
        return "JoinedDataRow{" +
                "key=" + key +
                ", firstValue=" + firstValue +
                ", secondValue=" + secondValue +
                '}';
    }
}
