package generics;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :qiang
 * @date :2019/10/10 下午7:48
 * @description :创建各种类型的容器对象的类
 * @other :
 */
public class New {

    public static <T> List<T> list() {
        return new ArrayList<T>();
    }


}
