package cn.superhh.blogpro.service;

import cn.superhh.blogpro.pojo.BlogType;

import java.util.HashMap;
import java.util.List;

public interface BlogTypeService {
    List<BlogType> selectAll(HashMap map);
    int getTotal();
    int save(BlogType blogType);
    int update(BlogType blogType);
    int delete(int[] ids);
    List<BlogType> countList();
}
