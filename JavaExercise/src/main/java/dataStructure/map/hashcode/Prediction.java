package dataStructure.map.hashcode;

import java.io.RandomAccessFile;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author :qiang
 * @date :2019/10/16 上午11:40
 * @description :
 * @other :
 */
public class Prediction {

    private static Random rand = new Random(47);

    private boolean shadow = rand.nextDouble() > 0.5;

    public String toString() {
        if (shadow) {
            return "six more weeks of winter!";
        } else {
            return "Early Spring!";
        }
    }

}