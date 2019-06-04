package cn.superhh.blogpro.controller.admin;

import cn.superhh.blogpro.pojo.Link;
import cn.superhh.blogpro.pojo.PageBean;
import cn.superhh.blogpro.pojo.Result;
import cn.superhh.blogpro.service.LinkService;
import cn.superhh.blogpro.util.ResponseUtil;
import com.alibaba.fastjson.JSONObject;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/link")
public class LinkController {
              @Autowired
    private   LinkService linkService;
 @RequestMapping("/list")
 public  String linkList(String page, String rows, HttpServletResponse response){
     PageBean pageBean=new PageBean(((Integer.parseInt(page))-1)*10,Integer.parseInt(rows));
     HashMap<String,Object> map=new HashMap<String,Object>();
     map.put("start", Integer.valueOf(pageBean.getStart()));
     map.put("size", Integer.valueOf(pageBean.getPageSize()));
     List<Link> linkList=linkService.linkList(map);
     int  total=linkService.getTotal();
     JSONObject result=new JSONObject();
     JSONArray jsonArray=JSONArray.fromObject(linkList);
     result.put("rows", jsonArray); //每页的记录数
     result.put("total", total);    //总记录数
     try {
         ResponseUtil.write(response, result);
     } catch (Exception e) {
         e.printStackTrace();
     }
     return null;
 }
 @RequestMapping("/save")
 @ResponseBody
 public Result save(Link link,Integer id){
     int n;
     if(id==null) {
        n = linkService.save(link);
        return getResult(n);
     }
     else{
        n= linkService.update(link);
          return getResult(n);
     }
     }
     @RequestMapping("/delete")
     @ResponseBody
     public Result delete(String[] ids){
             int n=linkService.delete(ids);
           return   getResult(n);
     }

     public Result getResult(int n){
         if (n > 0) {
             return new Result(true);
         }
         return new Result(false);
     }
 }

