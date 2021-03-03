package dataStructure.bloomFilter;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName : DemoMain
 * @Author : yq
 * @Date: 2021-03-01
 * @Description :
 */
public class DemoMain {


    public static void main(String[] args) {

        BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), 1000000, 0.001);

        List<String> lists = new LinkedList<>();

        for (int i = 0; i < 1000000; i++) {
            String uuid = UUID.randomUUID().toString();
            bloomFilter.put(uuid);
            lists.add(uuid);
        }

        int wrong = 0;
        int right = 0;

        for (int i = 0; i < 10000; i++) {
            String test = i % 100 == 0 ? lists.get(i) : UUID.randomUUID().toString();
            if (bloomFilter.mightContain(test)) {
                right++;
            } else {
                wrong++;
            }
        }

        System.out.println("=================right=====================" + right);//100
        System.out.println("=================wrong=====================" + wrong);
    }


}
