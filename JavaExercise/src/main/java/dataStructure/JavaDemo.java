package dataStructure;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Vector;
import java.util.WeakHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @ClassName : JavaDemo
 * @Author : yq
 * @Date: 2021-01-01
 * @Description :
 */
public class JavaDemo {

    public static void main(String[] args) {

        WeakHashMap weakHashMap = new WeakHashMap();
        weakHashMap.put("A","A");

        weakHashMap.get("A");


        HashMap hashMap = new HashMap();
        hashMap.put("","");

        System.gc();


    }
}
