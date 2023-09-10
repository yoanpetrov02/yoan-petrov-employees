package com.yoanpetrov.yoanpetrovemployees.services;

import com.yoanpetrov.yoanpetrovemployees.model.employees.EmployeeProjectRecord;
import com.yoanpetrov.yoanpetrovemployees.model.employees.EmployeeStatistic;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    public EmployeeStatistic getEmployeesStatistic(
            List<EmployeeProjectRecord> employeeRecords) {
        return new EmployeeStatistic(employeeRecords);
    }
}
