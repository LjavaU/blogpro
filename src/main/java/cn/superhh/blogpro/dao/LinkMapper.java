package cn.superhh.blogpro.dao;

import cn.superhh.blogpro.pojo.Link;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface LinkMapper {
    List<Link> selectAll(HashMap map);
    Integer getTotal();
    int save(Link link);
    int update(Link link);
    int delete(String[] ids);
}
