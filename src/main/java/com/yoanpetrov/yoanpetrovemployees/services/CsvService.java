package com.yoanpetrov.yoanpetrovemployees.services;

import com.yoanpetrov.yoanpetrovemployees.model.EmployeeProjectRecord;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CsvService {

    private static final String[] DATE_FORMATS = new String[] {
            "yyyy-MM-dd",
            "dd-MM-yyyy",
            "MM-dd-yyyy"
    };
    private static final String CSV_SPLIT_BY = ",";

    public List<EmployeeProjectRecord> readRecordsFromCsv(File csv) {
        try (BufferedReader reader = new BufferedReader(new FileReader(csv))) {
            return readLines(reader);
        } catch (IOException e) {
            e.printStackTrace();
            // todo add logging
            return Collections.emptyList();
        } catch (ParseException e) {
            e.printStackTrace();
            // todo add logging
            return Collections.emptyList();
        } catch (NumberFormatException e) {
            e.printStackTrace();
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
            if (record == null) {
                return Collections.emptyList();
            }
            records.add(record);
        }
        return records;
    }

    private EmployeeProjectRecord parseData(String[] data) {
        try {
            EmployeeProjectRecord record = new EmployeeProjectRecord();

            record.setEmployeeId(Integer.parseInt(data[0].trim()));
            record.setProjectId(Integer.parseInt(data[1].trim()));
            record.setDateFrom(DateUtils.parseDateStrictly(data[2], DATE_FORMATS));
            if ("NULL".equals(data[3].trim())) {
                record.setDateTo(new Date());
            } else {
                record.setDateTo(DateUtils.parseDateStrictly(data[3], DATE_FORMATS));
            }
            return record;
        } catch (NumberFormatException | ParseException e) {
            return null;
        }
    }
}
