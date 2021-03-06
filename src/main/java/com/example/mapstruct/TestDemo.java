package com.example.mapstruct;

/**
 * @author: wuxinxin
 * @version: v1.0
 * @description: com.example.mapstruct
 * @date:2019/5/31
 */
public class TestDemo {

    public static void main(String[] args) {

        test02();
    }

    public static void test02(){
        UserBaseInfo userBaseInfo = new UserBaseInfo("1111", "123456");
        UserOtherInfo userOtherInfo = new UserOtherInfo("18770333013@163.com", "110", "江西抚州");
        User user = UserConverter.INSTANCE.domin2UserDto(userBaseInfo, userOtherInfo);
        System.out.println(user);
    }

    public static void test01(){
        Student student1 = new Student(1, 1, "aa","18770153333@163.com","123456");
        StudentDto studentDto = StudentConverter.INSTANCE.student2StudentDto(student1);
        System.out.println(studentDto);


        Student student2 = new Student(1, 1, "aa","18770153333@163.com","123456");
        StudentDto2 studentDto2 = StudentConverter.INSTANCE.student2StudentDto2(student2);
        System.out.println(studentDto2);


        Student student3 = new Student(1, 1, "aa","18770153333@163.com","123456");
        StudentDto3 studentDto3 = StudentConverter.INSTANCE.student2StudentDto3(student3);
        System.out.println(studentDto3);

    }

}
