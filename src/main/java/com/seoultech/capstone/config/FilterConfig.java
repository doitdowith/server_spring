package com.seoultech.capstone.config;

import com.seoultech.capstone.config.jwt.JwtFilter;
import javax.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

public class FilterConfig {

  @Bean
  public FilterRegistrationBean<Filter> logFilter() {

    FilterRegistrationBean<Filter> filterRegistrationBean = new
        FilterRegistrationBean<>();
    filterRegistrationBean.setFilter(new JwtFilter());
    filterRegistrationBean.setOrder(1);
    filterRegistrationBean.addUrlPatterns("/*");

    return filterRegistrationBean;
  }
}
