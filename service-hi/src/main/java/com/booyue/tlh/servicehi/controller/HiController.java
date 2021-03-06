package com.booyue.tlh.servicehi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {

    @Value("${server.port}")
    private String port;

    @RequestMapping("/hi")
    public String hi() {
        return "hi:" + "I am from hi:" + port;
    }

    /**
     * 除了得到授权之外，还需要用户有：ROLE_ADMIN 的权限
     *
     * @return
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping("/hello_admin")
    public String hello_admin() {
        return "hello admin!";
    }

    @PreAuthorize("hasAuthority('ROLE_READ')")
    @RequestMapping("/hello_read")
    public String hello_read() {
        return "hello read!";
    }

    @PreAuthorize("hasAuthority('ROLE_ROOT')")
    @RequestMapping("/hello_root")
    public String hello_root() {
        return "hello root!";
    }
}
