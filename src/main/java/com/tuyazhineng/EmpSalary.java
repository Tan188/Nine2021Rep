package com.tuyazhineng;

public class EmpSalary {
    private int empId;
    public String name;
    private int month;
    private float salary;
    private String date;

    public EmpSalary(String name,int month,float salary) {
        this.name=name;
        this.month=month;
        this.salary=salary;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
