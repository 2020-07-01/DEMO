package leetcode.leetcode2020;

import callback_function.example2.Main;
import com.sun.org.apache.xpath.internal.operations.Bool;
import jdk.internal.org.objectweb.asm.Handle;
import proxy.springBootDemo.Interceptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName : November
 * @Author : yq
 * @Date: 2020-11-01
 * @Description :
 */
public class MainNovember {

    /**
     * 219. 存在重复元素 II
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if(nums == null || nums.length == 0){
            return false;
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) != null) {
                int index = map.get(nums[i]);
                if (Math.abs((index - i)) <= k) {
                    return true;
                } else {
                    map.put(nums[i], i);
                }
            } else {
                map.put(nums[i], i);
            }
        }
        return false;
    }

    /**
     * 500. 键盘行
     * @param words
     * @return
     */
    public String[] findWords(String[] words) {

        HashMap<String,Integer> map = new HashMap<>();
        map.put("q",1);
        map.put("w",1);
        map.put("e",1);
        map.put("r",1);
        map.put("t",1);
        map.put("y",1);
        map.put("u",1);
        map.put("i",1);
        map.put("o",1);
        map.put("p",1);

        map.put("a",2);
        map.put("s",2);
        map.put("d",2);
        map.put("f",2);
        map.put("g",2);
        map.put("h",2);
        map.put("j",2);
        map.put("k",2);
        map.put("l",2);

        map.put("z",3);
        map.put("x",3);
        map.put("c",3);
        map.put("v",3);
        map.put("b",3);
        map.put("n",3);
        map.put("m",3);


       List<String> list = new ArrayList<>();


           for(int i = 0;i<words.length;i++){

               String string  = words[i];
               int index = 0;
               Boolean flag = true;

               Integer temp = map.get(String.valueOf(string.charAt(index)).toLowerCase());
               while (index < string.length()){

                   if(map.get(String.valueOf(string.charAt(index)).toLowerCase()).intValue() != temp.intValue()){
                       flag = false;
                       break;
                   }
                   index++;
               }
               if(flag){
                   list.add(string);
               }

           }


       return list.toArray(new String[list.size()]);
    }


    public static void main(String[] args) {
        MainNovember mainNovember = new MainNovember();
        int[][] array1 = new int[][]{{0, 1}, {1, 0}};
        int[] array = new int[]{1,2,3,1};

        String[] strings = new String[]{"abdfs","cccd","a","qwwewm"};
        System.out.println(Arrays.toString(mainNovember.findWords(strings)));
    }

}
