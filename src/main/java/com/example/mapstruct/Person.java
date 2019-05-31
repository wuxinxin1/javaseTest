package com.example.mapstruct;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: wuxinxin
 * @version: v1.0
 * @description: com.example.mapstruct
 * @date:2019/5/31
 */
@Data
@NoArgsConstructor
public class Person {
    private Long id;
    private String name;
    private String email;
    private Date birthday;
    private User user;
}

@Data
class User{
    private Integer age;
}
