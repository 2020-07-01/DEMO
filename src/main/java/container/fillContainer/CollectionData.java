package container.fillContainer;

import java.util.ArrayList;

/**
 * @author :qiang
 * @date :2019/10/15 下午8:59
 * @description :
 * @other :
 */
public class CollectionData<T> extends ArrayList<T> {


    //存储指定数量的T对象
    public CollectionData(Generator<T> gen, int quantity) {

        for (int i = 0; i < quantity; i++) {
            add(gen.next());//存储T的对象
        }
    }


    //返回CollectionData类型
    public static <T> CollectionData<T> list(Generator<T> gen, int quantity) {

        //返回存储指定数量的T对象的CollectionData对象
        return new CollectionData<T>(gen, quantity);
    }


}
