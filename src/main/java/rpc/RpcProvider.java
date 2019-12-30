package rpc;

/**
 * @author :qiang
 * @date :2019/11/8 下午4:09
 * @description :
 * @other :
 */
public class RpcProvider {

    public static void main(String[] args) throws Exception {

        CalculateServiceImpl service = new CalculateServiceImpl();

        RpcFramework.publish(service, 8888);

    }
}
