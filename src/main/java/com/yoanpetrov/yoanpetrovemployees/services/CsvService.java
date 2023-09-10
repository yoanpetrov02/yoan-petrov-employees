package com.yoanpetrov.yoanpetrovemployees.services;

import com.yoanpetrov.yoanpetrovemployees.model.employees.EmployeeProjectRecord;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.ParseException;
import java.util.*;

/**
 * Csv file parsing service. Whenever a csv file is passed, the service parses it and maps the records
 * to {@code EmployeeProjectRecord} objects.
 */
@Service
public class CsvService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvService.class);

    private static final String[] DATE_FORMATS = new String[] {
            "yyyy-MM-dd",
            "dd-MM-yyyy",
            "MM-dd-yyyy"
    };

    private static final String CSV_SPLIT_BY = ",";

    /**
     * Reads {@code EmployeeProjectRecord}s from a csv file.
     *
     * @param csv the csv file to parse.
     * @return a list of the parsed records, or an empty list if an error occurs.
     */
    public List<EmployeeProjectRecord> readRecordsFromCsv(File csv) {
        try (BufferedReader reader = new BufferedReader(new FileReader(csv))) {
            return parseLines(reader);
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    /**
     * Reads lines from a csv file and parses them.
     *
     * @param reader the reader to read the lines from.
     * @return a list of the parsed records, or an empty list if an error occurs.
     */
    private List<EmployeeProjectRecord> parseLines(BufferedReader reader) {
        try {
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
        } catch (IOException e) {
            LOGGER.error("An i/o error occurred while reading csv file.", e);
            return Collections.emptyList();
        }
    }

    /**
     * Parses a record and maps it to a {@code EmployeeProjectRecord} object.
     *
     * @param data the raw data read from the file.
     * @return the parsed data as an {@code EmployeeProjectRecord} object, or null if an error occurs.
     */
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
