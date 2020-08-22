package com.example.jvm;


/**
 * 查看jvm使用的垃圾回收
 *
 * -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
 *
 * 年轻代： parallel gc
 */
public class Hello {

    private static int  _1M=1024*1024;

    public static void main(String[] args) throws InterruptedException {
        //test1();
        test2();

        //Thread.sleep(100000000);
    }


    /**
     * 测试内存分配策略
     *
     * -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
     */
    public static void test1(){

        byte[] b1=new byte[2*_1M];
        byte[] b2=new byte[2*_1M];
        byte[] b3=new byte[2*_1M];
        byte[] b4=new byte[4*_1M];

    }

    /**
     *
     *   byte[] b1=new byte[_1M*4];
     *         System.out.println("111");
     *         byte[] b2=new byte[_1M*2];
     *         System.out.println("222");
     *
     * 测试对象多大年龄会进入老年代
     *  1.一开始占用2596k
     *  2.创建了一个数组用了 4096 --->已经使用6692k，eden剩余，1500k
     *  3.创建一个数组用了 2048，---->eden需要内存6692+2048，明显eden内存不够，发生minor gc一次
     *                        ----->发现survivor只有1024k,而总存活对象为6692k（担保分配到老年代4104k，survivor分配840k，回收了1584k，eden区留了163k）
     *                        ----->2048k对象经过gc就可以直接分配到eden了
     *
     *
     * 0.151: [GC (Allocation Failure) [PSYoungGen: 6528K->849K(9216K)] 6528K->4953K(19456K), 0.0033519 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
     * Heap
     *  PSYoungGen      total 9216K, used 3062K [0x00000007bf600000, 0x00000007c0000000, 0x00000007c0000000)
     *   eden space 8192K, 27% used [0x00000007bf600000,0x00000007bf8290f0,0x00000007bfe00000)
     *   from space 1024K, 82% used [0x00000007bfe00000,0x00000007bfed4788,0x00000007bff00000)
     *   to   space 1024K, 0% used [0x00000007bff00000,0x00000007bff00000,0x00000007c0000000)
     *  ParOldGen       total 10240K, used 4104K [0x00000007bec00000, 0x00000007bf600000, 0x00000007bf600000)
     *   object space 10240K, 40% used [0x00000007bec00000,0x00000007bf002010,0x00000007bf600000)
     *  Metaspace       used 2957K, capacity 4496K, committed 4864K, reserved 1056768K
     *   class space    used 321K, capacity 388K, committed 512K, reserved 1048576K
     *
     *
     *
     */
    public static void test2(){

        byte[] b1=new byte[_1M*4];
        byte[] b2=new byte[_1M*2];

      /*  b2=null;
        System.out.println("333");
        byte[] b5=new byte[_1M*1];
        System.out.println("444");*/
    }
}
