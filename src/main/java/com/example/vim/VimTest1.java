package com.example.vim;

/**
 * Created by Administrator on 2019/7/23/023.
 */
public class VimTest1 {
    public static void main(String[] args) {

        /**
         * i: 插入当前光标前一个位置
         * a：插入当前光标后一个位置
         * o: 插入当前光标下一行
         *
         * I:插入当前行最前面
         * A:插入当前行最末尾
         * O:插入当前行上一行
         */
        //aaaibvaa

        /**
         * 模式：
         * 1.nomal 模式 用于多个模式切换
         * 2.command 模式 输入一些命令  wq   vs/sp
         * 3.insert 模式 进行编辑
         * 4.可视化 模式  块状选择，批量操作  v 选择单个字符   V 选择行  ctr+v选择块
         */
         //进行模式的说明
         //aaa //bbb

        /**
         * insert模式 删除
         * 1.ctr+h  删除一个字符
         * 2.ctr+w  删除一个单词
         * 3.ctr+u  删除当前行
         *
         */
        //my name is wxx
        //我的名字是wxx
        /**
         * nomal模式删除
         * 1.删除一个字符  x
         * 2.删除一个单词 daw
         * 3.删除一行
         */

        //aa bb cc
        /**
         * nomal模式查找
         * 1./ 正向查找  ?反向 查找
         * 2.n 下一个 N 上一个
         */
        //
        /**
         * nomal和insert模式方便切换
         * 1.转换nomal  ctr+[  直接切换为nomal模式
         * 2.转换insert gi 跳转到编辑处
         *
         *
         */
        //模式的切换
        /**
         * 移动大法：
         * 1.上下左右移动 j 下 k 上 l 右 h 左
         * 2.行间搜索字符  f+搜索字符  “;”下一个  ","上一个;  找到最后一个就不匹配
         * 3.移动到行首行尾  0行首 $ 行尾
         * 4.翻页  ctrl+u  ctrl+f ctrl+o返回上一个位置 gg 文件头 GG 文件尾部
         */
        //aa  bb ccc  dd
        //aa  bb ccc  ddb
    }
}
