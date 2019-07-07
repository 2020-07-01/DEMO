package coding.testInterface.test1;


import java.time.LocalDate;

public class Employee implements Comparable {


    private String name;//姓名
    private double salary;//薪资
    private LocalDate hireDay;//日期


    //有参构造方法
    public Employee(String n, double s, int year, int month, int day) {
        this.name = n;
        this.salary = s;
        this.hireDay = LocalDate.of(year, month, day);
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

    public LocalDate getHireDay() {
        return hireDay;
    }

    public void setHireDay(LocalDate hireDay) {
        this.hireDay = hireDay;
    }


    @Override
    public int compareTo(Object o) {
        Employee other = (Employee) o;
        return Double.compare(salary, other.salary);
    }

}
