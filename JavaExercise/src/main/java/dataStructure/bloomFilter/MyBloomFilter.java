package dataStructure.bloomFilter;

import java.util.BitSet;

/**
 * @ClassName : MyBloomFilter
 * @Author : yq
 * @Date: 2021-03-02
 * @Description : 布隆过滤器实现
 */
public class MyBloomFilter {

    /**
     * 位数组的大小
     */
    private static final int DEFAULT_SIZE = 2 << 24;

    /**
     * 通过这个数组创建6个不同的哈希函数
     */
    private static final int[] SEEDS = new int[]{3, 13, 46, 71, 91, 134};

    /**
     * 位数组。数组中的元素只能是0或者1
     */
    private BitSet bits = new BitSet(DEFAULT_SIZE);

    /**
     * 包含哈希函数类的数组
     */
    private SimpleHash[] func = new SimpleHash[SEEDS.length];


    public MyBloomFilter() {
        // 初始化多个Hash函数
        for (int i = 0; i < SEEDS.length; i++) {
            func[i] = new SimpleHash(DEFAULT_SIZE, SEEDS[i]);
        }
    }

    /**
     * 添加元素到位数组
     */
    public void add(Object value) {
        for (SimpleHash f : func) {
            bits.set(f.hash((value)));
        }
    }

    public boolean contains(Object value) {
        boolean ret = true;
        for (SimpleHash f : func) {
            ret = ret && bits.get(f.hash(value));
        }
        return ret;
    }

    /**
     * 静态内部类，用户哈希操作
     */
    public static class SimpleHash {

        private int cap;
        private int seed;

        public SimpleHash(int cap, int seed) {
            this.cap = cap;
            this.seed = seed;
        }

        public int hash(Object value) {
            int h;
            return (value == null) ? 0 : Math.abs(seed * (cap - 1) & ((h = value.hashCode()) ^ h >>> 16));
        }
    }


    public static void main(String[] args) {
        /*String value1 = "https://javaguide.cn/";
        String value2 = "https://github.com/Snailclimb";
        MyBloomFilter filter = new MyBloomFilter();

        System.out.println(filter.contains(value1));
        System.out.println(filter.contains(value2));
        filter.add(value1);
        filter.add(value2);
        System.out.println(filter.contains(value1));
        System.out.println(filter.contains(value2));*/

        System.out.println(100 >> 6);

        BitSet bitSet = new BitSet(100);
        bitSet.set(10);


    }

}
