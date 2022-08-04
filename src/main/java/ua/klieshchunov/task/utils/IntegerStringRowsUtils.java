package ua.klieshchunov.task.utils;

import ua.klieshchunov.task.rows.DataRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class IntegerStringRowsUtils {
    public static List<DataRow<Integer,String>> createListOfDataRowsFromProperties(Properties properties) {
        List<DataRow<Integer, String>> storage = new ArrayList<>();

        for (Map.Entry<Object, Object> property: properties.entrySet())
            storage.add(new DataRow<>(Integer.valueOf((String)property.getKey()), (String)property.getValue()));

        return storage;
    }

}
