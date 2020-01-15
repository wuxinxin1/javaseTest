package com.example.container;

import java.util.Vector;

/**
 * @author wuxinxin
 * vector基本和ArrayList实现差不多，只不过是线程安全的
 *
 * 1.vector扩容机制不一样，可以指定每次扩容的大小，如果没指定，那么每次扩大一倍
 * 2.vector是线程安全的修改操作都加了synchronized
 */
public class VectorTest {

    public static void main(String[] args) {
        Vector<Integer> objects = new Vector<>();
        objects.add(1);
    }

}
