package com.example.jvm.arthas;

import org.apache.kafka.common.protocol.types.Field;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private static List<String> users=new ArrayList<>();

    static {
        users.add("wxx");
        users.add("liujing");
        users.add("wxx2222");
        users.add("wxx3333");
    }

    public void addUser(){

        check();

        add();

        System.out.println("执行addUser");

    }

    private void check() {

        for (int i=0;i<3;i++){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("执行check");
    }


    private void add(){

        for (int i=0;i<5;i++){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("执行add");

    }

    public List delUser(Integer i){
        checkUser(i);
        del(i);
        System.out.println("删除user="+i+"成功");
        return users;
    }

    private Integer checkUser(Integer i){
        if(i>=users.size()){
            return -1;
        }

        return i;
    }

    public void del(int i){
        users.remove(i);
    }

}
