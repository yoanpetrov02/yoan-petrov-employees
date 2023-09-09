package com.yoanpetrov.yoanpetrovemployees.services;

import com.yoanpetrov.yoanpetrovemployees.model.EmployeeProjectRecord;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CsvService {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final String CSV_SPLIT_BY = ",";

    public List<EmployeeProjectRecord> readRecordsFromCsv(File csv) {
        try (BufferedReader reader = new BufferedReader(new FileReader(csv))) {
            return readLines(reader);
        } catch (IOException e) {
            System.err.println("IO EXCEPTION");
            // todo add logging
            return Collections.emptyList();
        } catch (ParseException e) {
            System.err.println("PARSE EXCEPTION");
            // todo add logging
            return Collections.emptyList();
        }
    }

    private List<EmployeeProjectRecord> readLines(BufferedReader reader)
            throws IOException, ParseException {
        List<EmployeeProjectRecord> records = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null) {
            String[] data = line.split(CSV_SPLIT_BY);
            EmployeeProjectRecord record = parseData(data);
            records.add(record);
        }
        return records;
    }

    private EmployeeProjectRecord parseData(String[] data) throws ParseException {
        System.out.println(data.length);
        EmployeeProjectRecord record = new EmployeeProjectRecord();

        record.setEmployeeId(Integer.parseInt(data[0].trim()));
        record.setProjectId(Integer.parseInt(data[1].trim()));
        record.setDateFrom(DATE_FORMAT.parse(data[2]));
        if ("NULL".equals(data[3].trim())) {
            record.setDateTo(new Date());
        } else {
            record.setDateTo(DATE_FORMAT.parse(data[3]));
        }
        return record;
    }

    public static void main(String[] args) throws ParseException {
        System.out.println(new SimpleDateFormat("dd-MM-yyyy").parse(" 32-10-2002"));
    }
}
