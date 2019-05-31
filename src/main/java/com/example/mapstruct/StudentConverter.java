package com.example.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 *  一个Mapping对应一个转换器类型
 * @author: wuxinxin
 * @version: v1.0
 * @description: com.example.mapstruct
 * @date:2019/5/31
 */
@Mapper
public interface StudentConverter {
    StudentConverter INSTANCE= Mappers.getMapper(StudentConverter.class);

    @Mappings({
            @Mapping(source="id",target="stuId"),
            @Mapping(source="age",target="stuAge"),
            @Mapping(source="name",target="stuName")
    })

    StudentDto student2StudentDto(Student student);


    @Mappings({
            @Mapping(source="name",target="name2")
    })
    StudentDto2 student2StudentDto2(Student student);


    @Mappings({
            @Mapping(source="id",target="id3"),
            @Mapping(source="age",target="age3"),
            @Mapping(source="name",target="name3")
    })
    StudentDto3 student2StudentDto3(Student student);
}
