package com.uno.getinline.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController { // ErrorController를 해야 error 권한을 가져옴

    @GetMapping("/")
    public String root() {
        return "index";
    }

}
