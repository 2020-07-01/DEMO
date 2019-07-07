package coding.testExtend;

/**
 * @author :qiang
 * @date :Created in 2019/7/7 下午3:28
 * @description :抽象类
 * @other :
 */
public abstract  class Person {

    private  String name;

    public Person(String name){
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //抽象方法
    public abstract  String getDescrition();
}
