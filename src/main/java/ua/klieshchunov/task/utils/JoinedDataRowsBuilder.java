package ua.klieshchunov.task.utils;

import ua.klieshchunov.task.rows.JoinedDataRow;

import java.util.ArrayList;
import java.util.List;

public class JoinedDataRowsBuilder<K,V1,V2> {
    private List<JoinedDataRow<K, V1, V2>> collection = new ArrayList<>();

    public JoinedDataRowsBuilder<K,V1,V2> addRow(K key, V1 valueLeft, V2 valueRight) {
        collection.add(new JoinedDataRow<>(key, valueLeft, valueRight));
        return this;
    }
    public List<JoinedDataRow<K, V1, V2>> build() {
        List<JoinedDataRow<K, V1, V2>> collectionCopy = List.copyOf(collection);
        collection.clear();

        return collectionCopy;
    }
}
