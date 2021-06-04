package com.example.demo.entity;

import java.sql.Timestamp;

public class Report {
    private Timestamp printTime;
    private long countChecks;
    private long countCancelChecks;
    private double totalA;
    private double totalB;
    private double totalC;
    private double sumTotal;
    private double ndsTotalA;
    private double ndsTotalB;
    private double ndsTotalC;
    private double sumNdsTotal;
    @Override
    public String toString() {
        return "Report{" +
                "printTime=" + printTime +
                ", countChecks=" + countChecks +
                ", countCancelChecks=" + countCancelChecks +
                ", totalA=" + totalA +
                ", totalB=" + totalB +
                ", totalC=" + totalC +
                ", sumTotal=" + sumTotal +
                ", ndsTotalA=" + ndsTotalA +
                ", ndsTotalB=" + ndsTotalB +
                ", ndsTotalC=" + ndsTotalC +
                ", sumNdsTotal=" + sumNdsTotal +
                '}';
    }

    public Report(Timestamp printTime,
                  long countChecks,
                  long countCancelChecks,
                  double totalA,
                  double totalB,
                  double totalC,
                  double sumTotal,
                  double ndsTotalA,
                  double ndsTotalB,
                  double ndsTotalC,
                  double sumNdsTotal) {
        this.printTime = printTime;
        this.countChecks = countChecks;
        this.countCancelChecks = countCancelChecks;
        this.totalA = totalA;
        this.totalB = totalB;
        this.totalC = totalC;
        this.sumTotal = sumTotal;
        this.ndsTotalA = ndsTotalA;
        this.ndsTotalB = ndsTotalB;
        this.ndsTotalC = ndsTotalC;
        this.sumNdsTotal = sumNdsTotal;
    }

    public Timestamp getPrintTime() {
        return printTime;
    }

    public void setPrintTime(Timestamp printTime) {
        this.printTime = printTime;
    }

    public long getCountChecks() {
        return countChecks;
    }

    public void setCountChecks(long countChecks) {
        this.countChecks = countChecks;
    }

    public long getCountCancelChecks() {
        return countCancelChecks;
    }

    public void setCountCancelChecks(long countCancelChecks) {
        this.countCancelChecks = countCancelChecks;
    }

    public double getTotalA() {
        return totalA;
    }

    public void setTotalA(double totalA) {
        this.totalA = totalA;
    }

    public double getTotalB() {
        return totalB;
    }

    public void setTotalB(double totalB) {
        this.totalB = totalB;
    }

    public double getTotalC() {
        return totalC;
    }

    public void setTotalC(double totalC) {
        this.totalC = totalC;
    }

    public double getNdsTotalA() {
        return ndsTotalA;
    }

    public void setNdsTotalA(double ndsTotalA) {
        this.ndsTotalA = ndsTotalA;
    }

    public double getNdsTotalB() {
        return ndsTotalB;
    }

    public void setNdsTotalB(double ndsTotalB) {
        this.ndsTotalB = ndsTotalB;
    }

    public double getNdsTotalC() {
        return ndsTotalC;
    }

    public void setNdsTotalC(double ndsTotalC) {
        this.ndsTotalC = ndsTotalC;
    }

    public double getSumNdsTotal() {
        return sumNdsTotal;
    }

    public void setSumNdsTotal(double sumNdsTotal) {
        this.sumNdsTotal = sumNdsTotal;
    }

    public double getSumTotal() {
        return sumTotal;
    }

    public void setSumTotal(double sumTotal) {
        this.sumTotal = sumTotal;
    }
}
