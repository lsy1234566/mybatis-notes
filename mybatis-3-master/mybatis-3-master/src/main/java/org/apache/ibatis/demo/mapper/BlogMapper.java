package org.apache.ibatis.demo.mapper;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.demo.model.Blog;

@Mapper
//@CacheNamespace
public interface BlogMapper {

  Blog selectBlog(int id);
}
