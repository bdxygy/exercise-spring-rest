package io.budisantoso.dev.learnspringsecurity.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/host")
public class HostController {

    @GetMapping("/admin")
    public String admin(){
        return "Welcome Admin";
    }

    @GetMapping("/user")
    public String user(){
        return "Welcome User";
    }

    @GetMapping("/dev")
    public String dev(){
        return "Welcome Developer";
    }



}
