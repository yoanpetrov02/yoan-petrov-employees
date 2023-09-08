package com.yoanpetrov.yoanpetrovemployees.model;

public class EmployeePair implements Comparable<EmployeePair> {

    private final int employeeIdA;
    private final int employeeIdB;
    private final int projectId;
    private long daysWorkedTogether;

    public EmployeePair(int employeeIdA, int employeeIdB, int projectId, long daysWorkedTogether) {
        this(employeeIdA, employeeIdB, projectId);
        this.daysWorkedTogether = daysWorkedTogether;
    }

    public EmployeePair(int employeeIdA, int employeeIdB, int projectId) {
        this.employeeIdA = employeeIdA;
        this.employeeIdB = employeeIdB;
        this.projectId = projectId;
        this.daysWorkedTogether = 0;
    }

    public int getEmployeeIdA() {
        return employeeIdA;
    }

    public int getEmployeeIdB() {
        return employeeIdB;
    }

    public int getProjectId() {
        return projectId;
    }

    public long getDaysWorkedTogether() {
        return daysWorkedTogether;
    }

    public void setDaysWorkedTogether(long daysWorkedTogether) {
        this.daysWorkedTogether = daysWorkedTogether;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeePair that = (EmployeePair) o;
        return (employeeIdA == that.employeeIdA || employeeIdA == that.employeeIdB)
                && (employeeIdB == that.employeeIdB || employeeIdB == that.employeeIdA)
                && projectId == that.projectId;
    }

    @Override
    public int compareTo(EmployeePair o) {
        return Long.compare(o.daysWorkedTogether, this.daysWorkedTogether);
    }

    @Override
    public String toString() {
        return "EmployeePair{" +
                "employeeIdA=" + employeeIdA +
                ", employeeIdB=" + employeeIdB +
                ", projectId=" + projectId +
                ", daysWorkedTogether=" + daysWorkedTogether +
                '}';
    }
}
