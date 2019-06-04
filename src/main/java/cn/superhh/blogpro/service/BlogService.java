package cn.superhh.blogpro.service;

import cn.superhh.blogpro.pojo.Blog;

import java.util.HashMap;
import java.util.List;

public interface BlogService {
    int save(Blog blog);
    List<Blog> selectAll(HashMap map);
    int getTotal(HashMap map);
    int delete(int[] ids);
    Blog findById(int id);
    int update(Blog blog);
     List<Blog> countList();
     Blog getLast(Integer id);
     Blog getNext(Integer id);
    List<Blog> like(String title);
}
