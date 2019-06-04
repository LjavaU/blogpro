package cn.superhh.blogpro.service.impl;

import cn.superhh.blogpro.dao.BloggerMapper;
import cn.superhh.blogpro.pojo.Blogger;
import cn.superhh.blogpro.service.BloggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BloggerServiceImpl implements BloggerService {
    @Autowired
    private BloggerMapper bloggerMapper;
    public Blogger login(String username, String password){
         return bloggerMapper.login(username,password);

    }
    public Blogger find(String username){
        return bloggerMapper.find(username);
    }
    public Integer update(Blogger blogger){
        return  bloggerMapper.update(blogger);
    }
    public  Blogger findById(int id){
        return bloggerMapper.findById(id);
    }

    @Override
    public int modifyPassword(int id, String newPassword) {
        return bloggerMapper.modifyPassword(id,newPassword);
    }
}
