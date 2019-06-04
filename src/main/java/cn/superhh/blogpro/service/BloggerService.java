package cn.superhh.blogpro.service;

import cn.superhh.blogpro.pojo.Blogger;

public interface BloggerService {
     Blogger login(String username,String password);
     Blogger find(String username);
     Integer update(Blogger blogger);
    Blogger findById(int id);
      int modifyPassword(int id,String newPassword);

}
