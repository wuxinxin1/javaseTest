package com.example.juc;

import org.junit.Test;

/**
 * Created by Administrator on 2019/3/23/023.
 */
public class volatileTest {
    public static void main(String[] args) {
        Demo demo = new Demo();

        Thread thread = new Thread(demo);

        thread.start();


        while (true){
            if(demo.isFlag()){
                break;
            }
        }


    }
}

class Demo implements Runnable{

    private boolean flag=false;

    @Override
    public void run() {

        try {
            Thread.sleep(200);
            flag=true;
            System.out.println("flag-----"+flag);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
