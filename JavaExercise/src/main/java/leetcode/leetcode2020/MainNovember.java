package leetcode.leetcode2020;

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
     *
     * @param candies
     * @return
     */
    public int distributeCandies(int[] candies) {

        if (candies == null || candies.length == 0) {
            return 0;
        }

        HashMap<Integer, Integer> hashMap = new HashMap();
        int result = 0;
        for (int i : candies) {
            if (hashMap.containsKey(i)) {
                continue;
            } else {
                result++;
                //加完这行代码，性能提升一倍
                if (result == candies.length / 2) {
                    return result;
                }
                hashMap.put(i, i);
            }
        }
        return result;

    }

    /**
     * 回文排列
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     * 某个字段串是否为某个回文串得排列之一
     *
     * @param s
     * @return
     */
    public boolean canPermutePalindrome(String s) {

        if (s == null || s.length() == 0) {
            return true;
        }
        char[] chars = s.toCharArray();

        Set<Character> set = new HashSet<>();
        for (int i = 0; i < chars.length; i++) {
            if (set.contains(chars[i])) {
                set.remove(chars[i]);
            } else {
                set.add(chars[i]);
            }
        }

        return set.size() <= 1 ? true : false;
    }


    /**
     * @param s
     * @return
     */
    public boolean canPermutePalindrome1(String s) {

        if (s == null || s.length() < 3) {
            return true;
        }
        char[] chars = s.toCharArray();

        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            hashMap.put(chars[i], hashMap.getOrDefault(chars[i], 0) + 1);
        }
        int i = 0;
        for (Map.Entry<Character, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue() % 2 != 0) {
                i++;
            }
            if (i == 2) {
                return false;
            }
        }

        return true;
    }


    /**
     * 720. 词典中最长的单词
     *
     * @param words
     * @return
     */
    public String longestWord(String[] words) {
        if (words == null || words.length == 0) {
            return null;
        }

        HashMap<String, Boolean> hashMap = new HashMap<>(words.length);

        for (int i = 0; i < words.length; i++) {
            hashMap.put(words[i], true);
        }
        String result = "";
        Arrays.sort(words, (v1, v2) -> {
            return v1.length() - v2.length();
        });

        for (int i = words.length - 1; i >= 0; i--) {
            String temp = words[i];
            Boolean p = true;
            while (temp.length() > 0) {
                if (hashMap.containsKey(temp)) {
                    temp = temp.substring(0, temp.length() - 1);
                } else {
                    p = false;
                    break;
                }
            }
            if (p) {
                if (result.length() == 0) {
                    result = words[i];
                } else {
                    if (result.length() == words[i].length()) {
                        int index = 0;
                        while (index >= 0) {
                            int r1 = result.charAt(index);
                            int w1 = words[i].charAt(index);
                            if (r1 > w1) {
                                result = words[i];
                                break;
                            } else if (r1 == w1) {
                                index++;
                                continue;
                            } else {
                                break;
                            }
                        }
                    } else {
                        return result;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 409. 最长回文串
     * 哈希法
     *
     * @param s
     * @return
     */
    public int longestPalindrome(String s) {

        /**
         * 找出所有的偶数
         * 再加1
         */

        //aa aa
        int sum = 0;
        HashMap<Character, Boolean> hashMap = new HashMap<>(s.length());
        int index = 0;
        while (index < s.length()) {
            if (hashMap.containsKey(s.charAt(index))) {
                hashMap.remove(s.charAt(index));
                sum += 2;
            } else {
                hashMap.put(s.charAt(index), true);
            }
            index++;
        }

        if (hashMap.isEmpty()) {
            return sum;
        } else {
            return sum + 1;
        }
    }

    /**
     * 最长回文串
     * set法
     *
     * @param s
     * @return
     */
    public int longestPalindrome1(String s) {

        /**
         * 找出所有的偶数
         * 再加1
         */

        //aa aa
        int sum = 0;
        Set<Character> set = new TreeSet<>();
        int index = 0;
        while (index < s.length()) {
            if (set.contains(s.charAt(index))) {
                set.remove(s.charAt(index));
                sum += 2;
            } else {
                set.add(s.charAt(index));
            }
            index++;
        }

        if (set.isEmpty()) {
            return sum;
        } else {
            return sum + 1;
        }
    }

    /**
     * 最长回文串
     * set法
     *
     * @param s
     * @return
     */
    public int longestPalindrome2(String s) {

        int sum = 0;
        int index = 0;
        while (index < s.length() && s.length() >= 1) {
            char c = s.charAt(index);
            String temp = s.substring(index + 1);
            if (temp.contains(Character.toString(c))) {
                temp = temp.replaceFirst(String.valueOf(c), "");
                sum = sum + 2;
                index = 0;
                s = temp;
            } else {
                index++;
            }
        }

        if (s.length() > 0) {
            return sum + 1;
        } else {
            return sum;
        }
    }

    /**
     * 704. 二分查找
     *
     * @param nums
     * @param target
     * @return
     */
    public int search1(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 魔法索引
     *
     * @param nums
     * @return
     */
    public int findMagicIndex(int[] nums) {

        if (nums == null || nums.length == 0) {
            return -1;
        }

        for (int i = 0; i < nums.length; i++) {
            if (i == nums[i]) {
                return i;
            }
        }

        return -1;
    }

    /**
     * @param root
     * @param low
     * @param high
     * @return
     */
    public int rangeSumBST(TreeNode root, int low, int high) {

        List<Integer> list = new ArrayList<>();

        sumLeft(root, list, low, high);
        int sum = 0;
        for (Integer integer : list) {
            sum = sum + integer;
        }
        return sum;
    }

    private void sumLeft(TreeNode node, List<Integer> list, int low, int hiht) {
        if (node != null) {
            if (low <= node.val && node.val <= hiht) {
                list.add(node.val);
            }
            if (low < node.val) {
                sumLeft(node.left, list, low, hiht);
            }
            if (node.val < hiht) {
                sumLeft(node.right, list, low, hiht);
            }
        }

    }

    private void sumRight(TreeNode node, List<Integer> list, int high) {
        if (node.val < high) {
            list.add(node.val);
            if (node.left != null) {
                sumRight(node.left, list, high);
            }
        }
    }

    /**
     * 1160. 拼写单词
     * 时间复杂度O(n*m)
     *
     * @param words
     * @param chars
     * @return
     */
    public int countCharacters(String[] words, String chars) {

        HashMap<Character, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < chars.length(); i++) {
            hashMap.put(chars.charAt(i), hashMap.getOrDefault(chars.charAt(i), 0) + 1);
        }
        int sum = 0;
        for (int i = 0; i < words.length; i++) {
            String temp = words[i];
            int index = 0;
            HashMap<Character, Integer> map = new HashMap<>();
            while (index < temp.length()) {
                if (hashMap.containsKey(temp.charAt(index))) {
                    map.put(temp.charAt(index), map.getOrDefault(temp.charAt(index), 0) + 1);
                    if (map.get(temp.charAt(index)) > hashMap.get(temp.charAt(index))) {
                        break;
                    } else {
                        index++;
                    }
                } else {
                    break;
                }
            }
            if (index == temp.length()) {
                sum = sum + temp.length();
            }
        }
        return sum;
    }


    List<Integer> result = new ArrayList<>();

    /**
     * N茶树的前序遍历 递归法
     *
     * @param root
     * @return
     */
    public List<Integer> preorder(Node root) {

        if (root == null) {
            return result;
        }
        result.add(root.val);
        for (Node n : root.children) {
            preorder(n);
        }
        return result;
    }

    /**
     * N叉树的前序遍历
     * 迭代法
     *
     * @param root
     * @return
     */
    public List<Integer> preorder1(Node root) {

        Stack<Node> stack = new Stack<>();
        List<Integer> list = new LinkedList<>();
        if (root == null) {
            return list;
        }
        stack.add(root);
        while (!stack.empty()) {
            Node node = stack.pop();
            list.add(node.val);
            //反转
            //123
            //321
            Collections.reverse(node.children);
            for (Node n : node.children) {
                //栈的底部添加
                //3 2 1
                stack.add(n);
            }
        }
        return list;
    }


    /**
     * N叉树的中序遍历
     * 递归法
     *
     * @param node
     * @return
     */
    public List<Integer> postOrder(Node node) {

        if (node == null) {
            return result;
        }

        for (Node n : node.children) {
            postOrder(n);
        }
        result.add(node.val);
        return result;
    }

    /**
     * N叉树的中序遍历 递归法
     *
     * @param node
     * @return
     */
    public List<Integer> order(Node node) {

        if (node == null) {
            return result;
        }

        for (Node n : node.children) {
            if (n.val < node.val) {
                order(n);
            } else if (n.val > node.val) {
                order(n);
            } else {
                result.add(node.val);
            }
        }
        return result;
    }


    /**
     * 599. 两个列表的最小索引总和
     * 两次Hash
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     *
     * @param list1
     * @param list2
     * @return
     */
    public String[] findRestaurant(String[] list1, String[] list2) {

        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }

        int min = Integer.MAX_VALUE;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < list2.length; i++) {
            Integer l1 = map.get(list2[i]);
            if (l1 != null) {
                if (l1 + i < min) {
                    list = new ArrayList<>();
                    list.add(list2[i]);
                    min = l1 + i;
                }

                if (l1 + i == min) {
                    list.add(list2[i]);
                }
            }
        }
        return list.toArray(new String[list.size()]);
    }


    /**
     * 961. 重复 N 次的元素
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     *
     * @param A
     * @return
     */
    public int repeatedNTimes(int[] A) {

        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < A.length; i++) {
            hashMap.put(A[i], hashMap.getOrDefault(A[i], 0) + 1);
        }

        int n = hashMap.size() - 1;

        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            if (n == entry.getValue()) {
                return entry.getKey();
            }
        }
        return -1;
    }


    /**
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> lists = new ArrayList<>();

        for (int i = 2; i < nums.length; i++) {
            for (int j = 1; j < i; j++) {
                for (int k = 0; k < j; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {

                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        lists.add(list);
                    }
                }
            }
        }

        return lists;
    }


    /**
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern(String s) {

        String string = s + s;

        return string.substring(1, string.length() - 1).contains(s);
    }

    /**
     * 441. 排列硬币
     *
     * @param n
     * @return
     */
    public int arrangeCoins(int n) {

        long sum = 0;
        int index = 0;
        while (sum < n) {
            index++;
            sum = sum + index;
        }
        if (sum == n) {
            return index;
        } else {
            return index - 1;
        }
    }


    /**
     * 441. 排列硬币
     * 等差数列求和公式
     * 和 = (首项 + 末项) * 项数 / 2
     *
     * @param n
     * @return
     */
    public int arrangeCoins1(int n) {

        int left = 1;
        int right = n;
        long sum;
        while (left <= right) {
            long mid = left * (right - left) / 2;
            sum = (1 + mid) * mid / 2;
            if (sum == n) {
                return (int) mid;
            } else if (sum > n) {
                right = (int) mid - 1;
            } else {
                left = (int) mid + 1;
            }
        }

        return right;
    }

    /**
     * 二分查找数字出现的次数
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {

        //如果能找到，左右两边遍历
        int left = 0;
        int right = nums.length - 1;
        int count = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                //往左
                for (int i = mid; i >= left; i--) {
                    if (nums[i] == target) {
                        count++;
                    } else {
                        break;
                    }
                }
                //往右
                for (int i = mid; i <= right; i++) {
                    if (nums[i] == target) {
                        count++;
                    } else {
                        break;
                    }
                }

                return count - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return 0;
    }


    /**
     * 852. 山脉数组的峰顶索引
     * 遍历/二分法
     *
     * @param arr
     * @return
     */
    public int peakIndexInMountainArray(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return i;
            }
        }
        return 0;
    }

    /**
     * 744. 寻找比目标字母大的最小字母
     * 时间复杂度O(n)
     *
     * @param letters
     * @param target
     * @return
     */
    public char nextGreatestLetter(char[] letters, char target) {

        if (letters == null || letters.length == 0) {
            return target;
        }
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] > target) {
                return letters[i];
            }
        }
        return letters[0];
    }


    /**
     * 744. 寻找比目标字母大的最小字母
     * 二分法查找
     *
     * @param letters
     * @param target
     * @return
     */
    public char nextGreatestLetter1(char[] letters, char target) {

        if (letters == null || letters.length == 0) {
            return target;
        }

        int left = 0;
        int right = letters.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (letters[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return letters[left % letters.length];
    }


    public static char nextGreatestLetter2(char[] letters, char target) {
        int left = 0;
        int right = letters.length;

        if (target >= letters[letters.length - 1]) {//循环比较的处理
            return letters[0];
        } else {
            while (left < right) {//二分搜索的应用
                int mid = (left + right) / 2;
                if (letters[mid] <= target) {
                    left = mid + 1;
                }
                if (letters[mid] > target) {
                    right = mid;
                }
            }
            return letters[left];
        }
    }


    /**
     * 876. 链表的中间结点
     * 快慢指针法：
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     *
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {

        ListNode fast = head;

        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 24. 两两交换链表中的节点
     * 迭代法
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {

        ListNode root = new ListNode(0);

        ListNode curNode = root;
        ListNode next = null;
        ListNode cur = null;
        while (head != null && head.next != null) {
            next = new ListNode(head.next.val);
            curNode.next = next;
            cur = new ListNode(head.val);
            curNode.next.next = cur;
            curNode = curNode.next.next;

            head = head.next.next;
        }

        if (head == null) {
            return root.next;
        } else {
            ListNode node = new ListNode(head.val);
            curNode.next = node;
        }
        return root.next;
    }

    /**
     * 203. 移除链表元素
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return head;
        }

        ListNode node = head.next;
        ListNode pre = head;
        while (node != null) {
            if (node.val == val) {
                node = node.next;
            } else {
                pre.next = node;
                pre = pre.next;
                node = node.next;
            }
        }
        pre.next = node;
        if (head.val == val) {
            return head.next;
        } else {
            return head;
        }
    }

    /**
     * 203. 移除链表元素
     * 双指针法
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements1(ListNode head, int val) {

        if (head == null) {
            return head;
        }

        ListNode node = new ListNode(0);
        ListNode slow = node;

        while (head != null) {
            if (head.val == val) {
                head = head.next;
            } else {
                ListNode temp = new ListNode(head.val);
                slow.next = temp;
                slow = slow.next;
                head = head.next;
            }
        }

        return node.next;
    }


    /**
     * 面试题 02.01. 移除重复节点
     * set辅助
     *
     * @param head
     * @return
     */
    public ListNode removeDuplicateNodes(ListNode head) {


        if (head == null) {
            return null;
        }

        ListNode left = new ListNode(head.val);
        ListNode node = left;

        Set<Integer> set = new TreeSet<>();

        head = head.next;
        while (head != null) {

            if (!set.contains(head.val)) {
                ListNode temp = new ListNode(head.val);
                node.next = temp;
                node = node.next;
                set.add(head.val);
            }

            head = head.next;
        }

        return left;
    }

    /**
     * 面试题 02.01. 移除重复节点
     *
     * @param head
     * @return
     */
    public ListNode removeDuplicateNodes1(ListNode head) {

        if (head == null) {
            return null;
        }

        ListNode node = new ListNode(head.val);
        node = head;

        Set<Integer> set = new TreeSet<>();

        set.add(head.val);

        while (head.next != null) {

            if (!set.contains(head.next.val)) {
                set.add(head.next.val);
                head = head.next;
            } else {

                head.next = head.next.next;
            }
        }
        return node;
    }

    /**
     * 面试题 02.07. 链表相交
     * <p>
     * 链表相交指的是两个链表中存在相同节点
     * set方法
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {


        Set<ListNode> set = new HashSet<>();

        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (set.contains(headB)) {
                return headB;
            } else {
                headB = headB.next;
            }
        }
        return null;
    }

    /**
     * 面试题 02.07. 链表相交
     * 双指针法
     * 当链表一样长时
     * A 走完了从B开始
     * B 走完了从A开始
     * 进行判断
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        //tempA和tempB我们可以认为是A,B两个指针
        ListNode tempA = headA;
        ListNode tempB = headB;
        while (tempA != tempB) {
            //如果指针tempA不为空，tempA就往后移一步。
            //如果指针tempA为空，就让指针tempA指向headB（注意这里是headB不是tempB）
            tempA = tempA == null ? headB : tempA.next;
            //指针tempB同上
            tempB = tempB == null ? headA : tempB.next;
        }
        //tempA要么是空，要么是两链表的交点
        return tempA;
    }

    /**
     * 1290. 二进制链表转整数
     *
     * @param head
     * @return
     */
    public int getDecimalValue(ListNode head) {

        Stack<Integer> stack = new Stack<>();

        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }
        int sum = 0;
        int i = 0;
        while (!stack.empty()) {
            sum = sum + stack.pop() * (int) Math.pow(2, i);
        }
        return sum;
    }

    /**
     * 1290. 二进制链表转整数
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     *
     * @param head
     * @return
     */
    public int getDecimalValue1(ListNode head) {

        ListNode node = head;
        int count = 0;
        while (node != null) {
            count++;
            node = node.next;
        }
        count = count - 1;
        int sum = 0;
        while (count >= 0) {
            sum = sum + head.val * (int) Math.pow(2, count);
            head = head.next;
            count--;
        }
        return sum;
    }

    /**
     * 合并二叉树
     *
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {

        if (t1 == null && t2 == null) {
            return null;
        }

        //合并根节点
        TreeNode root = new TreeNode((t1 == null ? 0 : t1.val) + (t2 == null ? 0 : t2.val));

        //合并左节点
        root.left = mergeTrees(t1 == null ? null : t1.left, t2 == null ? null : t2.left);
        //合并右节点
        root.right = mergeTrees(t1 == null ? null : t1.right, t2 == null ? null : t2.right);

        return root;
    }


    /**
     * @param root
     * @return
     */
    public int getMinimumDifference(TreeNode root) {

        List<Integer> list = new LinkedList<>();


        set(list, root);

        Collections.sort(list);

        if (list.size() == 1) {

            return list.get(0);
        } else {
            return list.get(0) - list.get(list.size() - 1);
        }

    }


    private void set(List<Integer> list, TreeNode root) {
        if (root != null) {
            list.add(root.val);
            set(list, root.left);
            set(list, root.right);
        } else {
            return;
        }
    }


    int maxCount = 0;
    List<Integer> list = new LinkedList<>();

    /**
     * 501. 二叉搜索树中的众数
     *
     * @param root
     * @return
     */
    public int[] findMode1(TreeNode root) {

        /**
         * 遍历二叉树
         * 记录次数
         * 获取次数最高的元素
         */

        HashMap<Integer, Integer> map = new HashMap<>();
        iterator(map, root);

        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    private void iterator(HashMap<Integer, Integer> map, TreeNode node) {
        if (node == null) {
            return;
        } else {
            map.put(node.val, map.getOrDefault(node.val, 0) + 1);

            int count = map.get(node.val);
            if (count > maxCount) {
                list = new ArrayList<>();
                list.add(node.val);
                maxCount = count;
            } else if (count == maxCount) {
                list.add(node.val);
            }
            iterator(map, node.left);
            iterator(map, node.right);
        }
    }


    List<Integer> answer = new ArrayList<Integer>();
    int base, count;

    public int[] findMode(TreeNode root) {
        dfs(root);
        int[] mode = new int[answer.size()];
        for (int i = 0; i < answer.size(); ++i) {
            mode[i] = answer.get(i);
        }
        return mode;
    }

    public void dfs(TreeNode o) {
        if (o == null) {
            return;
        }
        dfs(o.left);
        update(o.val);
        dfs(o.right);
    }

    public void update(int x) {
        if (x == base) {
            ++count;
        } else {
            count = 1;
            base = x;
        }
        if (count == maxCount) {
            answer.add(base);
        }
        if (count > maxCount) {
            maxCount = count;
            answer.clear();
            answer.add(base);
        }
    }




    public static void main(String[] args) {
        MainNovember mainNovember = new MainNovember();
        int[][] array1 = new int[][]{{0, 1}, {1, 0}};
        int[] array = new int[]{0, 2, 3, 4, 5};

        //mainNovember.duplicateZeros(array);
        String[] strings = new String[]{"Shogun", "Tapioca Express", "Burger King", "KFC"};

        String[] strings1 = new String[]{"KFC", "Shogun", "Burger King"};

        //System.out.println(Arrays.toString(mainNovember.uncommonFromSentences("this apple is sweet", "this apple is sour")));

        System.out.println(mainNovember.arrangeCoins1(5));

    }

}
