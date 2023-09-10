package com.yoanpetrov.yoanpetrovemployees.model.employees;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;

public class EmployeePair implements Comparable<EmployeePair> {

    private final IntegerProperty employeeIdA;
    private final IntegerProperty employeeIdB;
    private final IntegerProperty projectId;
    private LongProperty daysWorkedTogether;

    public EmployeePair(int employeeIdA, int employeeIdB, int projectId, long daysWorkedTogether) {
        this(employeeIdA, employeeIdB, projectId);
        this.daysWorkedTogether = new SimpleLongProperty(daysWorkedTogether);
    }

    public EmployeePair(int employeeIdA, int employeeIdB, int projectId) {
        this.employeeIdA = new SimpleIntegerProperty(employeeIdA);
        this.employeeIdB = new SimpleIntegerProperty(employeeIdB);
        this.projectId = new SimpleIntegerProperty(projectId);
        this.daysWorkedTogether = new SimpleLongProperty(0);
    }

    public int getEmployeeIdA() {
        return employeeIdA.get();
    }

    public int getEmployeeIdB() {
        return employeeIdB.get();
    }

    public int getProjectId() {
        return projectId.get();
    }

    public long getDaysWorkedTogether() {
        return daysWorkedTogether.get();
    }

    public void setDaysWorkedTogether(long daysWorkedTogether) {
        this.daysWorkedTogether.set(daysWorkedTogether);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeePair that = (EmployeePair) o;
        return (employeeIdA.get() == that.employeeIdA.get()
                || employeeIdA.get() == that.employeeIdB.get())
                && (employeeIdB.get() == that.employeeIdB.get()
                || employeeIdB.get() == that.employeeIdA.get())
                && projectId.get() == that.projectId.get();
    }

    @Override
    public int compareTo(EmployeePair o) {
        return Long.compare(
                o.daysWorkedTogether.get(),
                this.daysWorkedTogether.get());
    }

    @Override
    public String toString() {
        return "EmployeePair{" +
                "employeeIdA=" + employeeIdA.get() +
                ", employeeIdB=" + employeeIdB.get() +
                ", projectId=" + projectId.get() +
                ", daysWorkedTogether=" + daysWorkedTogether.get() +
                '}';
    }
}
