package com.example.dp.builder;


import java.util.ArrayList;
import java.util.List;

/**
 * @author wuxinxin
 * 构建者模式测试
 *
 * 运用场景
 * 1.构建复杂对象（lombok）
 * 2.构建的顺序对对象有影响，不同的构建顺序将影响对象的功能（例子属于这种）
 */
public class BuilderTest {

    public static void main(String[] args) {

        //不使用构造者，由用户自己创建对象
        ArrayList<String> strings = new ArrayList<>();
        strings.add("start");
        strings.add("stop");
        strings.add("voice");

        /*Bz bz = new Bz();
        bz.setList(strings);
        bz.run();

        Bw bw = new Bw();
        bw.setList(strings);
        bw.run();*/

        //使用构建者，构建者创建对象
        CarModel carModel = new BzBuilder().setSeq(strings).build();
        carModel.run();
    }

}

abstract class CarBuilder{

    public abstract CarBuilder setSeq(ArrayList<String> strings);

    public abstract CarModel build();

}

class BzBuilder extends CarBuilder{

    private Bz bz=new Bz();

    @Override
    public CarBuilder setSeq(ArrayList<String> strings) {
        bz.setList(strings);
        return this;
    }

    @Override
    public CarModel build() {
        return bz;
    }
}

class BwBuilder extends CarBuilder{

    private Bw bw=new Bw();

    @Override
    public CarBuilder setSeq(ArrayList<String> strings) {
        bw.setList(strings);
        return this;
    }

    @Override
    public CarModel build() {
        return bw;
    }
}


abstract class CarModel{

    private List<String> list;

    public abstract void start();

    public abstract void stop();

    public abstract void voice();

    public void run(){
        for (String s:list){
            if(s.equals("start")){
                this.start();
            }else if(s.equals("stop")){
                this.stop();
            }else if(s.equals("voice")){
                this.voice();
            }
        }
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}

class Bz extends CarModel{

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

class Bw extends CarModel{

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


