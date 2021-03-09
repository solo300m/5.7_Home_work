package com.example.mybookshoppostgrenext.controllers;

import com.example.mybookshoppostgrenext.Service.AuthorService;
import com.example.mybookshoppostgrenext.data.Book;
import com.example.mybookshoppostgrenext.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Controller
public class SearchAuthorController {
    private final BookService bookService;
    private AuthorService authorService;

    @Autowired
    public SearchAuthorController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping("/search")
    public String openSearchPage(){
        Logger.getLogger(SearchAuthorController.class.getName()).info("Open search page");
//        Map<String, List<Book>> list = bookService.getAuthorName(found);
//        model.addAttribute("books",list);
        return "/books/author.html";
    }

    @GetMapping("/search/{found}")
    public String searchPage(@PathVariable("found") String found, Model model){
        Logger.getLogger(SearchAuthorController.class.getName()).info("Open searchAuthor page");
        Map<String, List<Book>> list = authorService.getAuthorName(found);
        model.addAttribute("books",list);
        return "/books/author.html";
    }
}
