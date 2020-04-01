package rpc;

import java.io.Serializable;

/**
 * @author :qiang
 * @date :2019/11/8 下午4:11
 * @description :
 * @other :
 */
public class People implements Serializable {

    private Integer a;
    private Integer b;

    People(Integer a, Integer b) {
        this.a = a;
        this.b = b;
    }

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    public Integer getB() {
        return b;
    }

    public void setB(Integer b) {
        this.b = b;
    }
}
