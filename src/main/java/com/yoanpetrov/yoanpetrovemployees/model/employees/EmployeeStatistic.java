package com.yoanpetrov.yoanpetrovemployees.model.employees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Responsible for creating a statistic for which
 * employee pairs have worked the most on the same projects at the same time.
 */
public class EmployeeStatistic {

    private final List<EmployeeProjectRecord> records;
    private List<EmployeePair> pairs;

    /**
     * Creates a new statistic.
     *
     * @param records the list of employee project records to create a statistic for.
     */
    public EmployeeStatistic(List<EmployeeProjectRecord> records) {
        this.records = records;
    }

    public List<EmployeePair> getAllPairs() {
        if (pairs == null) {
            calculateBestPairs();
        }
        return pairs;
    }

    /**
     * Calculates which employee pairs have worked on the same project
     * together for the most time.
     */
    private void calculateBestPairs() {
        pairs = new ArrayList<>();
        for (int i = 0; i < records.size(); i++) {
            for (int j = 0; j < records.size(); j++) {
                if (i == j) {
                    continue;
                }
                if (records.get(i).getEmployeeId() == records.get(j).getEmployeeId()) {
                    continue;
                }
                if (records.get(i).getProjectId() != records.get(j).getProjectId()) {
                    continue;
                }
                EmployeePair pair = new EmployeePair(
                        records.get(i).getEmployeeId(),
                        records.get(j).getEmployeeId(),
                        records.get(i).getProjectId()
                );
                if (pairs.contains(pair)) {
                    continue;
                }
                calculateTimeWorkedTogether(pair, records.get(i), records.get(j));
                pairs.add(pair);
            }
        }
        Collections.sort(pairs);
    }

    /**
     * Calculates the amount of time in days that the two employees have spent working together on the same project.
     *
     * @param pair the pair of employees.
     * @param a the record of the first employee in the pair.
     * @param b the record of the second employee in the pair.
     */
    private void calculateTimeWorkedTogether(
            EmployeePair pair,
            EmployeeProjectRecord a,
            EmployeeProjectRecord b
    ) {
        if (!doPeriodsOverlap(a, b)) {
            return;
        }
        pair.setDaysWorkedTogether(getOverlappingPeriodDays(a, b));
    }

    /**
     * Checks whether the periods of time when the employees worked on the project overlap.
     *
     * @param a the record of the first employee.
     * @param b the record of the second employee.
     * @return true if the periods overlap, false otherwise.
     */
    private boolean doPeriodsOverlap(EmployeeProjectRecord a, EmployeeProjectRecord b) {
        Date startA = a.getDateFrom();
        Date endA = a.getDateTo();
        Date startB = b.getDateFrom();
        Date endB = b.getDateTo();

        return !(startA.after(endB)) && !(endA.before(startB));
    }

    /**
     * Calculates the length of the overlapping period
     * when the two employees worked together on the same project in days.
     *
     * @param a the record of the first employee.
     * @param b the record of the second employee.
     * @return the overlapping period in days.
     */
    private long getOverlappingPeriodDays(EmployeeProjectRecord a, EmployeeProjectRecord b) {
        return TimeUnit.DAYS.convert(
                getOverlappingPeriodMillis(a, b),
                TimeUnit.MILLISECONDS);
    }

    /**
     * Calculates the length of the overlapping period in milliseconds.
     *
     * @param a the record of the first employee.
     * @param b the record of the second employee.
     * @return the overlapping period in milliseconds.
     */
    private long getOverlappingPeriodMillis(EmployeeProjectRecord a, EmployeeProjectRecord b) {
        if (a.getDateFrom().after(b.getDateFrom())) {
            EmployeeProjectRecord temp = b;
            b = a;
            a = temp;
        }
        Date startA = a.getDateFrom();
        Date endA = a.getDateTo();
        Date startB = b.getDateFrom();
        Date endB = b.getDateTo();

        long diffA = Math.max(startA.getTime(), startB.getTime());
        long diffB = Math.min(endA.getTime(), endB.getTime());
        return Math.abs(diffB - diffA);
    }
}
