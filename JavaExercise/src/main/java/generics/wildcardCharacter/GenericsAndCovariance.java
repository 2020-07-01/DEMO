package generics.wildcardCharacter;


import java.util.ArrayList;
import java.util.List;

/**
 * @author :qiang
 * @date :2019/10/11 下午3:04
 * @description :
 * @other :
 */
public class GenericsAndCovariance {

    /**
     * 下面这行代码会出现错误
     * Apple为Fruit类型的子类
     */
    //List<Fruit> fruitList = new ArrayList<Apple>();

    /**
     * ? extends Fruit:具有任何从Fruit继承的类型的列表
     * 通配符引用的是明确的类型
     */
    List<? extends Fruit> flsit = new ArrayList<Apple>();

}
