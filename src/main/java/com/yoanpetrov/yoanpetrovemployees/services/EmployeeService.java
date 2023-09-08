package com.yoanpetrov.yoanpetrovemployees.services;

import com.yoanpetrov.yoanpetrovemployees.model.EmployeeProjectRecord;
import com.yoanpetrov.yoanpetrovemployees.model.EmployeesStatistic;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

@Service
public class EmployeeService {

    public EmployeesStatistic getEmployeesStatistic(File employeesCsv) {
        ArrayList<EmployeeProjectRecord> employeeRecords = new ArrayList<>();
        // parse the csv file and populate the employeeRecords list
        EmployeesStatistic statistic = new EmployeesStatistic(employeeRecords);
        return null;
    }
}
