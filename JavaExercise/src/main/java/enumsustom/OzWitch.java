package enumsustom;

public enum OzWitch {


    /**
     * 枚举类中的实例总是在第一行开始进行声明
     * 在实例实例之前不能定义方法和属性
     */
    WEST("Miss Gulch, aka the Wicked Witch of the West"),
    NORTH("Glinda, the Good Witch of the North"),
    EAST("Wicked Witch of the East, wearer of the Ruby " +
            "Slippers, crushed by Dorothy's house"),
    SOUTH("Good by inference, but missing"),

    NUMBER(1);//实例序例的最后要添加封号

    private String description;
    private Integer number;


    /**
     * 枚举类的构造方法为private类型的，并且枚举实例是单例模式的
     * 这样保证每一个枚举元素的唯一实例不会被外界new获取
     * 一般情况下，每一个枚举类只定义一种类型的数据 作为枚举实例的描述信息
     *
     * @param description
     */
    private OzWitch(String description) {
        this.description = description;
    }


    private OzWitch(Integer number) {
        this.number = number;
    }


    public Integer getNumber() {
        return number;
    }


    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "重写toString方法：" + this.name() + getDescription();
    }

    public static void main(String[] args) {
        for (OzWitch item : OzWitch.values()) {
            System.out.println(item);//重写了toString方法
            System.out.println(item.getDescription());//最后一个返回为null
        }

    }
}
