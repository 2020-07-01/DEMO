package generics.border;

import java.awt.*;

/**
 * @author :qiang
 * @date :2019/10/11 下午2:42
 * @description :
 * @other :
 */
public class ColoredDimension<T extends Dimension & HasColor> {
    /**
     * T 类型继承了Dimension和HasColor类的所有方法和属性
     */
    T item;

    ColoredDimension(T item) {
        this.item = item;
    }

    T getItem() {
        return item;
    }

    Color color() {
        return item.getColor();
    }

    int getX() {
        return item.x;
    }

    int getY() {
        return item.y;
    }

    int getZ() {
        return item.z;
    }

}
