package cn.gao.server.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @Date 2021/3/27 15:50
 * @Created by Administrator
 */
@RestController
public class HelloController {
    @RequestMapping("hello")
    public String hello(){
        return "hello";
    }
}
