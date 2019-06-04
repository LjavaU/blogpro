package cn.superhh.blogpro.service.impl;

import cn.superhh.blogpro.BlogproApplication;
import cn.superhh.blogpro.dao.BlogMapper;
import cn.superhh.blogpro.pojo.Blog;
import cn.superhh.blogpro.pojo.BlogType;
import cn.superhh.blogpro.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Blob;
import java.util.HashMap;
import java.util.List;

@Service
public class BlogServiceImpl  implements BlogService {
    @Autowired
    private BlogMapper blogMapper;
    public int save(Blog blog){
       return  blogMapper.save(blog);
    }

    @Override
    public List<Blog> selectAll(HashMap map) {
        return blogMapper.selectAll(map);
    }

    @Override
    public int getTotal(HashMap map) {
        return blogMapper.getTotal(map);
    }

    @Override
    public int delete(int[] ids) {
        return blogMapper.delete(ids);
    }

    @Override
    public Blog findById(int id) {
        return blogMapper.findById(id);
    }

    @Override
    public int update(Blog blog) {
        return blogMapper.update(blog);
    }

    @Override
    public List<Blog> countList() {
        return  blogMapper.countList();
    }

    @Override
    public Blog getLast(Integer id) {
        return blogMapper.getLast( id);
    }

    @Override
    public Blog getNext(Integer id) {
        return blogMapper.getNext(id);
    }

    @Override
    public List<Blog> like(String title) {
        return  blogMapper.like(title);
    }
}
