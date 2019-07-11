package com.example.others;

/**
 * 对于try cache 的研究
 * Created by Administrator on 2019/4/4/004.
 */
public class TryCatchTest {

    public static void main(String[] args) {

        new TryCatchTest().test();
    }

    private void test(){
        try {
            System.out.println("3333");
            int i=1/0;
            //System.exit(0);
            return;
        }catch (Exception e){
            System.out.println("1111");
        }finally {
            System.out.println("2222");
        }
        System.out.println("4444");
    }

}
