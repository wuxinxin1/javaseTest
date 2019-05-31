package com.example.mapstruct;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: wuxinxin
 * @version: v1.0
 * @description: com.example.mapstruct
 * @date:2019/5/31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private int id;
    private int age;
    private String name;
    private String email;
    private String password;
}
