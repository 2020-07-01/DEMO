package xxx.controller;

import xxx.bean.Product;
import xxx.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : ProductController
 * @Author : yq
 * @Date: 2021-09-30
 * @Description :
 */
@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productSrvice;

    /*@Autowired
    private OrderServiceTest orderServiceTest;*/

    @GetMapping(value = "/{id}")
    public Product selectById(@PathVariable("id") Integer id) {
        return productSrvice.findById(id);
    }

   /* @GetMapping(value = "/test1")
    public String test11(){
        return orderServiceTest.tst1("demo");
    }*/
}

