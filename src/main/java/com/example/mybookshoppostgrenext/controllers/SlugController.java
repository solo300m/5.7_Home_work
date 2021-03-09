package com.example.mybookshoppostgrenext.controllers;

import com.example.mybookshoppostgrenext.Service.AuthorService;
import com.example.mybookshoppostgrenext.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.logging.Logger;

@Controller

public class SlugController {
    private BookService bookService;
    private AuthorService authorService;

    @Autowired
    public SlugController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    //    @GetMapping
//    public String getSlugPage(Model model){
//        model.addAttribute("authors",bookService.getMapId(bookService.getAuthorsList()));
//        return "authors/slug.html";
//    }
    @GetMapping("/slug/authors")
    public String getAuthorsSlug(Model model){
        Logger.getLogger(MainPageController.class.getName()).info("Opened page authors from slug");
        model.addAttribute("authors",authorService.getMapAuthors(authorService.getAuthorsList()));
        return "/authors/index.html";
    }
    @GetMapping("/slug/bookshop")
    public String getSlugMain(Model model){
        Logger.getLogger(MainPageController.class.getName()).info("Opened page main from slug");
        model.addAttribute("bookData",bookService.getBookData());
        return "index.html";
    }
    @GetMapping("/slug/genres")
    public String genresPage(){
        Logger.getLogger(MainPageController.class.getName()).info("Opened page genres from slug");
        return "/genres/index.html";
    }
}
