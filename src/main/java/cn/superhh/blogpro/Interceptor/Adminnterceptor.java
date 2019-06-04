package cn.superhh.blogpro.Interceptor;

import cn.superhh.blogpro.pojo.Blogger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Adminnterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
             if(request.getRequestURI().contains("login")){
                 return  true;
             }

           Blogger blogger= (Blogger) request.getSession().getAttribute("currentUser");
           if(blogger==null){
            /*  request.getRequestDispatcher("login").forward(request,response);*/
             response.sendRedirect("/blogger/login");
           }
           else{
               return true;
           }


        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
