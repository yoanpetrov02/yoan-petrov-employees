package com.yoanpetrov.yoanpetrovemployees.services;

import com.yoanpetrov.yoanpetrovemployees.model.employees.EmployeeProjectRecord;
import com.yoanpetrov.yoanpetrovemployees.model.employees.EmployeeStatistic;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Employee service, responsible for providing the user with {@code EmployeeStatistic} objects.
 */
@Service
public class EmployeeService {

    public EmployeeStatistic getEmployeeStatistic(
            List<EmployeeProjectRecord> employeeRecords) {
        return new EmployeeStatistic(employeeRecords);
    }
}
