package serializable;

import java.io.Serializable;

/**
 * @ClassName : Person
 * @Author : yq
 * @Date: 2020-09-10
 * @Description :
 */

public class Person implements Serializable {

    private String name;
    private String zone;
    private Integer age;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", zone='" + zone + '\'' +
                ", age=" + age +
                '}';
    }
}
