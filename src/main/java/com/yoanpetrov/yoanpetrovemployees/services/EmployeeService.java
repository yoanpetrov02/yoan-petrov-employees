package com.yoanpetrov.yoanpetrovemployees.services;

import com.yoanpetrov.yoanpetrovemployees.model.EmployeeProjectRecord;
import com.yoanpetrov.yoanpetrovemployees.model.EmployeesStatistic;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    public EmployeesStatistic getEmployeesStatistic(List<EmployeeProjectRecord> employeeRecords) {
        return new EmployeesStatistic(employeeRecords);
    }
}
