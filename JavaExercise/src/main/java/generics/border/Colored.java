package generics.border;

import java.awt.*;

/**
 * @author :qiang
 * @date :2019/10/11 下午2:40
 * @description :
 * @other :
 */
public class Colored<T extends HasColor> {

    T item;

    Colored(T item) {
        this.item = item;
    }

    T getItem() {
        return item;
    }

    Color color() {
        return item.getColor();
    }

}
