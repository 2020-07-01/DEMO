package dataStructure.map.hashcode;

import java.util.*;

/**
 * @author :qiang
 * @date :2019/10/16 下午1:15
 * @description :
 * @other :
 */
public class SlowMap<K, V> extends AbstractMap<K, V> {

    //key和value一一对应
    private List<K> keys = new ArrayList<K>();
    private List<V> values = new ArrayList<V>();

    //存储元素
    public V put(K key, V value) {
        V oldValue = get(key);
        if (!keys.contains(key)) {
            keys.add(key);
            values.add(value);
        } else {
            values.set(keys.indexOf(key), value);//此时会存在key为null的情况
        }

        return oldValue;
    }

    public V get(Object key) {
        if (!keys.contains(key)) {
            return null;
        }
        return values.get(keys.indexOf(key));
    }


    //返回set类型的数据
    @Override
    public Set<Entry<K, V>> entrySet() {
        //创建HashSet对象
        Set<Map.Entry<K, V>> set = new HashSet<Map.Entry<K, V>>();
        Iterator ikey = keys.iterator();
        Iterator ivalue = values.iterator();

        while (ikey.hasNext()) {
            set.add(new MapEntry<K, V>((K) ikey.next(), (V) ivalue.next()));
        }

        return set;
    }

    public static void main(String[] args) {
        SlowMap<String, String> slowMap = new SlowMap();

        slowMap.put("1", "yi");
        slowMap.put("2", "er");
        slowMap.put("3", "san");

        System.out.println(slowMap);

    }
}
