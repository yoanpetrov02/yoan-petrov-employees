package com.yoanpetrov.yoanpetrovemployees.model;

import com.yoanpetrov.yoanpetrovemployees.model.employees.EmployeePair;
import com.yoanpetrov.yoanpetrovemployees.model.employees.EmployeeProjectRecord;
import com.yoanpetrov.yoanpetrovemployees.model.employees.EmployeeStatistic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

public class EmployeeStatisticTest {

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @Test
    public void testGetAllPairs() throws ParseException {
        List<EmployeeProjectRecord> records = List.of(
                new EmployeeProjectRecord(1, 1, FORMAT.parse("10/10/2010"), FORMAT.parse("30/10/2010")),
                new EmployeeProjectRecord(2, 1, FORMAT.parse("15/10/2010"), FORMAT.parse("20/10/2010")),
                new EmployeeProjectRecord(3, 1, FORMAT.parse("15/10/2010"), FORMAT.parse("30/10/2010"))

        );
        EmployeeStatistic statistic = new EmployeeStatistic(records);
        List<EmployeePair> expected = List.of(
                new EmployeePair(1, 3, 1, 15),
                new EmployeePair(1, 2, 1, 5),
                new EmployeePair(2, 3, 1, 5)
        );
        List<EmployeePair> actual = statistic.getAllPairs();
        System.out.println(Arrays.toString(actual.toArray()));

        Assertions.assertArrayEquals(expected.toArray(), actual.toArray());
    }
}
