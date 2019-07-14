
public class TestClass {

    //类变量
    private String name;


    //此时方法参数的变量名与类中的变量名同名，则屏蔽类中的变量名
    public void setName(String name){

        //此时为方法参数的变量名赋值
        name = "setname";
    }

    //构造方法
    public TestClass(String name){
        name = name;
    }


    public void show(){
        System.out.println(name);
    }


    public static void main(String[] args) {
        TestClass t = new TestClass("123");

        t.show();
        System.out.println("测试ceshi");
        System.out.println("测试");
    }



}



