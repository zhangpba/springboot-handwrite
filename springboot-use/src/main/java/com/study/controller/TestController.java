package com.study.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangpba
 * @description 测试控制器
 * @date 2023/2/13
 */
@RestController
public class TestController {

    @GetMapping("test")
    public String test() {
        return "zhangpba test !!!";
    }

}
