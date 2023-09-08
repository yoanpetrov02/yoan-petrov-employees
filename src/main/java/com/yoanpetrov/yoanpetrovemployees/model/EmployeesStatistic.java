package com.yoanpetrov.yoanpetrovemployees.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeesStatistic {

    private final List<EmployeeProjectRecord> records;
    private List<EmployeePair> pairs;
    private EmployeePair bestPair;

    public EmployeesStatistic(List<EmployeeProjectRecord> records) {
        this.records = records;
    }

    public EmployeePair getBestPair() {
        if (bestPair == null) {
            calculateBestPair();
        }
        return bestPair;
    }

    private void calculateBestPair() {
        pairs = new ArrayList<>();
        for (int i = 0; i < records.size(); i++) {
            for (int j = 0; j < records.size(); j++) {
                if (i == j) {
                    continue;
                }
                if (records.get(i).getEmployeeId() == records.get(j).getProjectId()) {
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
    }

    private void calculateTimeWorkedTogether(
            EmployeePair pair,
            EmployeeProjectRecord a,
            EmployeeProjectRecord b
    ) {
        // calculate the time worked together on the project and set the pair's field to it
        if (!doPeriodsOverlap(a, b)) {
            return;
        }
        pair.setDaysWorkedTogether(getOverlappingDays(a, b));
    }

    private boolean doPeriodsOverlap(EmployeeProjectRecord a, EmployeeProjectRecord b) {
        Date startA = a.getDateFrom();
        Date endA = a.getDateTo();
        Date startB = b.getDateFrom();
        Date endB = b.getDateTo();

        return !(startA.after(endB)) && !(endA.before(startB));
    }

    private int getOverlappingDays(EmployeeProjectRecord a, EmployeeProjectRecord b) {
        // solve overlapping days logic
        return -1;
    }
}
