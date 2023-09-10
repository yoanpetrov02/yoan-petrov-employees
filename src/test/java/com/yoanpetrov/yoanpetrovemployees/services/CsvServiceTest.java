package com.yoanpetrov.yoanpetrovemployees.services;

import com.yoanpetrov.yoanpetrovemployees.model.employees.EmployeeProjectRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class CsvServiceTest {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final CsvService SERVICE = new CsvService();

    private File testCsv;

    @BeforeEach
    public void createFile() throws IOException {
        testCsv = Files.createFile(Path.of("test.csv")).toFile();
    }

    @Test
    public void testReadRecordsFromCsv() throws ParseException, IOException {
        writeLines(new String[] {
                "143, 12, 2013-11-01, 2014-01-05",
                "218, 10, 2012-05-16, 2013-02-06",
                "143, 10, 2009-01-01, 2011-04-27",
        });
        List<EmployeeProjectRecord> expected = List.of(
                new EmployeeProjectRecord(
                        143, 12, DATE_FORMAT.parse("2013-11-01"), DATE_FORMAT.parse("2014-01-05")),
                new EmployeeProjectRecord(
                        218, 10, DATE_FORMAT.parse("2012-05-16"), DATE_FORMAT.parse("2013-02-06")),
                new EmployeeProjectRecord(
                        143, 10, DATE_FORMAT.parse("2009-01-01"), DATE_FORMAT.parse("2011-04-27"))
        );
        List<EmployeeProjectRecord> actual = SERVICE.readRecordsFromCsv(testCsv);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testReadRecordsNullDate() throws IOException {
        writeLines(new String[] {"143, 12, 2013-11-01, NULL"});
        List<EmployeeProjectRecord> records = SERVICE.readRecordsFromCsv(testCsv);

        Assertions.assertNotNull(records.get(0).getDateTo());
    }

    @Test
    public void testReadRecordsDifferentFormats() throws IOException, ParseException {
        writeLines(new String[] {
                "143, 12, 2013-11-01, 2014-01-05",
                "218, 10, 2012-05-16, 13-02-2013",
                "143, 10, 2009-01-01, 04-27-2011",
        });
        List<EmployeeProjectRecord> expected = List.of(
                new EmployeeProjectRecord(
                        143, 12, DATE_FORMAT.parse("2013-11-01"), DATE_FORMAT.parse("2014-01-05")),
                new EmployeeProjectRecord(
                        218, 10, DATE_FORMAT.parse("2012-05-16"), DATE_FORMAT.parse("2013-02-13")),
                new EmployeeProjectRecord(
                        143, 10, DATE_FORMAT.parse("2009-01-01"), DATE_FORMAT.parse("2011-04-27"))
        );
        List<EmployeeProjectRecord> actual = SERVICE.readRecordsFromCsv(testCsv);

        Assertions.assertEquals(expected, actual);
    }

    private void writeLines(String[] lines) throws IOException {
        try (PrintWriter writer = new PrintWriter(testCsv)) {
            for (String line : lines) {
                writer.println(line);
            }
        }
    }

    @AfterEach
    public void deleteFile() throws IOException {
        Files.delete(testCsv.toPath());
    }
}
