package com.yoanpetrov.yoanpetrovemployees.services;

import com.yoanpetrov.yoanpetrovemployees.model.employees.EmployeeProjectRecord;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.ParseException;
import java.util.*;

@Service
public class CsvService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvService.class);

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
            LOGGER.error("An i/o error occurred while reading csv file.", e);
        } catch (ParseException | NumberFormatException e) {
            LOGGER.error("An error occurred while parsing csv file.", e);
        }
        return Collections.emptyList();
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
        EmployeeProjectRecord record = new EmployeeProjectRecord();
        try {
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
