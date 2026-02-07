package com.example.social_login;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("/api/securedPage")
    public String getSecuredPage(Authentication authentication){

        if (authentication instanceof UsernamePasswordAuthenticationToken){
            System.out.println("User logged in by username and password");
        }

        if (authentication instanceof OAuth2AuthenticationToken)
            System.out.println("User logged in by outh2");
        return "SecuredPage.html";
    }

}
