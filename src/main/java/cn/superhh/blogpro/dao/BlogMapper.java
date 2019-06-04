package cn.superhh.blogpro.dao;

import cn.superhh.blogpro.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface BlogMapper {
      int save(Blog blog);
      List<Blog> selectAll(HashMap  map);
      int getTotal(HashMap map);
      int delete(int[] ids);
      Blog findById(int id);
      int update(Blog blog);
      List<Blog> countList();
      Blog getLast(Integer id);
      Blog getNext(Integer id);
      List<Blog> like(String title);
}
