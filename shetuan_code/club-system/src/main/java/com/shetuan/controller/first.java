package com.shetuan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: first
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 11:46
 * @Version 1.0
 */
@RestController
public class first {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
