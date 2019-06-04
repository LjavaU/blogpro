package cn.superhh.blogpro.controller.admin;

import cn.superhh.blogpro.pojo.BlogType;
import cn.superhh.blogpro.pojo.PageBean;
import cn.superhh.blogpro.pojo.Result;
import cn.superhh.blogpro.service.BlogTypeService;
import cn.superhh.blogpro.service.impl.BlogTypeServiceImpl;
import cn.superhh.blogpro.util.ResponseUtil;
import net.sf.json.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/blogType")
public class BlogTypeController {
    @Autowired
    private BlogTypeService blogTypeService;

    /**
     * 显示博客类型信息列表
     * @param page
     * @param rows
     * @param response
     * @return
     */
    @RequestMapping("/list")
    public String list(String page, String rows, HttpServletResponse response){
        PageBean pageBean=new PageBean(((Integer.parseInt(page))-1)*10,Integer.parseInt(rows));
        HashMap<String,Object> map=new HashMap<String,Object>();
        map.put("start", Integer.valueOf(pageBean.getStart()));
        map.put("size", Integer.valueOf(pageBean.getPageSize()));
        List<BlogType> blogTypeList=blogTypeService.selectAll(map);
        System.out.println(blogTypeList.size());
        int  total=blogTypeService.getTotal();
        JSONObject result=new JSONObject();
        JSONArray jsonArray=JSONArray.fromObject(blogTypeList);
        result.put("rows", jsonArray); //每页的记录数
        result.put("total", total);    //总记录数
        try {
            ResponseUtil.write(response, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 插入博客类型信息列表
     * @param blogType
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public Result add(BlogType blogType){
          int n=   blogTypeService.save(blogType);
        return getResult(n);
    }

    /**
     * 更新博客类型信息列表
     * @param blogType
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public Result update(BlogType blogType){
        int n=blogTypeService.update(blogType);
        return getResult(n);
    }


    /**
     * 删除博客类型信息列表
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(int[] ids){
        int n=0;
        try {
             n = blogTypeService.delete(ids);
            return getResult(n);
        }
        catch (Exception e){
            return getResult(n);
        }
    }

    private Result getResult(int n) {
        if(n>0){
            Result result=new Result();
            result.setSuccess(true);
            return result;
        }
        return  new Result(false);
    }
    @RequestMapping("/selectAll")
    public String select(){
             List<BlogType> result=blogTypeService.selectAll(null);
              // request.getServletContext().setAttribute("blogTypeCountList",result);
                  return "admin/writeBlog";

    }
}
