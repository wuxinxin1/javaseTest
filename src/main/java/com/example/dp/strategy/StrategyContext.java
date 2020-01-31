package com.example.dp.strategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StrategyContext {

    private static Map<String,Strategy> stringStrategyMap=new HashMap<>();


    public static void registerStrategy(String key,Strategy strategy){
        stringStrategyMap.put(key,strategy);
    }

    public static void execute(String key){
        stringStrategyMap.get(key).operate();
    }

}
