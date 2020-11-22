package com.mybatis.mapper;

import com.mybatis.bean.Blog;

import java.util.List;

/**
 * @ClassName : BlogMapper
 * @Author : yq
 * @Date: 2020-11-15
 * @Description :
 */
public interface BlogMapper {

    Blog selectBlog(Integer id);

    List<Blog> selectAll();

}
