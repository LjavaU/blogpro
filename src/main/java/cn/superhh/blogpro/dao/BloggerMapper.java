package cn.superhh.blogpro.dao;

import cn.superhh.blogpro.pojo.Blogger;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BloggerMapper {
    public Blogger login(@Param("userName") String username, @Param("passWord") String password);
    public Blogger find(String username);
    public Integer update(Blogger blogger);
    public Blogger findById(int id);
    Integer modifyPassword(@Param("id") int id,@Param("newPassword") String newPassword);
}
