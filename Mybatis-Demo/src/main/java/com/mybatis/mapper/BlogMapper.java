package com.mybatis.mapper;

import com.mybatis.bean.Blog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName : BlogMapper
 * @Author : yq
 * @Date: 2020-11-29
 * @Description :
 */
public interface BlogMapper {

    Blog selectBlog(Integer id);

    List<Blog> selectAll();

    void insert(@Param("blog") Blog blog);


}
