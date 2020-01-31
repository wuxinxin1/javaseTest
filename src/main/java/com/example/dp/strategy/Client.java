package com.example.dp.strategy;

/**
 * 策略模式测试
 * 1.定义一组算法，每个算法是独立的，增加新的策略，只要实现就好，符合开闭原则
 * 2.可以代替多重条件判断，但是如果策略太多，不利于维护
 */
public class Client {

    public static void main(String[] args) {
        Strategy1 strategy1 = new Strategy1();
        Strategy2 strategy2 = new Strategy2();

        StrategyContext.registerStrategy("s1",strategy1);
        StrategyContext.registerStrategy("s2",strategy2);

        StrategyContext.execute("s1");
        StrategyContext.execute("s2");
        StrategyContext.execute("s2");
    }
}
