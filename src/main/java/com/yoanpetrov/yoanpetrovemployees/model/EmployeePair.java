package com.yoanpetrov.yoanpetrovemployees.model;

public class EmployeePair {

    private final int employeeIdA;
    private final int employeeIdB;
    private final int projectId;
    private int daysWorkedTogether;

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

    public int getDaysWorkedTogether() {
        return daysWorkedTogether;
    }

    public void setDaysWorkedTogether(int daysWorkedTogether) {
        this.daysWorkedTogether = daysWorkedTogether;
    }
}
