package cn.superhh.blogpro.service;

import cn.superhh.blogpro.pojo.Link;

import java.util.HashMap;
import java.util.List;

public interface LinkService {
    List<Link> linkList(HashMap map);
    Integer getTotal();
    int save(Link link);
    int update(Link link);
    int delete(String[] ids);
}
