package shop.mtcoding.loginexample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import shop.mtcoding.loginexample.handler.CustomInterceptorHandler;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private CustomInterceptorHandler customInterceptorHandler;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customInterceptorHandler);
    }
    
}
