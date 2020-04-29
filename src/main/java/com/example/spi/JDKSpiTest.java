package com.example.spi;

import java.util.ServiceLoader;

/**
 *  jdk的spi机制测试
 *  1.在META-INF/services/路径下加载需要类型的指定文件，文件名为需要加载类型的全限定名
 *  2.ServiceLoader调用load方法，加载文件指定实现，然后实例化这些实现类
 * @author wuxinxin
 */
public class JDKSpiTest {

    public static void main(String[] args) {

        ServiceLoader<Animal> load = ServiceLoader.load(Animal.class);

        load.forEach(Animal::say);
    }

}
