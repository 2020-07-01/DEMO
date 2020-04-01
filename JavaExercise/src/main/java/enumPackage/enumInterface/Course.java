package enumPackage.enumInterface;

/**
 * @author :qiang
 * @date :2019/10/19 下午3:06
 * @description :
 * @other :
 */
public enum Course {

    APPETIZER(Food.Appetizer.class),
    MAINCOURSE(Food.MainCourse.class),
    DESSERT(Food.Dessert.class),
    COFFEE(Food.Coffee.class);

    private Food[] values;

    private Course(Class<? extends Food> kind) {
        values = kind.getEnumConstants();
    }




}
