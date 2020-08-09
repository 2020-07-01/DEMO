package dataStructure.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author yq
 * @date 2020/8/9 22:12
 */
public class MapDemo {


    /**
     * lambda
     * @param hashMap
     */
    public void traverseMap1(HashMap hashMap){
        hashMap.keySet().forEach(p->{
            System.out.println(hashMap.get(p));
        });
    }

    /**
     * 增强for循环 -> iterator -> 快速失败机制
     * @param hashMap
     */
    public void traverseMap2(HashMap hashMap){
        for (Object key:hashMap.keySet()) {
            System.out.println(hashMap.get(key));
        }
    }

    /**
     * iterator
     * @param hashMap
     */
    public  void traverseMap3(HashMap hashMap){
        Iterator iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()){
            System.out.println(hashMap.get(iterator.next()));
        }
    }

    /**
     * entry
     * @param hashMap
     */
    public void traverseMap4(HashMap hashMap){

        Set<Map.Entry> entrySet  = hashMap.entrySet();
        entrySet.forEach(p->{
            System.out.println(p.getKey());
            System.out.println(p.getValue());
        });
    }


    /**
     * 遍历的过程实际调用的是hashMap底层数据结构 table
     * @param hashMap
     */
    public void traverseMap5(HashMap hashMap){
        Iterator iterator = hashMap.keySet().iterator();
        while (iterator.hasNext()){
            System.out.println(hashMap.get(iterator.next()));
        }
    }

}
