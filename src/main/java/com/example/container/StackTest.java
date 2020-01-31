package com.example.container;

import java.util.Stack;


/**
 * 实现就是vector,只不过进栈和出栈总是操作最后一个元素，线程安全的
 */
public class StackTest {

    public static void main(String[] args) {
        Stack<Integer> integers = new Stack<>();
        integers.add(1);
    }

}
