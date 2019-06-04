package cn.superhh.blogpro.dao;

import cn.superhh.blogpro.pojo.BlogType;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface BlogTypeMapper {
    public List<BlogType> selectAll(HashMap map);
    public int getTotal();
    public int save(BlogType blogType);
    public  int  update(BlogType blogType);
    public int delete(int[] ids);
      BlogType findById(int id);
      List<BlogType> countList();
}
