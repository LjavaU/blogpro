package cn.superhh.blogpro.service.impl;

import cn.superhh.blogpro.dao.BlogTypeMapper;
import cn.superhh.blogpro.pojo.BlogType;
import cn.superhh.blogpro.service.BlogTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class BlogTypeServiceImpl implements BlogTypeService {
    @Autowired
    private BlogTypeMapper blogTypeMapper;
    public List<BlogType> selectAll(HashMap map){
        return blogTypeMapper.selectAll(map);
    }
    public int getTotal(){
        return blogTypeMapper.getTotal();
    }
    public int save(BlogType blogType){
        return blogTypeMapper.save(blogType);
    }
    public int update (BlogType blogType){
        return blogTypeMapper.update(blogType);
    }
    public int delete(int[] ids){
        return blogTypeMapper.delete(ids);
    }

    @Override
    public List<BlogType> countList() {
        return blogTypeMapper.countList();
    }
}
