package com.mybatis.mapper;

import com.mybatis.bean.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ClassName : UserMapper
 * @Author : yq
 * @Date: 2020-11-29
 * @Description :
 */
public interface UserMapper {
    @Select("select * from user where id = #{id} and name = #{name}")
    List<User> selectAll(Integer id, String name);

}
