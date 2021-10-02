package xxx.service;

import xxx.bean.Product;
import org.springframework.stereotype.Service;

/**
 * @ClassName : ProductService
 * @Author : yq
 * @Date: 2021-09-30
 * @Description :
 */
@Service
public class ProductService {

    public Product findById(Integer id) {
        return new Product(id, "冰箱", 1, 26666D);
    }

}
