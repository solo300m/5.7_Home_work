package com.example.mybookshoppostgrenext.controllers;

import com.example.mybookshoppostgrenext.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.logging.Logger;

@Controller
public class PopularController {

    private final BookService bookService;

    @Autowired
    public PopularController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/popular")
    public String popularPage(){
        Logger.getLogger(PopularController.class.getName()).info("Open popular page");
        return "/popular.html";
    }
}
