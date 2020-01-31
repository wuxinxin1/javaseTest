package com.example.dp.prototype;

/**
 * @author wuxinxin
 *
 * 原型设计模式
 *
 * 1.当有线程安全问题时候可以用来解决线程安全问题
 * 2.单例模式不要实现Cloneable
 * 3.效率高，不调用构造函数，直接内存拷贝
 */
public class ProtoTypeTest {

    public static void main(String[] args) throws Exception {
        //测试原型模式
        Car car = new Car();
        System.out.println(car);
        System.out.println(car.clone());

        //测试使用了原型模式的单例
        //1.使用单例的时候，不要用原型模式，因为clone的时候是内存复制，不会调用构造方法，会破坏单例
        Car1 instance = Car1.getInstance();
        System.out.println(instance);
        System.out.println(instance.clone());
    }

}

class Car implements Cloneable{

    @Override
    public Car clone() throws CloneNotSupportedException {
        return (Car) super.clone();
    }
}

class Car1 implements Cloneable{

    private static Car1 car1=new Car1();

    private Car1() {
        System.out.println("car 1");
    }

    public static Car1  getInstance(){
        return car1;
    }

    @Override
    public Car1 clone() throws CloneNotSupportedException {
        return (Car1) super.clone();
    }
}
