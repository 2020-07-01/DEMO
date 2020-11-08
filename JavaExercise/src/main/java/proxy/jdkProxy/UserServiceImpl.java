package proxy.jdkProxy;

/**
 * @ClassName : UserServiceImpl
 * @Author : yq
 * @Date: 2020-11-03
 * @Description :
 */
public class UserServiceImpl implements UserService{

    @Override
    public void addUser() {

        System.out.println("添加用户......");

    }

    @Override
    public void selectUser() {
        System.out.println("检索用户......");
    }

    @Override
    public void deleteUser() {
        System.out.println("删除用户......");
    }
}
