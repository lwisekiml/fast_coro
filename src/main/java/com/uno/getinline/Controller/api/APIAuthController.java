package com.uno.getinline.Controller.api;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class APIAuthController {

    @GetMapping("/sign-up")
    public String signUp() {
        return "done.";
    }

    @GetMapping("/login")
    public String login() {
        return "done.";
    }

}
