package com.uno.getinline.Controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController implements ErrorController { // ErrorController를 해야 error 권한을 가져옴

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @RequestMapping("/error")
    public String error() {
        return "error";
    }
}
