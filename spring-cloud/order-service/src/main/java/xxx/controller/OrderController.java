package xxx.controller;

import xxx.bean.Order;
import xxx.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName : OrderController
 * @Author : yq
 * @Date: 2021-09-30
 * @Description :
 */
@RestController
@RequestMapping(value = "/order")
public class OrderController {


    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/{id}")
    public Order selectOrderById(@PathVariable("id") Integer id) {
        return orderService.selectOrderById(id);
    }

    @GetMapping(value = "/test1")
    public String test(@RequestParam(value = "parameter") String parameter){

        return "success!";
    }
}
