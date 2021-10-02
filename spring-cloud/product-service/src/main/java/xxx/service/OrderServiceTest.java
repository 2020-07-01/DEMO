package xxx.service;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName : OrderServiceTest
 * @Author : yq
 * @Date: 2021-10-02
 * @Description :
 */
//@FeignClient(name = "order-service",path = "/order-service/order")
public interface OrderServiceTest {

    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    String tst1(@RequestParam("parameter") String parameter);

}
