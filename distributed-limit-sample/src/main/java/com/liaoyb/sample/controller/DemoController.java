package com.liaoyb.sample.controller;

import com.liaoyb.limit.annotation.CommonLimit;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liaoyb
 * @date 2018-10-25 15:12
 */
@RestController
public class DemoController {

    @CommonLimit
    @RequestMapping("hello")
    public String hello() {
        return "hello demo";
    }
}
