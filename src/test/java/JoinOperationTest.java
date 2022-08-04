import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ua.klieshchunov.task.JoinOperation;
import ua.klieshchunov.task.operationImpl.InnerJoinOperation;
import ua.klieshchunov.task.operationImpl.LeftJoinOperation;
import ua.klieshchunov.task.operationImpl.RightJoinOperation;
import ua.klieshchunov.task.rows.DataRow;
import ua.klieshchunov.task.rows.JoinedDataRow;
import ua.klieshchunov.task.utils.IntegerStringRowsUtils;
import ua.klieshchunov.task.utils.PropertiesUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class JoinOperationTest {
    public JoinOperation<DataRow<Integer,String>, DataRow<Integer,String>, JoinedDataRow<Integer, String, String>> operation;

    public static Collection<DataRow<Integer, String>> countriesCollection;
    public static Collection<DataRow<Integer, String>> citiesCollection;

    public Collection<JoinedDataRow<Integer, String, String>> expectedCollection;
    public Collection<JoinedDataRow<Integer, String, String>> actualCollection;

    @BeforeEach
    public void createRows() {
        Properties countries = PropertiesUtils.loadProperties("src/main/resources/countries.properties");
        Properties cities = PropertiesUtils.loadProperties("src/main/resources/cities.properties");

        countriesCollection = IntegerStringRowsUtils.createListOfDataRowsFromProperties(countries);
        citiesCollection = IntegerStringRowsUtils.createListOfDataRowsFromProperties(cities);
    }

    @Test
    public void testJoinOperationWithNullParameters() {
        countriesCollection = null;

        operation = new InnerJoinOperation<>();
        assertThrows(IllegalArgumentException.class, () -> operation.join(countriesCollection, citiesCollection));
        operation = new LeftJoinOperation<>();
        assertThrows(IllegalArgumentException.class, () -> operation.join(countriesCollection, citiesCollection));
        operation = new RightJoinOperation<>();
        assertThrows(IllegalArgumentException.class, () -> operation.join(countriesCollection, citiesCollection));
    }

    @Test
    public void testInnerJoin() {
        operation = new InnerJoinOperation<>();

        JoinedDataRow<Integer, String, String> firstJoined = new JoinedDataRow<>(0, "Ukraine", "Kyiv");
        JoinedDataRow<Integer, String, String> secondJoined = new JoinedDataRow<>(1, "Germany", "Berlin");

        expectedCollection = new ArrayList<>(Arrays.asList(firstJoined, secondJoined));
        actualCollection = operation.join(countriesCollection, citiesCollection);

        assertEquals(expectedCollection, actualCollection);
    }

    @Test
    public void testLeftJoin() {
        operation = new LeftJoinOperation<>();

        JoinedDataRow<Integer, String, String> firstJoined = new JoinedDataRow<>(0, "Ukraine", "Kyiv");
        JoinedDataRow<Integer, String, String> secondJoined = new JoinedDataRow<>(1, "Germany", "Berlin");
        JoinedDataRow<Integer, String, String> thirdJoined = new JoinedDataRow<>(2, "France", null);

        expectedCollection = new ArrayList<>(Arrays.asList(firstJoined, secondJoined, thirdJoined));
        actualCollection = operation.join(countriesCollection, citiesCollection);

        assertEquals(expectedCollection, actualCollection);
    }

    @Test
    public void testRightJoin() {
        operation = new RightJoinOperation<>();

        JoinedDataRow<Integer, String, String> firstJoined = new JoinedDataRow<>(0, "Ukraine", "Kyiv");
        JoinedDataRow<Integer, String, String> secondJoined = new JoinedDataRow<>(1, "Germany", "Berlin");
        JoinedDataRow<Integer, String, String> thirdJoined = new JoinedDataRow<>(3, null, "Budapest");

        expectedCollection = new ArrayList<>(Arrays.asList(firstJoined, secondJoined, thirdJoined));
        actualCollection = operation.join(countriesCollection, citiesCollection);

        assertEquals(expectedCollection, actualCollection);
    }


}
