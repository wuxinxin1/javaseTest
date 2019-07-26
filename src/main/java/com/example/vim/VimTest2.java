package com.example.vim;

/**
 * Created by Administrator on 2019/7/25/025.
 */
public class VimTest2 {
    /**
     * 批量搜索/替换
     * :[%/m,n]  s/pattern/target/[g/c/n]
     * : range   s/pattern/target/[g/c/n]
     *  1.range 有两种方式 %表示全部 m,n表示行范围
     *  2.pattern表示要匹配的正则表达式
     *  3.target表示要替换的值
     *  4。flag表示要执行的操作类型 g 全局操作 c 全局操作但是要每次做确认动作 n表示只返回匹配行数，不执行替换
     */
    public void m1(){
        System.out.println("mm 1");
    }

    public void m2(){
        System.out.println("mm 2");
    }

    /**
     * 使用 buffer切换编辑多个缓冲区(buffer)
     * 1. :ls 列举当前缓冲区
     * 2. :b n 切换缓冲区 tab键补全
     *
     * 分屏查看多个buffer区(window)
     * 1. vs
     * 2.sp
     * 3.ctr+w+w 切换
     */


}
