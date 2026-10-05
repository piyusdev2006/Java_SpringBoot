package com.piyus;

import com.piyus.config.WebConfig;
import jakarta.servlet.annotation.WebServlet;
import jdk.swing.interop.DispatcherWrapper;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() throws LifecycleException {

        System.out.println("Hello Naveen!");

        // Here we need to write so much boilerplate code to configure
        // web mvc to run the application

        Tomcat tomcat = new Tomcat();

        tomcat.setPort(8080);
        tomcat.getConnector();

        // jis bhi application ki baat ho rhi hoti hai, tomcat me usse contextPath kahte hai
        // kyoki tomcat container me multiple application hoti hai aur har application me multiple servlets hote hai
        // , isiliye use context path chahiye hota hai jo run karwana hai ya baat ho rhi hai

        String contextPath = "";

        // create a folder in src/main/<folderName> and pass it url in baseDoc
        // jis bhi application ki baat ho rhi hai uske pure pure parent folder ka path chahiye hota hai

        String baseDoc = new File("src/main/webApp").getAbsolutePath();

        Context context = tomcat.addContext(contextPath, baseDoc);

        AnnotationConfigWebApplicationContext springContext = new AnnotationConfigWebApplicationContext();
        springContext.register(WebConfig.class);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(springContext);

        Tomcat.addServlet(context, "dispatcherServlet", dispatcherServlet);

        context.addServletMapping("/", "dispatcherServlet");

        tomcat.start();

        System.out.println("Tomcat Started on server 8080");

        // keep running tomcat server
        tomcat.getServer().await();
    }
}
