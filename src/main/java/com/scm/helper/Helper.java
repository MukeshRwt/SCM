package com.scm.helper;

import java.security.Principal;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Helper {

    public static String getEmailOfLoggedInUser(Authentication authentication){

    //   AuthenticationPrincipal principal = (AuthenticationPrincipal)authentication.getPrincipal();
        //  agar email id  passward se login kiya h to email kese nikalenge 
        if (authentication instanceof OAuth2AuthenticationToken) {

        var  aOAuth2AuthenticationToken =   ( OAuth2AuthenticationToken)authentication;
        var clientId = aOAuth2AuthenticationToken.getAuthorizedClientRegistrationId();

        var oauth2User =(OAuth2User) authentication.getPrincipal();
        String username = "";

        // signup with google 
        if (clientId.equalsIgnoreCase("google")) {
            System.out.println("Getting email from google");
          username =   oauth2User.getAttribute("email").toString();
        }

         // signup with github
        else if (clientId.equalsIgnoreCase("github")) {
            System.out.println("Getting email from github");
           username = oauth2User.getAttribute("email") !=null ? oauth2User.getAttribute("email").toString()
            : oauth2User.getAttribute("login").toString()+ "@gmail.com";
        }

                    
       
        }
        else{
            System.out.println("Getting data from local database");
           
        }
         return authentication.getName();
    }
}
