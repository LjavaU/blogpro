package cn.superhh.blogpro.controller.admin;

import cn.superhh.blogpro.pojo.Blog;
import cn.superhh.blogpro.pojo.Comment;
import cn.superhh.blogpro.pojo.PageBean;
import cn.superhh.blogpro.pojo.Result;
import cn.superhh.blogpro.service.BlogService;
import cn.superhh.blogpro.service.CommentService;
import cn.superhh.blogpro.util.DateJsonValueProcessor;
import cn.superhh.blogpro.util.ResponseUtil;
import com.alibaba.fastjson.JSONObject;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/admin/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
       private BlogService blogService;
    /**
     * 后台显示博客评论列表
     * @param page
     * @param rows
     * @param response
     * @param state
     * @return
     */
    @RequestMapping("/list")
    public String list(String page, String rows, HttpServletResponse response, @RequestParam(value="state",required=false) String state ){
        PageBean pageBean=new PageBean(((Integer.parseInt(page))-1)*10,Integer.parseInt(rows));
        HashMap<String,Object> map=new HashMap<String,Object>();
        map.put("start", Integer.valueOf(pageBean.getStart()));
        map.put("size", Integer.valueOf(pageBean.getPageSize()));
        map.put("state",state);
        List<Comment> commentList=commentService.list(map);
        int  total=commentService.getTotal(map);
        JSONObject result=new JSONObject();
        //格式化日期
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd "));

        JSONArray jsonArray=JSONArray.fromObject(commentList,jsonConfig);
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
     * 删除博客评论
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(int[] ids){
            int n=  commentService.delete(ids);
            if(n>0){
                return new Result(true);
            }
           return new Result(false);
    }

    /**
     * 评论审核
     * @param ids
     * @param state
     * @return
     */
    @RequestMapping("/review")
          @ResponseBody
    public Result review(int[] ids, String state){
            try {
                for (int i = 0; i < ids.length; i++) {
                    Comment comment = new Comment();
                    comment.setState(Integer.valueOf(state));
                    comment.setId(ids[i]);
                    commentService.review(comment);
                       if(state.equals("2")){
                           Comment comment1=commentService.findByID(ids[i]);
                        Blog blog= blogService.findById( comment1.getBlog().getId());
                        blog.setReplyHit(blog.getReplyHit()-1);
                          blogService.update(blog);
                       }

                }
                return new Result(true);
            }
            catch (Exception e){
                e.printStackTrace();
                return  new Result(false);
            }



}


}
