package cn.superhh.blogpro.service.impl;

import cn.superhh.blogpro.dao.LinkMapper;
import cn.superhh.blogpro.pojo.Link;
import cn.superhh.blogpro.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
@Service
public class LinkServiceImpl  implements LinkService {
     @Autowired
    private LinkMapper linkMapper;
    @Override
    public List<Link> linkList(HashMap map) {
        return linkMapper.selectAll(map);
    }

    @Override
    public Integer getTotal() {
        return linkMapper.getTotal();
    }

    @Override
    public int save(Link link) {
        return linkMapper.save(link);
    }

    @Override
    public int update(Link link) {
        return linkMapper.update(link);
    }

    @Override
    public int delete(String[] ids) {
        return linkMapper.delete(ids);
    }
}
