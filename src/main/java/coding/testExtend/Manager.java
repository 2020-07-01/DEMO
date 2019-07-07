package coding.testExtend;


public class Manager extends Employee {

    //奖励
    private double bonus;


    public Manager(String n, double s, int year, int month, int day,double bonus) {
        //必须显示的调用父类的构造器方法
        super(n, s, year, month, day);
        this.bonus = bonus;

    }

    public double getBonus() {

        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    //重写getSalary方法：基本工资+奖励
    //如果父类中的getSalary方法声明为final则不能被覆盖
    public double getSalary(){
        double baseSalary = super.getSalary();//调用父类的方法
        return baseSalary+bonus;
    }


    public static void main(String[] args) {
        //创建父类对象
        Employee e = new Employee("yuqiang",5000,2019,7,1);
        System.out.println(e.getSalary());

        //创建子类对象
        //一个对象变量可以指示多种实际类型变量的现象称为多态
        Manager e1 = new Manager("yuqiang",5000,2019,7,1,100);
        System.out.println(e1.getSalary());









    }

}
