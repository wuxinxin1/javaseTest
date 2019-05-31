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
public class StudentDto{
    private int stuId;
    private int stuAge;
    private String firstName;
    private String lastName;
    private String stuName;
    private String email;
}
