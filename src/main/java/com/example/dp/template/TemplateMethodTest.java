package com.example.dp.template;


import java.util.List;

/**
 * @author wuxinxin
 *
 * 测试模板方法
 *
 * 1.把不变的放在父类中，扩展放在子类
 * 2.扩展功能增加新的实现，不需要修改代码，符合开闭原则
 */
public class TemplateMethodTest {

    public static void main(String[] args) {
        Bz bz = new Bz();
        bz.run();

        Bw bw = new Bw();
        bw.run();
    }

}

abstract class CarModel{

    protected abstract void start();

    protected abstract void stop();

    protected abstract void voice();

    public void run(){
        //实现模板方法的主流程控制（算法）
        this.start();
        this.stop();
        this.voice();
    }
}

class Bz extends CarModel {

    @Override
    public void start() {
        System.out.println("BZ start");
    }

    @Override
    public void stop() {
        System.out.println("BZ stop");
    }

    @Override
    public void voice() {
        System.out.println("BZ voice");
    }
}

class Bw extends CarModel {

    @Override
    public void start() {
        System.out.println("BW start");
    }

    @Override
    public void stop() {
        System.out.println("BW stop");
    }

    @Override
    public void voice() {
        System.out.println("BW voice");
    }
}

