package ra.academy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration // chú thích đây là lớp cấu hình
@EnableWebMvc // cho phép cấu hình bằng mvc
@ComponentScan(basePackages = {"ra.academy.controller","ra.academy.service","ra.academy.config"})
public class WebConfig implements WebMvcConfigurer {
    // tạo bean viewResolver
    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver  viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
    // cấu hình file upload
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver getResolver()  {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSizePerFile(52428800); // 50MB
        return resolver;
    }
}
