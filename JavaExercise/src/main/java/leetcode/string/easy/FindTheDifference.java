package string.easy;

import java.util.HashMap;

/**
 * @author :qiang
 * @date :2019/11/4 下午7:48
 * @description :找不同的字符
 * @other :
 * 给定两个字符串 s 和 t，它们只包含小写字母。
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 * 请找出在 t 中被添加的字母
 */
public class FindTheDifference {

    /**
     * 先遍历t将其存储在hashMap中，然后遍历s,如果存在则进行删除
     *
     * @param s
     * @param t
     * @return
     */
    public static char findTheDifference(String s, String t) {

        HashMap<Character, Integer> hashMap = new HashMap<>();

        for (Character item : t.toCharArray()) {
            hashMap.put(item, hashMap.getOrDefault(item, 0) + 1);
        }

        char result = '0';
        for (Character item : s.toCharArray()) {
            //如果不存在则直接返回
            if (!hashMap.containsKey(item)) {
                result = item;
                return result;
            }
            //如果value大于1则减1
            if (hashMap.get(item) > 1) {
                hashMap.put(item, hashMap.get(item) - 1);
                continue;
            }

            //如果value等于1则直接删除
            if (hashMap.get(item) == 1) {
                hashMap.remove(item);

            }
        }

        for (Character item : hashMap.keySet()) {
            result = item;
            return result;
        }

        return result;
    }

    public static void main(String[] args) {

        System.out.println(findTheDifference("a", "aa"));
    }
}
