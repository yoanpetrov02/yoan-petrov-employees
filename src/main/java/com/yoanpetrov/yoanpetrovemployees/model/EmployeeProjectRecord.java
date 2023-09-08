package com.yoanpetrov.yoanpetrovemployees.model;

import java.util.Date;

public class EmployeeProjectRecord {

    private final int employeeId;
    private final int projectId;
    private final Date dateFrom;
    private Date dateTo; // can be null


    public EmployeeProjectRecord(int employeeId, int projectId, Date dateFrom, Date dateTo) {
        this.employeeId = employeeId;
        this.projectId = projectId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getProjectId() {
        return projectId;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }
}
