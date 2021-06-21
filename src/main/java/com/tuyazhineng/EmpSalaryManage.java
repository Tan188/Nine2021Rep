package com.tuyazhineng;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EmpSalaryManage {
    private ArrayList salaryList;
    static int id = 0;

    public EmpSalaryManage() {
        salaryList = new ArrayList();
    }

    //public void addSalary(int empId, String name, int month, float salary){}
    public void addSalary(EmpSalary empSalary){
        id ++;
        empSalary.setEmpId(id);

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        String date = sdf.format(new Date()).toString();
        empSalary.setDate(date);

        salaryList.add(empSalary);
        System.out.println(empSalary.getEmpId()+"_"+empSalary.getName()+"_"+ empSalary.getSalary()+"_"+ empSalary.getMonth()+"_"+empSalary.getDate());

    }

    public void deleteSalary(int id){
        for(int i=0;i<=salaryList.size();i++){
            EmpSalary es = (EmpSalary) salaryList.get(i);
            if (es.getEmpId()==id){
                salaryList.remove(i);
                System.out.println("删除Id为"+id+"的工资成功！");
            }
        }
    }

    public void queryLatestSalary(int n){
        for(int i = salaryList.size()-1;i>=salaryList.size()-n;i--){
            EmpSalary empSalary = (EmpSalary) salaryList.get(i);
            System.out.println(empSalary.getEmpId()+"_"+empSalary.getName()+"_"+ empSalary.getSalary()+"_"+ empSalary.getMonth()+"_"+empSalary.getDate());

        }
    }

}
