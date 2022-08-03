package ua.klieshchunov.task.operationImpl;

import ua.klieshchunov.task.JoinOperation;
import ua.klieshchunov.task.rows.DataRow;
import ua.klieshchunov.task.rows.JoinedDataRow;

import java.util.Collection;
import java.util.stream.Collectors;

public class InnerJoinOperation<V1,V2,R> implements JoinOperation<DataRow<R,V1>, DataRow<R,V2>, JoinedDataRow<R, V1, V2>> {

    @Override
    public Collection<JoinedDataRow<R, V1, V2>> join(Collection<DataRow<R, V1>> leftCollection, Collection<DataRow<R, V2>> rightCollection) {

        return leftCollection.stream()
                .flatMap(left -> rightCollection.stream()
                        .filter(right -> left.key.equals(right.key))
                        .map(right -> new JoinedDataRow<>(left.key, left.value, right.value)))
                .collect(Collectors.toList());
    }
}
