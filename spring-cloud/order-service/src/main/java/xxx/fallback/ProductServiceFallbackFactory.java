package xxx.fallback;

import xxx.bean.Product;
import xxx.service.ProductService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName : ProductServiceFallbackFactory
 * @Author : yq
 * @Date: 2021-09-30
 * @Description :
 */
@Slf4j
public class ProductServiceFallbackFactory implements FallbackFactory<ProductService> {

    @Override
    public ProductService create(Throwable throwable) {
        return id -> {
            log.error("product-service 服务的 selectProductById 方法出现异常......异常信息如下：", throwable);
            return new Product(id, "托底数据", 2, 66666D);
        };
    }
}
