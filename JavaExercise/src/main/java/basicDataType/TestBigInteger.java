package basicDataType;

import dataStructure.performanceTest.Test;

import java.math.BigInteger;

/**
 * @author :qiang
 * @date :2019/10/19 下午7:50
 * @description : 大整数
 * @other :
 */
public class TestBigInteger {

    public TestBigInteger() {
        System.out.println("123");
    }

    public static void main(String[] args) {

        //将普通类型转换为大整数
        BigInteger a = BigInteger.valueOf(100);
        BigInteger b = BigInteger.valueOf(2);
        BigInteger c = a.add(b);//加法计算

        System.out.println(c);
    }
}

