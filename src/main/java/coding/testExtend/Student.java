package coding.testExtend;

/**
 * @author :qiang
 * @date :Created in 2019/7/7 下午3:32
 * @description :学生类
 * @other :
 */
public class Student extends Person {

    private String major;

    public Student(String name,String major){
        super(name);
        this.major = major;


    }

    @Override
    public String getDescrition() {
        return "a student major in "+major;
    }



}
