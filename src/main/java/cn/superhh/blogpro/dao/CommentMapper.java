package cn.superhh.blogpro.dao;

import cn.superhh.blogpro.pojo.Comment;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface CommentMapper {
    int save(Comment commment);
    List<Comment> list(HashMap hashmap);
    Integer getTotal(HashMap map);
    Integer delete(int[] ids);
    Integer update( Comment comment);
    Comment findByID(int id);
}
