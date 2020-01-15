package com.example.others;

import org.apache.kafka.common.protocol.types.Field;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuxinxin
 * 范型测试
 */
public class FXTest {

    public static void main(String[] args) {
        /*List arrayList = new ArrayList();
        arrayList.add("aaaa");
        arrayList.add(100);

        for(int i = 0; i< arrayList.size();i++){
            String item = (String)arrayList.get(i);
            System.out.println("泛型测试:"+"item = " + item);
        }*/


       /* List<String> arrayList = new ArrayList();
        arrayList.add("aaaa");
        arrayList.add(100);

        for(int i = 0; i< arrayList.size();i++){
            String item = (String)arrayList.get(i);
            System.out.println("泛型测试:"+"item = " + item);
        }*/



        /*List<String> stringArrayList = new ArrayList();
        List<Integer> integerArrayList = new ArrayList();

        Class classStringArrayList = stringArrayList.getClass();
        Class classIntegerArrayList = integerArrayList.getClass();

        if(classStringArrayList.equals(classIntegerArrayList)){
            System.out.println("泛型测试"+"类型相同"+classIntegerArrayList);
        }*/

        //后面的<>可以在编译阶段检查类型,否则在运行期才会抛出类型转换异常
        C1<Integer> integerC1 = new C1<>(3);
        C1<Boolean> integerC2 = new C1<>(false);
        //C1<String> integerC3 = new C1(1);
        //System.out.println(integerC1.getA());
        //System.out.println(integerC2.getA());
        //System.out.println(integerC3.getA());

        if(integerC1 instanceof C1){
            //System.out.println("是一个类型");
        }

        //不能对确切的泛型类型使用instanceof操作。如下面的操作是非法的，编译时会出错
        /*if(integerC1 instanceof C1<Integer>){
            System.out.println("是一个类型");
        }*/


        /*NumberGenerator dataGenerator = new NumberGenerator(111);

        IntegerGenerator integerGenerator = new IntegerGenerator(222);

        StringGenerator stringGenerator = new StringGenerator("3333asa");

        show(dataGenerator);

        show(integerGenerator);

        show(stringGenerator);


        //泛型类，是在实例化类的时候指明泛型的具体类型；泛型方法，是在调用方法的时候指明泛型的具体类型 。

        //实例化一个泛型类，在实例化的时候指定类型<>在输入时检测<?>在获取时候检测
        C1 c1Instance1 = new C1(111);
        C1<?> c1Instance2 = new C1<>("aaaaa");
        System.out.println(c1Instance1.getA());
        System.out.println(c1Instance2.getA());*/


        //演示泛型方法
       /* Factory<String> factory = new Factory(); //泛型类和泛型方法的泛型是独立的
        Car car = factory.product(new Car());
        Animal animal = factory.product1(new Animal());
        System.out.println(animal);
        System.out.println(car);
        factory.print(1,2,"3",false);
        Factory.print1(1);
        Factory.print1(false);
        Factory.show(1);*/
        //Factory.show("aaaa");//不满足泛型边界 <T extends Number>

        //演示泛型类类型的边界问题
        C2 c2 = new C2(1);
        //C2 aaa = new C2("aaa");//不满足泛型边界
        C2 c21 = new C2(1.5);


    }

   /* public static void show(Generator<Number> numberGenerator){
        System.out.println(numberGenerator.next());
    }*/

   //使用通配符解决同一个接口实现类问题
   public static void show(Generator<?> numberGenerator){
        System.out.println(numberGenerator.next());
    }

}

class StringGenerator implements Generator<String>{

    private String string;

    public StringGenerator(String string) {
        this.string=string;
    }

    @Override
    public String next() {
        return string;
    }
}

class IntegerGenerator implements Generator<Integer>{

    private Integer integer;

    public IntegerGenerator(Integer integer) {
        this.integer=integer;
    }

    @Override
    public Integer next() {
        return integer;
    }
}

class NumberGenerator implements Generator<Number>{

    private Number number;

    public NumberGenerator(Number t) {
        this.number=t;
    }

    @Override
    public Number next() {
        return number;
    }
}

//定义一个泛型接口
interface Generator<T> {
    public T next();
}

//定义泛型类，在实例化的时候指定具体类型

class C1<T>{
    private T a;

    public C1(T a) {
        this.a = a;
    }

    public T getA() {
        return a;
    }
}

//定义泛型方法
class Factory<T>{

    //这个方法不是泛型方法，只是使用了泛型参数
    /*public T  product(T e){
        return e;
    }*/

    //这个方法不是泛型方法,并且使用泛型参数不当
   /* public E product(E e){
        return e;
    }*/

   //方法类型和类泛型是独立的
   public <E> E product(E e){
       return e;
   }

    //方法类型和类泛型是独立的
    public <T> T product1(T e){
        return e;
    }

    //泛型类型可变参数
    public <T> void print(T...args){
        for (T e:args) {
            System.out.println(e);
        }
    }

    //静态方法不可以使用泛型类的泛型，解决方案就是直接声明为泛型方法
    public static <T> void print1(T t){
        System.out.println(t);
    }

    public static <T extends Number> void show(T t){
        System.out.println(t);
    }
}

class C2<T extends Number>{

    private T t;

    public C2(T t) {
        this.t = t;
    }
}

class Car{

}

class Animal{

}
