package com.example.dp;


import java.util.HashMap;
import java.util.Map;

/**
 * 工厂设计模式
 *
 * 1.简单工厂设计模式
 *
 * @author wuxinxin
 */
public class FactoryTest {

    public static void main(String[] args) throws Exception{
        //测试简单工厂模式--不满足开关原则
        Product product = SimpleFactory.createProduct(SimpleFactory.ProductType.product1);
        Product product2 = SimpleFactory.createProduct(SimpleFactory.ProductType.product2);
        System.out.println(product);
        System.out.println(product2);

        //测试简单工厂模式--使用反射，满足开关原则

        SimpleFactory2.register("p1",Product1.class);
        SimpleFactory2.register("p2",Product2.class);

        Product p1 = SimpleFactory2.createProduct("p1");
        Product p2 = SimpleFactory2.createProduct("p2");
        System.out.println(p1);
        System.out.println(p2);

        //测试简单工厂模式--不用反射，满足开关原则

        SimpleFactory3.register("p1",new Product1());
        SimpleFactory3.register("p2",new Product2());

        Product p11 = SimpleFactory3.createProduct("p1");
        Product p22 = SimpleFactory3.createProduct("p2");
        System.out.println(p11);
        System.out.println(p22);
    }

}


/**
 * 简单工厂
 * 优点：1.客户端调用创建对象调用一个通用接口，减少耦合，符合依赖倒置原则
 *      2.这个类只负责对象的实例化，职责单一，符合单一职责原则
 * 缺点：1.当增加新的产品的时候，需要对代码进行修改，不符合开闭原则（对外扩展的开放，对修改的关闭->扩展需要修改代码）
 */
class SimpleFactory{

    public enum ProductType{
        /**
         * 可枚举的种类
         */
        product1,product2,product3
    }

    public static Product createProduct(ProductType productType){
        if(productType.equals(ProductType.product1)){
            return new Product1();
        }else if(productType.equals(ProductType.product2)){
            return new Product2();
        }else if(productType.equals(ProductType.product3)){
            return new Product3();
        }else {
            return null;
        }
    }

}

/**
 * 简单工厂--解决开闭原则问题
 * 使用反射机制,可以满足开关原则，但是反射效率较低，而且受权限控制
 *
 */
class SimpleFactory2{

    private static Map<String,Class> hashMap=new HashMap<String,Class>();

    public static void register(String key,Class value){
        hashMap.put(key,value);
    }

    public static Product createProduct(String key) throws IllegalAccessException, InstantiationException {
        Class aClass = hashMap.get(key);

        if(aClass==null){
            return null;
        }

        return (Product)aClass.newInstance();
    }
}


/**
 * 简单工厂--解决开闭原则问题
 * 不使用反射
 *
 */
class SimpleFactory3{

    private static Map<String,Product> hashMap=new HashMap<String,Product>();

    public static void register(String key,Product value){
        hashMap.put(key,value);
    }

    public static Product createProduct(String key){
       return hashMap.get(key).newInstance();
    }
}


interface Product{

     Product newInstance();

}

class Product1 implements Product{
    @Override
    public Product newInstance() {
        return new Product1();
    }

    /*private Product1() {
    }*/
}
class Product2 implements Product{

    @Override
    public Product newInstance() {
        return new Product2();
    }
}
class Product3 implements Product{

    @Override
    public Product newInstance() {
        return new Product3();
    }
}
