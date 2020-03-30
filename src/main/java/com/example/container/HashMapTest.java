package com.example.container;

import java.util.*;


/**
 * HashMap测试
 *
 * @author wuxinxin
 */
public class HashMapTest {

    public static void main(String[] args) throws Exception{

        HashMap<Integer, Object> objectObjectHashMap = new HashMap<>();

        //1.hashMap第一次初始化的大小是16 threshold也就是阀值 16*0.75=12
        //2.每个桶第一次put的时候赋值就好了,如果是这个桶已经有值了，那么判断是进行树插入还是链表插入
        for(int i=1;i<100;i++){
            //测试hash分布不均匀的情况
            if(i%2==0) {
                objectObjectHashMap.put(i, i);
            }
        }

        //计算hash
        //System.out.println(new Integer(1).hashCode());

        System.out.println(objectObjectHashMap.get("a"));
    }

}
