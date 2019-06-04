package cn.superhh.blogpro.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局处理异常
 */

@ControllerAdvice
public class GlobeExceptiionHandle {
    @ExceptionHandler(value = Exception.class)
    public ModelAndView handle(ModelAndView mv, HttpServletRequest request, Exception e){
        mv.addObject("exception", e);
        mv.setViewName("errorPage");
        return mv;

    }
}
