package com.example._DemoSpringBootProject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("hello")
    public String hello() {
        return "<h1> Hello Naveen </h1>";
    }


    @GetMapping("bye")
    public String bye() {
        return "<h1> Bye Naveen </h1>";
    }
}
