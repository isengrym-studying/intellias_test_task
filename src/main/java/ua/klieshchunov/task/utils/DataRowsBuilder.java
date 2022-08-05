package ua.klieshchunov.task.utils;

import ua.klieshchunov.task.rows.DataRow;
import java.util.ArrayList;
import java.util.List;

public class DataRowsBuilder<K,V> {

    private List<DataRow<K, V>> collection = new ArrayList<>();

    public DataRowsBuilder<K,V> addRow(K key, V value) {
        collection.add(new DataRow<>(key, value));
        return this;
    }

    public List<DataRow<K,V>> build() {
        List<DataRow<K, V>> collectionCopy = List.copyOf(collection);
        collection.clear();

        return collectionCopy;
    }
}
