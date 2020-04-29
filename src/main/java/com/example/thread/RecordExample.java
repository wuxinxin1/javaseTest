package com.example.thread;


/**
 * 测试多线程重排
 */

public class RecordExample {

    private  int  a=0;

    private Boolean flag=false;

    public void write(){
        flag=true;
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a=1;

    }

    public void red(){
        if(flag){
            int i=a*a;
            if(i==0){
                System.out.println("多线程重排"+i+"--"+a);
            }
        }
    }

    public static void main(String[] args) {
        while (true){
            RecordExample recordExample = new RecordExample();
            new Thread(new TT1(recordExample)).start();
            new Thread(new TT(recordExample)).start();
        }

    }

}

class TT implements Runnable{

    private RecordExample recordExample;

    public TT(RecordExample recordExample) {
        this.recordExample = recordExample;
    }

    @Override
    public void run() {
        recordExample.write();
    }
}

class TT1 implements Runnable{

    private RecordExample recordExample;

    public TT1(RecordExample recordExample) {
        this.recordExample = recordExample;
    }

    @Override
    public void run() {
        recordExample.red();
    }
}


