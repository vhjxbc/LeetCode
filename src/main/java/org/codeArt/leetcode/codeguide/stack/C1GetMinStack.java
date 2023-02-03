package org.codeArt.leetcode.codeguide.stack;

import java.util.Stack;

/**
 * 实现一个特殊的栈,在实现栈的基本功能的基础上
 * 再实现返回栈中最小元素的操作
 */
public class C1GetMinStack {

    /*
     * 要求:
     * 1.pop, push, getMin操作的时间复杂度都是O(1)
     * 2.设计的栈类型可以使用线程的栈结构(Deque, Stack)
     *
     * 解决方案:
     *     在设计时,我们使用两个栈,一个栈用来保存当前栈中的元素,其功能和一个正常的栈
     *     没有区别,这个栈记为 stackData. 另一个栈用于保存每一步的最小值,这个栈记为 stackMin.
     *     具体实现方式有两种:
     *     (1) 双栈法:
     *         压入数据规则:
     *          假设当前数据为 newNum, 先将其压入 stackData,然后判断 stackMin 是否为空
     *          如果为空,则 newNum 也压入stackMin
     *          如果不为空,则比较 newNum 和 stackMin 的栈顶元素中哪一个更小
     *          如果 stackMin 中栈顶元素小,则 stackMin 不压入任何内容
     *
     *         弹出数据规则:
     *          现在 stackData 中弹出栈顶元素,记为 value,然后比较当前 stackMin 的栈顶元素和 value
     *          哪一个更小.通过上文提到的压入规则可知, stackMin 中存在的元素是从栈底到栈顶逐渐变小的
     *          stackMin 栈顶的元素既是 stackMin 栈的最小值,也是挡墙 stackData 栈的最小值,所以不会出现
     *          value 比 stackMin 的栈顶元素更小的情况, value 只可能大于或等于 stackMin 的栈顶元素
     *          当 value 等于 stackMin 的栈顶元素时, stackMin 弹出栈顶元素,当 value 大于 stackMin 的
     *          栈顶元素时, stackMin 不弹出栈顶元素,返回 value
     */
    static class MyStack1 {
        private final Stack<Integer> stackData;
        private final Stack<Integer> stackMin;

        public MyStack1() {
            this.stackData = new Stack<>();
            this.stackMin = new Stack<>();
        }

        public void push(int newNum) {
            if (stackMin.isEmpty() || newNum <= getMin()) {
                stackMin.push(newNum);
            }

            stackData.push(newNum);
        }

        public int pop() {
            if (stackData.isEmpty()) {
                throw new RuntimeException("stack is empty!");
            }

            int value = stackData.pop();

            if (value == getMin()) {
                stackMin.pop();
            }

            return value;
        }

        private int getMin() {
            if (stackMin.isEmpty()) {
                throw new RuntimeException("stack is empty!");
            }

            return stackMin.peek();
        }
    }

    /**
     * (2) 还是双栈略微有些差别
     *     压入数据规则:
     *       假设当前数据为 newNum,先将其压入 stackData,然后判断 stackMin 是否为空
     *       如果为空,则 newNum 也压入 stackMin,如果不为空,则比较 newNum 和 stackMin的栈顶
     *       元素哪一个更小,如果 newNum 更小或者两者相等, newNum 也压入 stackMin,如果 stackMin 中
     *       栈元素小,则吧 stackMin 的栈顶元素重复压入stackMin, 即在栈顶元素上再压入一个栈顶元素
     */
    static class MyStack2 {
        private final Stack<Integer> stackData;
        private final Stack<Integer> stackMin;

        public MyStack2() {
            this.stackData = new Stack<>();
            this.stackMin = new Stack<>();
        }

        public void push(int newNum) {
            if (stackMin.isEmpty() || newNum < getMin()) {
                stackMin.push(newNum);
            } else {  //重复插入元素操作
                int newMin = stackMin.peek();
                stackMin.push(newMin);
            }

            stackData.push(newNum);
        }

        public int pop() {
            if (stackData.isEmpty()) {
                throw new RuntimeException("stack is empty!");
            }

            stackMin.pop();

            return stackData.pop();
        }

        private int getMin() {
            if (stackMin.isEmpty()) {
                throw new RuntimeException("stack is empty!");
            }

            return stackMin.peek();
        }
    }

    /**
     * 方案一和方案二都是利用 stackMin 栈保存着 stackData 每一步的最小值,共同点时所有操作的时间复杂度都是 O(1)
     * 空间复杂度都是 O(n),区别是: 方案一种 stackMin 压入时稍微省空间,但是弹出操作稍微费时间,方案二相反
     */
    public static void main(String[] args) {
        int[] values = new int[]{10, 5, 49, 15, 72, 999, 753, 165};

        MyStack1 stack1 = new MyStack1();
        MyStack2 stack2 = new MyStack2();

        for (int value : values) {
            stack1.push(value);
            stack2.push(value);
        }

        System.out.println(stack1.getMin());
        System.out.println(stack2.getMin());
    }




}
