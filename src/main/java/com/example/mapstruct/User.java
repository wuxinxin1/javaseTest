package com.example.mapstruct;

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
public class User {
    private String name;
    private String password;
    private String email;
    private String phoneNo;
    private String address;
}
