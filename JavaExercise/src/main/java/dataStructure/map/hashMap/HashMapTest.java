package dataStructure.map.hashMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author yq
 * @date 2020/7/21 20:02
 * hashMap的遍历方式
 */
public class HashMapTest {


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

    /**
     * entrySet  entryIterator
     */
    public void entrySetTest(){
        HashMap hashMap = new HashMap();
        hashMap.put("1","1");
        hashMap.put("2","2");

        /**
         * entrySet
         * EntryIterator  HashIterator
         */
        Set<Map.Entry> entries = hashMap.entrySet();

        Iterator iterator = entries.iterator();
        iterator.hasNext();
        /**
         * HashIterator.nextNode() 实际调用hashMap的table
         */
        Object object = iterator.next();
    }

    /**
     * KeyIterator  hashIterator
     */
    public void ketSetTest(){
        HashMap hashMap = new HashMap();
        Iterator iterator = hashMap.keySet().iterator();
        iterator.hasNext();

    }
    public static void main(String[] args) {
        HashMap<String,String> hashMap = new HashMap();
        hashMap.put("1","1");
        hashMap.put("2","2");
        hashMap.put("3","3");
        hashMap.put("4","4");

        Iterator<Map.Entry<String,String>> entries = hashMap.entrySet().iterator();
        while (entries.hasNext()){
            System.out.println(entries.next().getKey());
            System.out.println(entries.next().getValue());
        }


    }
}




















