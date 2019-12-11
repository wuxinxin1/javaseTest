package com.example.thread;

/**
 * @author wuxinxin
 * 展示内存可见性问题
 */
public class VolatileTest {
    public static  boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        MyTask task = new MyTask();
        new Thread(task).start();

        while (true){
            //读取值
            //直接用线程缓存的值 不会去主内存去拉取变量
            if (flag){
                System.out.println("=============");
                break;
            }
            //System.out.println("aa");
        }
    }

}



class MyTask implements Runnable{

    // 修改值
    @Override
    public void run() {

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        VolatileTest.flag = true;
        System.out.println("flag=" + VolatileTest.flag);
    }

}
