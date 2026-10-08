package com.piyus.InterceptorInSpringBoot.config;

import com.piyus.InterceptorInSpringBoot.interceptor.AuthenticationInterceptor;
import com.piyus.InterceptorInSpringBoot.interceptor.AuthorizationInterceptor;
import com.piyus.InterceptorInSpringBoot.interceptor.LoggingInterceptor;
import com.piyus.InterceptorInSpringBoot.interceptor.TimingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LoggingInterceptor loggingInterceptor;
    private final AuthenticationInterceptor authenticationInterceptor;
    private final TimingInterceptor timingInterceptor;
    private final AuthorizationInterceptor authorizationInterceptor;

    @Autowired
    public WebConfig(
            LoggingInterceptor loggingInterceptor,
            AuthenticationInterceptor authenticationInterceptor,
            TimingInterceptor timingInterceptor,
            AuthorizationInterceptor authorizationInterceptor
    ) {
        this.loggingInterceptor = loggingInterceptor;
        this.authenticationInterceptor = authenticationInterceptor;
        this.timingInterceptor = timingInterceptor;
        this.authorizationInterceptor = authorizationInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // bhi tak humne ek hi interceptor ko register kiya tha
        // registry.addInterceptor(loggingInterceptor)


        // hum multiple interceptor ko register kar sakte hai
        registry.addInterceptor(loggingInterceptor)
                // hum interceptors par condition bhi add kar sakte hai
                .addPathPatterns("/api/**") // ye sabhi ke liye chalega :- "/api/students/rohit"
                .excludePathPatterns("./api/auth/login", "api/public/**")
                .order(2);
        // ek hi star add krna hai addPathPatterns("/api/*") bas itne ke liye hi chalega: "/api/students", "/api/teachers" hi chalega

        // servlet ke filter() me ek hi star(*) se sabhi paths ke liye chalega

        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/api/**")
                .order(3);

        registry.addInterceptor(timingInterceptor)
                .addPathPatterns("/api/**")
                .order(1);

        registry.addInterceptor(authorizationInterceptor)
                .addPathPatterns("/api/**")
                .order(4);
    }
}