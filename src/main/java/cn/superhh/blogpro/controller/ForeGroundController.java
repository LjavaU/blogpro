package cn.superhh.blogpro.controller;

import cn.superhh.blogpro.pojo.Blog;
import cn.superhh.blogpro.service.BlogService;
import cn.superhh.blogpro.service.BlogTypeService;
import cn.superhh.blogpro.service.BloggerService;
import cn.superhh.blogpro.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ForeGroundController {
    @Autowired
    private BloggerService bloggerService;
    @Autowired
    private BlogTypeService blogTypeService;
     @Autowired
    private BlogService blogService;
     @Autowired
     private LinkService linkService;

    /**
     *游客访问前台主页面
     * @param request
     * @return
     */
    @RequestMapping("/main")
    public ModelAndView to(HttpServletRequest  request){
        ModelAndView mav = new ModelAndView();

        mav.addObject("mainPage", "foreground/blogger/info.jsp");
        mav.addObject("pageTitle", "PJBS");
        request.getServletContext().setAttribute("blogTypeCountList",blogTypeService.countList());
        request.getServletContext().setAttribute("blogCountList",blogService.countList());
        request.getServletContext().setAttribute("blogger",bloggerService.findById(1));
        request.getServletContext().setAttribute("linkList",linkService.linkList(null));
        mav.setViewName("mainTemp");
        return mav;
    }

    /**
     * 导航
     * @return
     */
    @RequestMapping("/aboutMe")
    public ModelAndView aboutme(){


                 ModelAndView mav = new ModelAndView();
                 mav.addObject("blogger", this.bloggerService.findById(1));
                 mav.addObject("mainPage", "foreground/blogger/info.jsp");
                 mav.addObject("pageTitle", "关于博主");
                 mav.setViewName("mainTemp");
                return mav;
    }

    }


