package cn.superhh.blogpro.controller.admin;

import cn.superhh.blogpro.pojo.BlogType;
import cn.superhh.blogpro.pojo.Result;
import cn.superhh.blogpro.service.BlogTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin/system")
public class SystemController {
      @Autowired
      private BlogTypeService blogTypeService;
               @RequestMapping("/refreshSystem")
               @ResponseBody
    public Result refresh(HttpServletRequest request){
                 //刷新博客类别列表
         List<BlogType> list= blogTypeService.selectAll(null);
           request.getServletContext().setAttribute("blogTypeCountList",list);
           return new Result(true);
    }
}
