package com.example.springboardapi.config;

import com.example.springboardapi.filter.XSSFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    public FilterRegistrationBean<XSSFilter> filterRegister() {
        FilterRegistrationBean<XSSFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new XSSFilter());
        registrationBean.setOrder(1);
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }
}
