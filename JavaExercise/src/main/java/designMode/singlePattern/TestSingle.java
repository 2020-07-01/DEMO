package designMode.singlePattern;

/**
 * @author :qiang
 * @date :2019/10/20 下午4:36
 * @description :
 * @other :
 */
public class TestSingle {

    public static void main(String[] args) {

        User user = User.getInstance( );
        User user1 = User.getInstance( );

        System.out.println(user == user1);


        Student student = Student.getInstance();
        Student student1 = Student.getInstance();

        System.out.println(student == student1);
    }
}
