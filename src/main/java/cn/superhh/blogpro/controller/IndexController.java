package cn.superhh.blogpro.controller;

import cn.superhh.blogpro.pojo.Blog;
import cn.superhh.blogpro.pojo.PageBean;
import cn.superhh.blogpro.service.BlogService;
import cn.superhh.blogpro.util.PageUtil;
import cn.superhh.blogpro.util.StringUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    private BlogService blogService;

    /**
     *查找博客内容
     * @param page
     * @param typeId
     * @param releaseDateStr
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping({"/index"})
     public ModelAndView index(@RequestParam(value="page", required=false) String page, @RequestParam(value="typeId", required=false) String typeId, @RequestParam(value="releaseDateStr", required=false) String releaseDateStr, HttpServletRequest request)
          throws Exception
   {
          ModelAndView mav = new ModelAndView();
          if (StringUtil.isEmpty(page)) {
                 page = "1";
             }



          PageBean pageBean = new PageBean((Integer.parseInt(page)-1)*10, 10);
         HashMap<String, Object> map = new HashMap();
           map.put("start", Integer.valueOf(pageBean.getStart()));
           map.put("size", Integer.valueOf(pageBean.getPageSize()));
           map.put("typeId", typeId);
            map.put("releaseDateStr", releaseDateStr);

            List<Blog> blogList = blogService.selectAll(map);
             for (Blog blog : blogList)
                 {
                    String s= blog.getReleaseDate().toString().replace('T',' ');
                     blog.setReleaseDateStr(s);
                  List<String> imagesList = blog.getImagesList();
                 String blogInfo = blog.getContent();
                Document doc = Jsoup.parse(blogInfo);
                   Elements jpgs = doc.select("img[src$=.jpg]");
                 for (int i = 0; i < jpgs.size(); i++)
                       {
                      Element jpg = (Element)jpgs.get(i);
                      imagesList.add(jpg.toString());
                      if (i == 2) {
                             break;
                            }
                       }
                }
             mav.addObject("blogList", blogList);
            StringBuffer param = new StringBuffer();
             if (StringUtil.isNotEmpty(typeId)) {
                  param.append("typeId=" + typeId + "&");
               }
             if (StringUtil.isNotEmpty(releaseDateStr)) {
                 param.append("releaseDateStr=" + releaseDateStr + "&");
               }
             mav.addObject("pageCode", PageUtil.genPagination(request.getContextPath() + "/index", blogService.getTotal(map), Integer.parseInt(page), 10, param.toString()));
            mav.addObject("mainPage", "foreground/blog/list.jsp");
            mav.addObject("pageTitle", "PJBS");
             mav.setViewName("mainTemp");
            return mav;
           }

}
