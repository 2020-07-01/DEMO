package stack.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :qiang
 * @date :2019/10/20 下午8:33
 * @description :设计一个最小的栈,使用ArrayList实现
 * @other :
 */
public class MinStack {
    int min = Integer.MAX_VALUE;
    List<Integer> list = new ArrayList<Integer>();

    /**
     * initialize your data structure here.
     */
    public MinStack() {

    }

    public void push(int x) {
        list.add(x);
        if (x < min) {
            min = x;
        }
    }

    public void pop() {
        //如果栈顶的元素于min相等则从头遍历list
        if (min == list.get(list.size() - 1)) {
            min = Integer.MAX_VALUE;
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i) < min) {
                    min = list.get(i);
                }
            }
        }
        if (list.size() > 0) {
            //将最后一个元素删除
            list.remove(list.size() - 1);
        }
    }

    //返回最后一个元素
    public int top() {
        return list.get(list.size() - 1);
    }

    public int getMin() {
        return min;
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(1);
        minStack.push(2);
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());


    }

}
