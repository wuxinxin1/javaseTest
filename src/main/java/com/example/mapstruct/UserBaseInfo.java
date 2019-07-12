package com.example.mapstruct;

import lombok.AllArgsConstructor;
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
public class UserBaseInfo {
    private String name;
    private String password;
}
