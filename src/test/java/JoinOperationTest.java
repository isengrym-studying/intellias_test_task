import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ua.klieshchunov.task.JoinOperation;
import ua.klieshchunov.task.operationImpl.InnerJoinOperation;
import ua.klieshchunov.task.operationImpl.LeftJoinOperation;
import ua.klieshchunov.task.operationImpl.RightJoinOperation;
import ua.klieshchunov.task.rows.DataRow;
import ua.klieshchunov.task.rows.JoinedDataRow;
import ua.klieshchunov.task.utils.DataRowsBuilder;
import ua.klieshchunov.task.utils.JoinedDataRowsBuilder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class JoinOperationTest {
    JoinOperation<DataRow<Integer,String>, DataRow<Integer,String>, JoinedDataRow<Integer, String, String>> operation;

    static Collection<DataRow<Integer, String>> countriesCollection;
    static Collection<DataRow<Integer, String>> citiesCollection;

    Collection<JoinedDataRow<Integer, String, String>> expectedCollection;
    Collection<JoinedDataRow<Integer, String, String>> actualCollection;

    JoinedDataRowsBuilder<Integer, String, String> joinedDataRowsBuilder;
    DataRowsBuilder<Integer, String> dataRowsBuilder;

    @BeforeEach
    public void createRows() {
        dataRowsBuilder = new DataRowsBuilder<>();
        joinedDataRowsBuilder = new JoinedDataRowsBuilder<>();

        countriesCollection = dataRowsBuilder
                .addRow(0, "Ukraine")
                .addRow(1, "Germany")
                .addRow(2, "France")
                .build();

        citiesCollection = dataRowsBuilder
                .addRow(0, "Kyiv")
                .addRow(1, "Berlin")
                .addRow(3, "Budapest")
                .build();
    }

    @Test
    public void testInnerJoin() {
        operation = new InnerJoinOperation<>();

        expectedCollection = joinedDataRowsBuilder
                .addRow(0, "Ukraine", "Kyiv")
                .addRow(1, "Germany", "Berlin")
                .build();

        actualCollection = operation.join(countriesCollection, citiesCollection);

        assertEquals(expectedCollection, actualCollection);
    }

    @Test
    public void testLeftJoin() {
        operation = new LeftJoinOperation<>();

        expectedCollection = joinedDataRowsBuilder
                .addRow(0, "Ukraine", "Kyiv")
                .addRow(1, "Germany", "Berlin")
                .addRow(2, "France", null)
                .build();

        actualCollection = operation.join(countriesCollection, citiesCollection);

        assertEquals(expectedCollection, actualCollection);
    }

    @Test
    public void testRightJoin() {
        operation = new RightJoinOperation<>();

        expectedCollection = joinedDataRowsBuilder
                .addRow(0, "Ukraine", "Kyiv")
                .addRow(1, "Germany", "Berlin")
                .addRow(3, null, "Budapest")
                .build();

        actualCollection = operation.join(countriesCollection, citiesCollection);

        assertEquals(expectedCollection, actualCollection);
    }

    @Test
    public void testInnerLeftRightJoinOperationWithNullParameters() {
        countriesCollection = null;

        operation = new InnerJoinOperation<>();
        assertThrows(IllegalArgumentException.class, () -> operation.join(countriesCollection, citiesCollection));
        operation = new LeftJoinOperation<>();
        assertThrows(IllegalArgumentException.class, () -> operation.join(countriesCollection, citiesCollection));
        operation = new RightJoinOperation<>();
        assertThrows(IllegalArgumentException.class, () -> operation.join(countriesCollection, citiesCollection));
    }

    @Test
    public void testInnerLeftRightJoinOperationWithEmptyLeftCollection() {
        countriesCollection = new ArrayList<>();

        operation = new InnerJoinOperation<>();
        actualCollection = operation.join(countriesCollection, citiesCollection);
        expectedCollection = Collections.emptyList();
        assertEquals(actualCollection, expectedCollection);

        operation = new LeftJoinOperation<>();
        actualCollection = operation.join(countriesCollection, citiesCollection);
        expectedCollection = Collections.emptyList();
        assertEquals(actualCollection, expectedCollection);

        operation = new RightJoinOperation<>();
        actualCollection = operation.join(countriesCollection, citiesCollection);
        expectedCollection = joinedDataRowsBuilder
                .addRow(0, null, "Kyiv")
                .addRow(1, null, "Berlin")
                .addRow(3, null, "Budapest")
                .build();
        assertEquals(actualCollection, expectedCollection);
    }

    @Test
    public void testInnerLeftRightJoinOperationsWithEmptyRightCollection() {
        citiesCollection = new ArrayList<>();

        operation = new InnerJoinOperation<>();
        actualCollection = operation.join(countriesCollection, citiesCollection);
        expectedCollection = Collections.emptyList();
        assertEquals(actualCollection, expectedCollection);

        operation = new LeftJoinOperation<>();
        actualCollection = operation.join(countriesCollection, citiesCollection);
        expectedCollection = joinedDataRowsBuilder
                .addRow(0, "Ukraine", null)
                .addRow(1, "Germany", null)
                .addRow(2, "France", null)
                .build();
        assertEquals(actualCollection, expectedCollection);

        operation = new RightJoinOperation<>();
        actualCollection = operation.join(countriesCollection, citiesCollection);
        expectedCollection = Collections.emptyList();
        assertEquals(actualCollection, expectedCollection);
    }
}
