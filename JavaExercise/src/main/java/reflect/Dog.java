package reflect;

/**
 * @author yq
 * @date 2020/8/22 13:22
 */
public class Dog {

    private String type;
    private String name;

    Dog() {
        type = "金毛";
        name = "大黄";
    }


    Dog(String type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
