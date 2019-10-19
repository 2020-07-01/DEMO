package dataStructure;

import java.util.BitSet;
import java.util.Random;

/**
 * @author :qiang
 * @date :2019/10/18 下午4:26
 * @description : 测试BitSet
 * @other :
 */
public class Bits {

    public static void printBitSet(BitSet b){
        System.out.println("bits:"+b);
        StringBuilder bbits = new StringBuilder();
        for(int j = 0;j<b.size();j++){
            bbits.append(b.get(j) ? "1" : 0);
        }

        System.out.println("bit pattern:"+bbits);

    }


    public static void main(String[] args) {
        Random rand = new Random(47);
        byte by = (byte)rand.nextInt();
        BitSet bb = new BitSet();


    }
}
