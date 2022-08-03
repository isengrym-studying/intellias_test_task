import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ua.klieshchunov.task.JoinOperation;
import ua.klieshchunov.task.operationImpl.InnerJoinOperation;
import ua.klieshchunov.task.rows.DataRow;
import ua.klieshchunov.task.rows.JoinedDataRow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class InnerJoinOperationTest {
    public JoinOperation<DataRow<Integer,String>, DataRow<Integer,String>, JoinedDataRow<Integer, String, String>> operation;

    static Collection<DataRow<Integer, String>> countriesCollection;
    static Collection<DataRow<Integer, String>> citiesCollection;

    public Collection<JoinedDataRow<Integer, String, String>> expectedCollection;
    public Collection<JoinedDataRow<Integer, String, String>> actualCollection;

    @BeforeAll
    public static void createRows() {
        DataRow<Integer, String> firstCountry = new DataRow<>(0, "Ukraine");
        DataRow<Integer, String> secondCountry = new DataRow<>(1, "Germany");
        DataRow<Integer, String> thirdCountry = new DataRow<>(2, "France");

        DataRow<Integer, String> firstCity = new DataRow<>(0, "Kyiv");
        DataRow<Integer, String> secondCity = new DataRow<>(1, "Berlin");
        DataRow<Integer, String> thirdCity = new DataRow<>(3, "Budapest");

        countriesCollection = new ArrayList<>(Arrays.asList(firstCountry, secondCountry, thirdCountry));
        citiesCollection = new ArrayList<>(Arrays.asList(firstCity, secondCity, thirdCity));
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

}
