package cn.superhh.blogpro.Interceptor;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
public class AppConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new Adminnterceptor()).addPathPatterns("/blog/*");
        registry.addInterceptor(new Adminnterceptor()).addPathPatterns("/blogger/*");
        registry.addInterceptor(new Adminnterceptor()).addPathPatterns("/blogType/*");
        registry.addInterceptor(new Adminnterceptor()).addPathPatterns("/admin/comment/*");
        registry.addInterceptor(new Adminnterceptor()).addPathPatterns("/link/*");
        registry.addInterceptor(new Adminnterceptor()).addPathPatterns("/admin/system/*");
    }

}
