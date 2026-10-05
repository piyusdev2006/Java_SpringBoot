package com.piyus.ProfilingInSpringBoot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/demo")
public class DemoController {

    String dbURL = "jdbc:mysql://localhost:3306/springBootProfiling";
    String Username = "";
    String password = "";

    @Value("${app.welcome.message}")
    private String message = "Hello this is Naveen Singh";

    @Value("${app.welcome.code}")
    private int code;

    @Value("${app.welcome.users}")
    private List<String>names;

    @GetMapping("/greet")
    public ResponseEntity<String>greet(){
        System.out.println(names);
        return ResponseEntity.ok(message);
    }
}
