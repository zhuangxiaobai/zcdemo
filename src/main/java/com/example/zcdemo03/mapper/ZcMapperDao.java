package com.example.zcdemo03.mapper;


import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ZcMapperDao {
    List<String> getName();
}
