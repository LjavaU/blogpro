package cn.superhh.blogpro.service;

import cn.superhh.blogpro.pojo.Comment;

import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.List;

public interface CommentService {
    int add(Comment comment);
    List<Comment> list(HashMap map);
    Integer getTotal(HashMap map);
    Integer delete(int[] ids);
    Integer review(Comment comment);
    Comment findByID(int id);
}
