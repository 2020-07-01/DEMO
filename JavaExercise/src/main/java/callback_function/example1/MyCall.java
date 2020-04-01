package callback_function.example1;

/**
 * @author :qiang
 * @date :2019/11/10 下午4:15
 * @description :
 * @other :
 */
public class MyCall {

    private MyCallInterface myCallInterface;

    public void setCall(MyCallInterface myCallInterface) {
        this.myCallInterface = myCallInterface;
    }

    public void call() {
        this.myCallInterface.method();
    }
}

