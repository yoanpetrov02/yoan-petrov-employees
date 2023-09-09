package com.yoanpetrov.yoanpetrovemployees.model;

import java.util.Date;
import java.util.Objects;

public class EmployeeProjectRecord {

    private int employeeId;
    private int projectId;
    private Date dateFrom;
    private Date dateTo;

    public EmployeeProjectRecord() {}

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

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeProjectRecord that = (EmployeeProjectRecord) o;
        return employeeId == that.employeeId
                && projectId == that.projectId
                && Objects.equals(dateFrom, that.dateFrom)
                && Objects.equals(dateTo, that.dateTo);
    }
}
