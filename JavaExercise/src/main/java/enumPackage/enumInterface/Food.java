package enumPackage.enumInterface;

/**
 * @author :qiang
 * @date :2019/10/19 下午2:57
 * @description : 使用接口对枚举进行分类
 * @other :
 */
public interface Food {

    /**
     * 在接口的内部创建枚举类实现该接口
     * 达到对枚举元素进行分类组织的目的
     */
    //开胃菜
    enum Appetizer implements Food {
        SALAD, SOUP, SPRING_ROLLS,
    }

    //主要食材
    enum MainCourse implements Food {
        TIRAMISU, GELATO, BLACK_FOREST_CAKE,
        FRUIT, CREME_CARAMEL;
    }

    enum Dessert implements Food {
        TIRAMISU, GELATO, BLACK_FOREST_CAKE,
        FRUIT, CREME_CARAMEL;
    }

    //咖啡
    enum Coffee implements Food {
        BLACK_COFFEE, DECF_COFFEE, ESPERSSO, LATE, CAPPUCCINO, TEA, HERB_TEA,
    }

}
