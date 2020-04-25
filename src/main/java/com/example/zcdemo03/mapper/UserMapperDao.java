package com.example.zcdemo03.mapper;

import com.example.zcdemo03.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapperDao {



    List<User> selectAllUser();
  //  int countByExample(PmsBrandExample example);

//    int deleteByExample(PmsBrandExample example);

    int deleteByPrimaryKey(Long id);

  //  int insert(User user);

    int insertSelective(User user);

//    List<PmsBrand> selectByExampleWithBLOBs(PmsBrandExample example);

//    List<PmsBrand> selectByExample(PmsBrandExample example);

     User selectByPrimaryKey(Long id);

//    int updateByExampleSelective(@Param("record") PmsBrand record, @Param("example") PmsBrandExample example);
//
//    int updateByExampleWithBLOBs(@Param("record") PmsBrand record, @Param("example") PmsBrandExample example);
//
//    int updateByExample(@Param("record") PmsBrand record, @Param("example") PmsBrandExample example);
//
    int updateByPrimaryKeySelective(User user);
//
//    int updateByPrimaryKeyWithBLOBs(PmsBrand record);
//
//    int updateByPrimaryKey(PmsBrand record);
}
