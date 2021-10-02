package xxx.service;

import xxx.bean.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName : ProductService
 * @Author : yq
 * @Date: 2021-09-30
 * @Description :
 */
@Component
@FeignClient(name = "product-service")
public interface ProductService {

    @GetMapping(value = "/product/{id}")
    Product selectProductById(@PathVariable("id") Integer id);
}
