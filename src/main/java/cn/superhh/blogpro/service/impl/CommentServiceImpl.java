package cn.superhh.blogpro.service.impl;

import cn.superhh.blogpro.dao.CommentMapper;
import cn.superhh.blogpro.pojo.Comment;
import cn.superhh.blogpro.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public int add(Comment comment) {
        return commentMapper.save(comment);
    }

    @Override
    public List<Comment> list(HashMap map) {
        return commentMapper.list(map);
    }

    @Override
    public Integer getTotal(HashMap map) {
        return commentMapper.getTotal(map);
    }

    @Override
    public Integer delete(int[] ids) {
        return commentMapper.delete(ids);
    }

    @Override
    public Integer review(Comment comment) {
        return commentMapper.update(comment);
    }

    @Override
    public Comment findByID(int id) {
        return commentMapper.findByID(id);
    }
}
