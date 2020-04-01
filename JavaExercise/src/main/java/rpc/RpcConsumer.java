package rpc;

/**
 * @author :qiang
 * @date :2019/11/8 下午4:08
 * @description :
 * @other :
 */
public class RpcConsumer {
    public static void main(String[] args) {
        CalculateService service = RpcFramework.call(CalculateService.class, "127.0.0.1", 8888);
        People people = new People(1, 1);
        String hello = service.Calculate(people);
        System.out.println(hello);
    }

}
