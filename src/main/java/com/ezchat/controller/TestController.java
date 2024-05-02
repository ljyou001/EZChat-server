package com.ezchat.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @RequestMapping("/helloworld")
    public String test() {
        return "Hello World";
    }
}
