package com.booyue.tlh.authorizationserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController {

    /**
     * 远程Resource Service在验证access_token时，会回调该方法来验证access_token的有效性。
     * @param principal
     * @return
     */
    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public Principal getUser(Principal principal) {


        return principal;
    }


}
