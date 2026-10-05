package com.hello1;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
//@Lazy -- for singleton lazy initialization, but default its singleton eager initalization
public class CartService implements BeanNameAware, ApplicationContextAware/*implements InitializingBean, DisposableBean */ {

    Map<Integer, String> mp;

    public CartService() {

        mp = new HashMap<>();
        System.out.println("cart service init");

    }

    // ye sabhi methods spring call kr rha callback ke through

    // Using InitializingBean Interface: yaha pe koi bhi task perform kar sakta hu
    // jo kisi method call hone se pahle krna hai

//    @Override
//    public void afterPropertiesSet() throws Exception {
//        System.out.println("Initializing Cart Service");
//        mp.put(1, "piyus");
//        mp.put(2, "singh");
//    }

//     Using initMethod = "start"
//    public void start(){
//        System.out.println("Initializing Cart Service ,cart service start");
//        mp.put(1, "piyus");
//        mp.put(2, "singh");
//    }

    // Using @PostConstruct
    @PostConstruct
    public void getValue(){
        mp.put(1, "piyus");
        mp.put(2, "singh");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("cart service setBeanName" + name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("cart service setApplicationContext" + applicationContext.getClass());
    }


    // niche wale methods ko khud call kr rhe
    public String getValue(int key){
        return mp.get(key);
    }

    public void addToCart(){
        System.out.println("services added to Cart");
    }


    // it destroy the bean after use
//    @Override
//    public void destroy() throws Exception {
//        mp.clear();
//        System.out.println("cart service destroy");
//    }

    // destroying bean using : destroyMethod
//    public void stop(){
//        mp.clear();
//        System.out.println("cart service destroy");
//    }

    @PreDestroy
    public void preDestroy (){
        mp.clear();
        System.out.println("cart service destroy");
    }
}
