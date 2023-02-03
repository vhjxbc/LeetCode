package org.codeArt.leetcode.codeguide.stack;

import java.util.Stack;

/**
 * 编写一个类,用两个栈实现队列,支持队列的基本操作
 */
public class C2TwoStackQueue {

    public Stack<Integer> stackPush;
    public Stack<Integer> stackPop;

    public C2TwoStackQueue() {
        this.stackPush = new Stack<>();
        this.stackPop = new Stack<>();
    }

    /**
     * 具体实现:
     *   用一个栈作为压入栈,用一个栈作为弹出栈,还是使用双栈的思想
     *   push栈向pop栈导入数据
     */
    private void pushToPop() {
        if (stackPop.empty()) {
            while (!stackPush.empty()) {
                stackPop.push(stackPush.pop());
            }
        }
    }

    public void add(int pushInt) {
        stackPush.push(pushInt);

        pushToPop();
    }

    public int poll() {
        if (stackPop.empty() && stackPush.empty()) {
            throw new RuntimeException("Queue is empty");
        }

        pushToPop();

        return stackPop.pop();
    }

    public int peek() {
        if (stackPop.empty() && stackPush.empty()) {
            throw new RuntimeException("Queue is empty");
        }

        pushToPop();

        return stackPop.peek();
    }

    public static void main(String[] args) {
        C2TwoStackQueue a = new C2TwoStackQueue();

        int[] values = {1,2,3,4,5,6,7,8,9,10};

        for (int i : values) {
            a.add(i);
        }

        for (int i = 0; i < values.length; i++) {
            System.out.print(a.poll() + "\t");
        }
    }


}
