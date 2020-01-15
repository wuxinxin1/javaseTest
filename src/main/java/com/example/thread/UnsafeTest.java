package com.example.thread;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeTest {

    public static void main(String[] args) throws Exception {

        //获取unsafe对象
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);

        //操作对象
        M m = new M(100, "wxx");

        //获取内存地址
        long idAdd = unsafe.objectFieldOffset(M.class.getDeclaredField("id"));
        System.out.println("偏移量:"+idAdd);

        //获取值
        int v = unsafe.getInt(m, idAdd);
        System.out.println(v);

    }


    static class M{
        public M(int id, String name) {
            this.id = id;
            this.name = name;
        }

        private int id;
        private String name;

    }

}
