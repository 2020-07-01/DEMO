package dataStructure.fillContainer;

import java.util.LinkedHashMap;

/**
 * @author :qiang
 * @date :2019/10/15 下午9:18
 * @description : 使用Generator填充map
 * @other :
 */
public class MapData<K, V> extends LinkedHashMap<K, V> {

    public MapData(Generator<Pair<K, V>> gen, int quantity) {
        for (int i = 0; i < quantity; i++) {
            Pair<K, V> p = gen.next();
            put(p.key, p.value);
        }
    }

    public MapData(Generator<K> genk, Generator<V> genv, int quantity) {

        for (int i = 0; i < quantity; i++) {
            put(genk.next(), genv.next());
        }
    }
}


