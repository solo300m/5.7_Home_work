package com.example.mybookshoppostgrenext.controllers;

import com.example.mybookshoppostgrenext.data.Book;
import com.example.mybookshoppostgrenext.Service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
//@RequestMapping("/recent")
public class RecentController {

    private final BookService bookService;

    @Autowired
    public RecentController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("recentBooks")
    public List<Book> recentBooks(){
        //bookService.setAuthorsData(bookService.getBookData());
        //bookService.updateBookIdAuthors();
        return bookService.getListOfNewBooks();
    }
    @ModelAttribute("searchResults")
    public List<Book> searchResults(){
        return new ArrayList<>();
    }

    @GetMapping("/recent")
    public String recentPage(Model model){
        model.addAttribute("searchResults",bookService.getPageOfNewBooks(0,5).getContent());
        Logger.getLogger(RecentController.class.getName()).info("Opened page recent");
        return "/recent.html";
    }
    @GetMapping("/recent/bookshop")
    public String mainPageReturn(){
        Logger.getLogger(RecentController.class.getName()).info("Reload great page!");
        return "index";
    }
}
