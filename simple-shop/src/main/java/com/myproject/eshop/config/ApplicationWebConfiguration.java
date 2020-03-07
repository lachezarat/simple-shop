package com.myproject.eshop.config;

import com.myproject.eshop.web.interceptors.FaviconInterceptor;
import com.myproject.eshop.web.interceptors.LogoInterceptor;
import com.myproject.eshop.web.interceptors.PageTitleInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationWebConfiguration implements WebMvcConfigurer {

    private final FaviconInterceptor faviconInterceptor;
    private final PageTitleInterceptor pageTitleInterceptor;
    private final LogoInterceptor logoInterceptor;

    @Autowired
    public ApplicationWebConfiguration(FaviconInterceptor faviconInterceptor, PageTitleInterceptor pageTitleInterceptor, LogoInterceptor logoInterceptor) {
        this.faviconInterceptor = faviconInterceptor;
        this.pageTitleInterceptor = pageTitleInterceptor;
        this.logoInterceptor = logoInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(faviconInterceptor);
        registry.addInterceptor(pageTitleInterceptor);
        registry.addInterceptor(logoInterceptor);
    }
}
