package com.booyue.tlh.oauth2client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class Oauth2clientApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2clientApplication.class, args);
    }


    @RequestMapping("/test")
    public String test() {
        return "oauth2client";
    }

}
