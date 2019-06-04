package cn.superhh.blogpro.controller;

import cn.superhh.blogpro.lucene.BlogIndex;
import cn.superhh.blogpro.lucene.BlogIndex1;
import cn.superhh.blogpro.pojo.Blog;
import cn.superhh.blogpro.service.BlogService;
import cn.superhh.blogpro.service.CommentService;
import cn.superhh.blogpro.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Controller
public class ForeBlogController {
@Autowired
    private BlogService blogService;
@Autowired
private CommentService commentService;

  @Autowired
private BlogIndex1 blogIndex;
  @Autowired
private BlogIndex blogIndex2;

    @RequestMapping({"/articles/{id}"})
    public ModelAndView details(@PathVariable("id") Integer id, HttpServletRequest request,ModelAndView mav)
    {
        try {

            Blog blog = blogService.findById(id);
            String keyWords = blog.getKeyWord();
            //处理关键字


            if (StringUtil.isNotEmpty(keyWords)) {
                String[] arr = keyWords.split(" ");
                mav.addObject("keyWords", StringUtil.filterWhite(Arrays.asList(arr)));
            } else {
                mav.addObject("keyWords", null);
            }
            mav.addObject("blog", blog);
            //阅读数加1
            blog.setClickHit(Integer.valueOf(blog.getClickHit() + 1));
            blogService.update(blog);
            HashMap<String, Object> map = new HashMap();
            map.put("blogId", blog.getId());
            map.put("state", Integer.valueOf(1));
            mav.addObject("commentList", commentService.list(map));
            mav.addObject("pageCode", genUpAndDownPageCode(blogService.getLast(id), blogService.getNext(id), request.getServletContext().getContextPath()));
            mav.addObject("mainPage", "foreground/blog/view.jsp");
            mav.addObject("pageTitle", blog.getTitle() + "_Java开源博客系统");
            mav.setViewName("mainTemp");
            return mav;
        }catch (Exception e){
            mav.addObject("exception",e);
             mav.setViewName("errorPage");
             return mav;
        }



    }

    /**
     * 全局搜索
     * @param q
     * @param page
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping({"/q"})
    public ModelAndView search(@RequestParam(value="q", required=false) String q, @RequestParam(value="page", required=false) String page, HttpServletRequest request)
            throws Exception
    {
        if (StringUtil.isEmpty(page)) {
            page = "1";
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("mainPage", "foreground/blog/result.jsp");
        List<Blog> blogList = blogService.like("%"+q+"%");
        Integer toIndex = Integer.valueOf(blogList.size() >= Integer.parseInt(page) * 10 ? Integer.parseInt(page) * 10 : blogList.size());
        mav.addObject("blogList", blogList.subList((Integer.parseInt(page) - 1) * 10, toIndex.intValue()));
        mav.addObject("pageCode", genUpAndDownPageCode(Integer.valueOf(Integer.parseInt(page)), Integer.valueOf(blogList.size()), q, Integer.valueOf(10), request.getServletContext().getContextPath()));
        mav.addObject("q", q);
        mav.addObject("resultTotal", Integer.valueOf(blogList.size()));
        mav.addObject("pageTitle", "搜索关键字'" + q + "'结果页面_Java开源博客系统");
        mav.setViewName("mainTemp");
        return mav;
    }


    private String genUpAndDownPageCode(Blog lastBlog, Blog nextBlog, String projectContext)
    {
        StringBuffer pageCode = new StringBuffer();
        if ((lastBlog == null) || (lastBlog.getId() == null)) {
            pageCode.append("<p>上一篇：没有了</p>");
        } else {
            pageCode.append("<p>上一篇：<a href='" + projectContext + "/articles/" + lastBlog.getId() + "'>" + lastBlog.getTitle() + "</a></p>");
        }
        if ((nextBlog == null) || (nextBlog.getId() == null)) {
            pageCode.append("<p>下一篇：没有了</p>");
        } else {
            pageCode.append("<p>下一篇：<a href='" + projectContext + "/articles/" + nextBlog.getId() + "'>" + nextBlog.getTitle() + "</a></p>");
        }
        return pageCode.toString();
    }

    private String genUpAndDownPageCode(Integer page, Integer totalNum, String q, Integer pageSize, String projectContext)
    {
        long totalPage = totalNum.intValue() % pageSize.intValue() == 0 ? totalNum.intValue() / pageSize.intValue() : totalNum.intValue() / pageSize.intValue() + 1;
        StringBuffer pageCode = new StringBuffer();
        if (totalPage == 0L) {
            return "";
        }
        pageCode.append("<nav>");
        pageCode.append("<ul class='pager' >");
        if (page.intValue() > 1) {
            pageCode.append("<li><a href='" + projectContext + "/blog/q.html?page=" + (page.intValue() - 1) + "&q=" + q + "'>上一页</a></li>");
        } else {
            pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
        }
        if (page.intValue() < totalPage) {
            pageCode.append("<li><a href='" + projectContext + "/blog/q.html?page=" + (page.intValue() + 1) + "&q=" + q + "'>下一页</a></li>");
        } else {
            pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
        }
        pageCode.append("</ul>");
        pageCode.append("</nav>");

        return pageCode.toString();
    }
}

