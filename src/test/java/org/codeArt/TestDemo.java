package org.codeArt;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

public class TestDemo {

    public String radixChange(int num, int base) {
        StringBuilder sb = new StringBuilder();
        Deque<String> stack = new LinkedList<>();
        while (num % base != 0) {
            int temp = num % base;

            stack.push(format(temp));

            num /= base;
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }

    public String format(int a) {
        switch (a) {
            case 10: return "A";
            case 11: return "B";
            case 12: return "C";
            default: return a + "";
        }
    }

    @Test
    public void test() {
        int a = 100;
        System.out.println(radixChange(a, 13));
    }

}
