package com.tuyazhineng;

import java.util.Scanner;

public class EmpSalaryExe {
    public static void main(String[] args) {
        EmpSalaryManage esm = new EmpSalaryManage();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            switch (scanner.next()) {
                case "input":
                    String name = scanner.next();
                    int month = scanner.nextInt();
                    float salary = scanner.nextFloat();
                    EmpSalary es = new EmpSalary(name, month, salary);
                    esm.addSalary(es);
                    break;

                case "list":
                    int count = scanner.nextInt();
                    if (count <= 0) {
                        System.out.println("请输入大于0的数字");
                        break;
                    }
                    try {
                        esm.queryLatestSalary(count);
                    } catch (Exception e) {
                        System.out.println("当前字数大于总人数，已返回全部记录");
                        break;
                    }

                case "del":
                    int id = scanner.nextInt();
                    esm.deleteSalary(id);
                    System.out.println("删除成功");
                    break;


            }
        }

    }
}
