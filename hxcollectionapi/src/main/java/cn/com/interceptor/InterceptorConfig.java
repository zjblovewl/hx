package cn.com.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @类功能说明：拦截器注册
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/3/12 上午15:45
 * @版本：V1.0
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
    @Bean
    public AppInterceptor appInterceptor() {
        return new AppInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册用时拦截器
        registry.addInterceptor(new ExcuteTimeInterceptor());

        //注册加解密拦截器  (拦截所有已api开头的拦截器)
        InterceptorRegistration ir = registry.addInterceptor(appInterceptor()).addPathPatterns("/api/**");

        //不拦截请求
        //ir.excludePathPatterns("/test/test");


    }
}
