package lambda;

/**
 * @author yq
 * @date 2020/8/20 17:44
 */
public class InnerClass {


    public void test(Inner inner){

        inner.innerTest2();

    }


    public void test3(Test1 test1){
        test1.get2();
    }

    static class Inner{

        public void innerTest1(){
            System.out.println("这是匿名类的innerTest1方法");
        }

        public void innerTest2(){
            System.out.println("这是匿名类的innerTest2方法");
        }
    }


    public static void main(String[] args) {


        InnerClass threadTest = new InnerClass();

        threadTest.test3(()->{

            System.out.println("重新接口的抽象方法");
        });

        threadTest.test(new Inner(){

        });

      /*  new Thread(() -> {

            for (int x = 0; x < 10; x++) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {

                }
                System.out.println(Thread.currentThread().getName() + "、x = " + x);
            }
        }, "线程对象").start();*/
    }

}



interface Test1{


    public void get2();

}


