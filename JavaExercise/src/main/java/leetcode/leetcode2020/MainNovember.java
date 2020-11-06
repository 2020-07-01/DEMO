package leetcode.leetcode2020;

import callback_function.example2.Main;
import com.sun.org.apache.xpath.internal.operations.Bool;
import jdk.internal.org.objectweb.asm.Handle;
import proxy.springBootDemo.Interceptor;

import java.net.InetAddress;
import java.util.*;

/**
 * @ClassName : November
 * @Author : yq
 * @Date: 2020-11-01
 * @Description :
 */
public class MainNovember {

    /**
     * 219. 存在重复元素 II
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
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
     *
     * @param words
     * @return
     */
    public String[] findWords(String[] words) {

        HashMap<String, Integer> map = new HashMap<>();
        map.put("q", 1);
        map.put("w", 1);
        map.put("e", 1);
        map.put("r", 1);
        map.put("t", 1);
        map.put("y", 1);
        map.put("u", 1);
        map.put("i", 1);
        map.put("o", 1);
        map.put("p", 1);

        map.put("a", 2);
        map.put("s", 2);
        map.put("d", 2);
        map.put("f", 2);
        map.put("g", 2);
        map.put("h", 2);
        map.put("j", 2);
        map.put("k", 2);
        map.put("l", 2);

        map.put("z", 3);
        map.put("x", 3);
        map.put("c", 3);
        map.put("v", 3);
        map.put("b", 3);
        map.put("n", 3);
        map.put("m", 3);


        List<String> list = new ArrayList<>();


        for (int i = 0; i < words.length; i++) {

            String string = words[i];
            int index = 0;
            Boolean flag = true;

            Integer temp = map.get(String.valueOf(string.charAt(index)).toLowerCase());
            while (index < string.length()) {

                if (map.get(String.valueOf(string.charAt(index)).toLowerCase()).intValue() != temp.intValue()) {
                    flag = false;
                    break;
                }
                index++;
            }
            if (flag) {
                list.add(string);
            }

        }


        return list.toArray(new String[list.size()]);
    }

    /**
     * 复写0
     * 空间复杂度O(n)
     * 时间复杂度O(n)
     *
     * @param arr
     */
    public void duplicateZeros(int[] arr) {

        if (arr == null || arr.length == 0) {
            return;
        }

        int index = 0;
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {

            if (arr[i] == 0) {
                result[index++] = 0;
                result[index++] = 0;
            } else {
                result[index++] = arr[i];
            }
            if (index == arr.length) {
                break;
            }
        }

        System.arraycopy(result, 0, arr, 0, arr.length);

    }

    /**
     * 复写0
     * 快慢指针法
     *
     * @param arr
     */
    public void duplicateZeros1(int[] arr) {

        if (arr == null || arr.length == 0) {
            return;
        }

        int left = 0;
        int right = 0;
        for (left = 0; right < arr.length; left++, right++) {
            if (arr[left] == 0) {
                right++;
            }
        }
        right--;
        left--;

        if (right > arr.length - 1) {
            arr[--right] = arr[left--];
            right--;
        }

        while (right > left) {
            if (arr[left] == 0) {

                arr[right--] = arr[left];
            }
            arr[right--] = arr[left--];
        }
    }

    /**
     * 884. 两句话中的不常见单词
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     *
     * @param A
     * @param B
     * @return
     */
    public String[] uncommonFromSentences(String A, String B) {
        if (A == null || A.length() == 0) {
            return B.split(" ");
        }
        if (B == null || B.length() == 0) {
            return A.split(" ");
        }

        HashMap<String, Integer> hashMap = new HashMap<>();
        HashMap<String, Integer> hashMapB = new HashMap<>();
        String[] stringA = A.split(" ");
        String[] stringB = B.split(" ");

        for (int i = 0; i < stringA.length; i++) {
            hashMap.put(stringA[i], hashMap.getOrDefault(stringA[i], 0) + 1);
        }

        List<String> result = new ArrayList<>();

        for (int i = 0; i < stringB.length; i++) {
            if (!hashMap.containsKey(stringB[i])) {

                if (!hashMapB.containsKey(stringB[i])) {
                    result.add(stringB[i]);
                    hashMapB.put(stringB[i], 1);
                } else {
                    result.remove(stringB[i]);
                }

            } else {
                hashMapB.put(stringB[i], 1);
                hashMap.remove(stringB[i]);
            }
        }

        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue() == 1) {
                result.add(entry.getKey());
            }
        }

        return result.toArray(new String[result.size()]);
    }


    /**
     * 884. 两句话中的不常见单词
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     *
     * @param A
     * @param B
     * @return
     */
    public String[] uncommonFromSentences1(String A, String B) {
        if (A == null || A.length() == 0) {
            return B.split(" ");
        }
        if (B == null || B.length() == 0) {
            return A.split(" ");
        }

        HashMap<String, Integer> map = new HashMap<>();

        String[] stringA = A.split(" ");
        String[] stringB = B.split(" ");

        for (int i = 0; i < stringA.length; i++) {
            map.put(stringA[i], map.getOrDefault(stringA[i], 0) + 1);
        }

        for (int i = 0; i < stringB.length; i++) {
            map.put(stringB[i], map.getOrDefault(stringB[i], 0) + 1);
        }

        ArrayList<String> list = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                list.add(entry.getKey());
            }
        }
        return list.toArray(new String[list.size()]);
    }

    /**
     * 1189. “气球” 的最大数量
     * if比较与switch哪个性能高
     *
     * @param text
     * @return
     */
    public int maxNumberOfBalloons(String text) {

        if (text == null || text.length() == 0) {
            return 0;
        }
        HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();
        int index = 0;
        while (index < text.length()) {
            char c = text.charAt(index);
            switch (c) {
                case 'b':
                    hashMap.put(Character.valueOf(c), hashMap.getOrDefault(Character.valueOf(c), 0) + 1);
                    break;
                case 'a':
                    hashMap.put(Character.valueOf(c), hashMap.getOrDefault(Character.valueOf(c), 0) + 1);
                    break;
                case 'l':
                    hashMap.put(Character.valueOf(c), hashMap.getOrDefault(Character.valueOf(c), 0) + 1);
                    break;
                case 'o':
                    hashMap.put(Character.valueOf(c), hashMap.getOrDefault(Character.valueOf(c), 0) + 1);
                    break;
                case 'n':
                    hashMap.put(Character.valueOf(c), hashMap.getOrDefault(Character.valueOf(c), 0) + 1);
                    break;
                default:
                    index++;
                    continue;
            }
            index++;
        }

        if (hashMap.size() < 5) {
            return 0;
        }
        int l = Integer.MAX_VALUE;
        int a = Integer.MAX_VALUE;
        //balloon
        for (Map.Entry<Character, Integer> entry : hashMap.entrySet()) {
            if (entry.getKey().charValue() == 'o' || entry.getKey().charValue() == 'l') {
                l = Math.min(l, entry.getValue());
            } else {
                a = Math.min(a, entry.getValue());
            }
        }

        return Math.min(a, (l / 2));
    }

    /**
     * 1207. 独一无二的出现次数
     * 两次哈希表
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     *
     * @param arr
     * @return
     */
    public boolean uniqueOccurrences(int[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        Set<Integer> set = new HashSet<>();

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (set.contains(entry.getValue())) {
                return false;
            } else {
                set.add(entry.getValue());
            }
        }
        return true;
    }


    /**
     * 1207. 独一无二的出现次数
     * 两次哈希表
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     *
     * @param arr
     * @return
     */
    public boolean uniqueOccurrences1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        Set<Integer> set = new HashSet<>(map.values());
        return set.size() == map.size();
    }


    /**
     * 575. 分糖果
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     * @param candies
     * @return
     */
    public int distributeCandies(int[] candies) {

        if(candies == null || candies.length == 0){
            return 0;
        }

        HashMap<Integer,Integer> hashMap = new HashMap();
        int result = 0;
        for (int i : candies) {
            if(hashMap.containsKey(i)){
                continue;
            }else {
                result++;
                //加完这行代码，性能提升一倍
                if(result == candies.length/2){
                    return result;
                }
                hashMap.put(i,i);
            }
        }
        return result;

    }

    /**
     * 回文排列
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     * 某个字段串是否为某个回文串得排列之一
     * @param s
     * @return
     */
    public boolean canPermutePalindrome(String s) {

        if(s == null || s.length() == 0){
            return true;
        }
        char[] chars = s.toCharArray();

        Set<Character> set = new HashSet<>();
        for (int i = 0;i<chars.length;i++){
            if(set.contains(chars[i])){
                set.remove(chars[i]);
            }else {
                set.add(chars[i]);
            }
        }

        return set.size() <= 1 ? true : false;
    }


    /**
     *
     * @param s
     * @return
     */
    public boolean canPermutePalindrome1(String s) {

        if(s == null || s.length() < 3){
            return true;
        }
        char[] chars = s.toCharArray();

        HashMap<Character,Integer> hashMap = new HashMap<>();
        for (int i = 0;i<chars.length;i++){
             hashMap.put(chars[i],hashMap.getOrDefault(chars[i],0)+1);
        }
        int i = 0;
        for(Map.Entry<Character,Integer> entry : hashMap.entrySet()){
            if(entry.getValue()%2 != 0){
                i++;
            }
            if(i == 2){
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        MainNovember mainNovember = new MainNovember();
        int[][] array1 = new int[][]{{0, 1}, {1, 0}};
        int[] array = new int[]{1, 2, 2, 1, 1, 3};

        //mainNovember.duplicateZeros(array);
        String[] strings = new String[]{"abdfs", "cccd", "a", "qwwewm"};
        //System.out.println(Arrays.toString(mainNovember.uncommonFromSentences("this apple is sweet", "this apple is sour")));

        System.out.println(mainNovember.uniqueOccurrences(array));
    }

}
