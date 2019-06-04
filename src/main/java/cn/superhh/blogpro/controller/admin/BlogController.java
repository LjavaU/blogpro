package cn.superhh.blogpro.controller.admin;

import cn.superhh.blogpro.lucene.BlogIndex;
import cn.superhh.blogpro.lucene.BlogIndex1;
import cn.superhh.blogpro.pojo.Blog;
import cn.superhh.blogpro.pojo.BlogType;
import cn.superhh.blogpro.pojo.PageBean;
import cn.superhh.blogpro.pojo.Result;
import cn.superhh.blogpro.service.BlogService;
import cn.superhh.blogpro.service.BlogTypeService;
import cn.superhh.blogpro.util.DateJsonValueProcessor;
import cn.superhh.blogpro.util.ResponseUtil;
import cn.superhh.blogpro.util.StringUtil;
import net.sf.json.JSONObject;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private BlogTypeService blogTypeService;
    @Autowired
    private BlogIndex blogIndex;
    /**
     * 写/修改博客
     *
     * @param blog
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public Result save(Blog blog) {
        int n = 0;
        try {
            if (blog.getId() == null) {

                LocalDateTime dateTime = LocalDateTime.now();

                blog.setReleaseDate(dateTime);
                n = blogService.save(blog);
                System.out.println(blog);
                if (n > 0) {
                    return new Result(true);
                }
                return null;
            } else {
                n = blogService.update(blog);
                if (n > 0) {
                    return new Result(true);
                }
                return null;
            }
        }
        catch (Exception e){
            return new Result(false);
        }
    }






    /**
     * 返回博客信息列表
     *
     * @param page
     * @param rows
     * @param response
     * @return
     */
    @RequestMapping("/list")
    public String list(String page, String rows, Blog blog, HttpServletResponse response) {

        PageBean pageBean = new PageBean(((Integer.parseInt(page)) - 1) * 10, Integer.parseInt(rows));
        HashMap<String, Object> map = new HashMap();
        map.put("title", StringUtil.formatLike(blog.getTitle()));
        map.put("start", Integer.valueOf(pageBean.getStart()));
        map.put("size", Integer.valueOf(pageBean.getPageSize()));
        List<Blog> blogList = blogService.selectAll(map);
        int total = blogService.getTotal(map);
        //格式化日期
        JSONObject result = new JSONObject();
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(LocalDateTime.class, new DateJsonValueProcessor("yyyy-MM-dd "));
        //对象转换为json串
        JSONArray jsonArray = JSONArray.fromObject(blogList, jsonConfig);
        result.put("rows", jsonArray);
        result.put("total", total);
        try {
            ResponseUtil.write(response, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;


    }

    /**
     * 删除博客信息
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(int[] ids) {
        int n = 0;
        try {
                n = blogService.delete(ids);

            if (n > 0) {
                return new Result(true);
            }
        } catch (Exception e) {
                e.printStackTrace();
        }
        return new Result(false);
    }

    /**
     * 根据id 查询博客信息
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    @ResponseBody
    public Blog findById(int id){
         Blog result=blogService.findById(id);
        List<BlogType> list=blogTypeService.selectAll(null);
        // request.getServletContext().setAttribute("blogTypeCountList",list);
             return result;
    }
}
