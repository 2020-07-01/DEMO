package stack.easy;


import java.util.Stack;

/**
 * @author :qiang
 * @date :2019/10/20 下午9:34
 * @description :使用栈实现
 * @other :
 */
public class MinStack1 {

    Stack<Integer> stack = new Stack<>();
    //辅助栈的栈顶一直存储最小的元素
    Stack<Integer> minStack = new Stack<>();

    MinStack1() {
    }

    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty() || x <= minStack.peek())
            minStack.push(x);
    }

    public void pop() {
        //如果栈顶存储的值相同则同时删除即可
        if (stack.pop().equals(minStack.peek())) {

            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {

        return minStack.peek();
    }

}
