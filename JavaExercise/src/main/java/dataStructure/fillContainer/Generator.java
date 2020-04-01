package dataStructure.fillContainer;

/**
 * @author :qiang
 * @date :2019/10/15 下午8:58
 * @description : 生产者
 * @other :
 */
public interface Generator<T> {
    //返回类型T
    T next();
}
