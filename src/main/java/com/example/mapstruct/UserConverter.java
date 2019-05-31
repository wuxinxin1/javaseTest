package com.example.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 多个对象合并到一个对象
 * @author: wuxinxin
 * @version: v1.0
 * @description: com.example.mapstruct
 * @date:2019/5/31
 */
@Mapper
public interface UserConverter {
    UserConverter INSTANCE= Mappers.getMapper(UserConverter.class);
    User domin2UserDto(UserBaseInfo userBaseInfo,UserOtherInfo userOtherInfo);
}
