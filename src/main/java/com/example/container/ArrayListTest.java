package com.example.container;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * @author wuxinxin
 *
 * ArrayList分析
 */
public class ArrayListTest {

    public static void main(String[] args) {

        //arraylist的三个构造方法
        //1.无参构造，分配一个空数组
        //2.给定一个初始大小的值，创建一个初始大小的数组
        //3.使用一个子类泛型的list初始化
        /*ArrayList<Integer> integers = new ArrayList<>();
        integers.add(10);

        ArrayList<Integer> objects = new ArrayList<Integer>(10);

        ArrayList<Number> integers1 = new ArrayList<Number>(integers);

        System.out.println(integers1);*/

        //分析修改方法,允许为null
        /*ArrayList<Integer> integers = new ArrayList<>();
        //直接添加到数组末尾
        integers.add(3);
        //指定元素的下标位置进行插入,这个需要将数组部分移动，时间复杂度O(n)
        integers.add(1,5);
        //删除指定下标的元素,这个需要将数组部分移动，时间复杂度O(n)
        integers.remove(0);
        //删除指定的元素，分为两种删除 1 如果为null,使用null的删除方式 2 如果不是null,那么调用equals方法删除
        //直接遍历数组进行删除，所以在fastRemove 不需要检测下标
        integers.remove(null);
        //清空元素，其实只是将元素都置为null,分配的空间没变
        integers.clear();*/
        //测试迭代器
        //1.list每次修改都会使修改次数+1（相当于版本号）
        //2.在遍历的时候不允许直接调用remove方法直接删除，因为会进行版本检测；但是可以调用ListIterator的remove方法进行删除，会自动版本做同步
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(3);
        integers.add(6);
        integers.get(1);

        ListIterator<Integer> integerListIterator = integers.listIterator();

        Iterator<Integer> iterator = integers.iterator();

        while (integerListIterator.hasNext()){
            //integers.add(3);
            System.out.println(integerListIterator.next());
        }

        integerListIterator.remove();
        //integerListIterator.remove();
    }

}
