package com.example.mybookshoppostgrenext.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DocPageController {

    @GetMapping("/docs")
    public String docsPage(){
        return "/Documents/Documents.html";
    }

    @GetMapping("/company")
    public String companyPage(){
        return "/Documents/about_kompany.html";
    }

    @GetMapping("/help")
    public String helpPage(){
        return "/Documents/help.html";
    }

    @GetMapping("/customers")
    public String customersPage(){
        return "/Documents/customer.html";
    }
}
