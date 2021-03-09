package com.example.mybookshoppostgrenext.controllers;

import com.example.mybookshoppostgrenext.Service.AuthorService;
import com.example.mybookshoppostgrenext.data.Book;
import com.example.mybookshoppostgrenext.Service.BookService;
import com.example.mybookshoppostgrenext.Service.HibernateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Controller
//@RequestMapping("/bookshop")
public class MainPageController {

    private final BookService bookService;
    private AuthorService authorService;
    private HibernateService hibernateService;

    @Autowired
    public MainPageController(BookService bookService, AuthorService authorService, HibernateService hibernateService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.hibernateService = hibernateService;
    }

    @ModelAttribute("recommendedBooks")
    public List<Book> recommendedBooks(){
        //bookService.setAuthorsData(bookService.getBookData());
        //bookService.updateBookIdAuthors();
        return bookService.getBookData();
    }

    @GetMapping("/")
    public String reloadMainPage(){

        Logger.getLogger(MainPageController.class.getName()).info("Reload great page!");
        return "index";
    }

    @GetMapping("/bookshop")
    public String mainPage(Model model){
        model.addAttribute("book2author",hibernateService.getBookData2());
        Logger.getLogger(MainPageController.class.getName()).info("Hy, it is great page!");
        return "index";
    }
    @GetMapping("/bookshop/genres")
    public String genresPage(){
        Logger.getLogger(MainPageController.class.getName()).info("Opened page genres");
        return "/genres/index.html";
    }
    @GetMapping("/bookshop/authors")
    public String authorPage(Model model){
        Logger.getLogger(MainPageController.class.getName()).info("Opened page authors from main_page");
        model.addAttribute("authors",authorService.getMapAuthors(authorService.getAuthorsList()));
        return "/authors/index.html";
    }
    @GetMapping("/bookshop/recent")
    public String recentPage(){
        Logger.getLogger(MainPageController.class.getName()).info("Opened page recent from main_page");
        return "/recent.html";
    }

}
