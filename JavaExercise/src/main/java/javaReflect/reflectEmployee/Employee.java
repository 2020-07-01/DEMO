package javaReflect.reflectEmployee;

import java.time.LocalDate;

/**
 * @author :qiang
 * @date :2019/10/22 下午6:53
 * @description :
 * @other :
 */
public class Employee {

    private String name;
    private double salary;
    private LocalDate hreDay;

    public Employee(String name, double salary, int year,int mouth,int day) {
        this.name = name;
        hreDay = LocalDate.of(year,mouth,day);
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getHreDay() {
        return hreDay;
    }

    public void setHreDay(LocalDate hreDay) {
        this.hreDay = hreDay;
    }
}
