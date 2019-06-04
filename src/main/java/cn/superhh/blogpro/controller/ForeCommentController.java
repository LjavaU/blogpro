package cn.superhh.blogpro.controller;

import cn.superhh.blogpro.pojo.Blog;
import cn.superhh.blogpro.pojo.Comment;
import cn.superhh.blogpro.pojo.Result;
import cn.superhh.blogpro.service.BlogService;
import cn.superhh.blogpro.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/comment")
public class ForeCommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
      private BlogService blogService;
    /**
     * 游客添加评论
     * @param comment
     * @param request
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public Result add(Comment comment, HttpServletRequest request, HttpServletResponse response) throws IOException {
                String ip= request.getRemoteHost();
                   comment.setUserIp(ip);
                   if(comment.getContent()==null ){
                    response.sendRedirect("/main");

                   }
                   if(comment.getContent()!=null && !comment.getContent().trim().isEmpty()) {
                       int n = commentService.add(comment);
                     Blog blog =blogService.findById(comment.getBlog().getId());
                       blog.setReplyHit(blog.getReplyHit()+1);
                       blogService.update(blog);
                       if (n > 0) {
                           return new Result(true);
                       } else {
                           return new Result(false);
                       }
                   }
                       return null;
    }
}
