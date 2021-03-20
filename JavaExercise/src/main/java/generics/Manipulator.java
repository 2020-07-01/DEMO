package generics;



/**
 * @author :qiang
 * @date :2019/10/10 下午8:55
 * @description :泛型类型的擦除
 * @other :
 */

/**
 * 此时泛型T需要进行边界的限制
 * T 必须具有类型Hasf或者从Hasf导出的类型
 *
 * 泛型类型参数将擦除他的第一边界
 * 编译器实际上会将类型参数替换为他的擦除——T擦出到Hasf
 * 而普通的变量在未指定边界的情况下将被擦除为Object
 *
 * 即使擦除在类内部或者方法中移除了有关实际类型的信息，编译器仍旧可以确保在方法或者类中使用的类型的一致性
 * @param <T>
 */
public class Manipulator<T extends Hasf> {


    private T obj;

    public Manipulator(T x) {
        obj = x;
    }

    public void manipulator() {
        obj.f();
    }

}
