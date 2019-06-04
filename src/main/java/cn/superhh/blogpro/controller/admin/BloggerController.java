package cn.superhh.blogpro.controller.admin;

import cn.superhh.blogpro.pojo.BlogType;
import cn.superhh.blogpro.pojo.Blogger;
import cn.superhh.blogpro.pojo.Result;
import cn.superhh.blogpro.service.BlogTypeService;
import cn.superhh.blogpro.service.BloggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/blogger")
public class BloggerController {
    @Autowired
    private BloggerService bloggerService;
    @Autowired
    private BlogTypeService blogTypeService;

    /**
     * 登陆
     * @param
     * @param model
     * @return
     */
         @RequestMapping(value = "/login",method = RequestMethod.POST)
        public String  login(Blogger b, Model model, HttpSession session,HttpServletRequest request){
           Blogger blogger= bloggerService.login(b.getUsername(),b.getPassword());
           if(blogger!=null){
                session.setAttribute("currentUser",blogger);
               List<BlogType> result=blogTypeService.selectAll(null);
               request.getServletContext().setAttribute("blogTypeCountList",result);
                return "/admin/main";
           }
           else{
               model.addAttribute("error","用户名或密码错误");
               model.addAttribute("username",b.getUsername());
               model.addAttribute("password",b.getPassword());
               return "Login";
           }
        }

    /**
     * 跳转到登陆页面
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
        public String toLogin(){
               return "Login";
        }

    /**
     * 框架页跳转
     * @param url
     * @return
     */
    @RequestMapping("/lab")
         public String tolab(String url,String id,Model model){
               if(id==null)
        return "/admin/"+url;
               else {
                   model.addAttribute("id",id);
                   return "/admin/" + url;
               }
         }

    /**
     * 查找用户
     * @param 
     *
     */
    @RequestMapping("/find")
    @ResponseBody
    public Blogger find(HttpSession session){
       Blogger blogger= (Blogger) session.getAttribute("currentUser");
           Blogger result=     bloggerService.find(blogger.getUsername());
            return result;

    }

    /**
     * 更新博主信息
     * @param blogger
     * @param imageFile
     * @param request
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public String update(Blogger blogger, MultipartFile imageFile , HttpServletRequest request){
                 Blogger b=  bloggerService.findById(blogger.getId());
                    String fn=b.getImageName();
                    if(fn!=null) {
                        String dfn = request.getServletContext().getRealPath("userimages") + File.separator + fn;
                        File f = new File(dfn);
                        f.delete();   //删除用户之前的文件
                    }
             String fileName=System.currentTimeMillis()+ imageFile.getOriginalFilename();
               blogger.setImageName(fileName);
            String deskFilname=  request.getServletContext().getRealPath("userimages")+ File.separator+fileName;
        System.out.println(deskFilname);
            File file1=new File(deskFilname);
                file1.getParentFile().mkdirs();
        try {
            imageFile.transferTo(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            int n = bloggerService.update(blogger);
            if (n > 0) {
                return "修改成功";
            }
        }
        catch (Exception e) {
            return "修改失败";
        }

        return "修改失败";

    }

    /**
     * 修改密码
     * @param id
     * @param newPassword
     * @return
     */
    @RequestMapping("/modifyPassword")
    @ResponseBody
    public Result modifyPassword(int id, String newPassword){
               int n= bloggerService.modifyPassword(id,newPassword);
               if(n>0){
                   return new Result(true);
               }
               else {
                   Result result=new Result();
                   result.setSuccess(false);
                   return result;
               }
    }

    /**
     * 退出登录
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String logOut(HttpSession session){
        session.invalidate();
        return "redirect:login";
    }


}
