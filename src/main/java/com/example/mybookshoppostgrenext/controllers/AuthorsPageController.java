package com.example.mybookshoppostgrenext.controllers;

import com.example.mybookshoppostgrenext.Service.AuthorService;
import com.example.mybookshoppostgrenext.Service.BookService;
import com.example.mybookshoppostgrenext.data.Authors;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Controller

//@RequestMapping("/authors")
//@Api(description = "authors data")
public class AuthorsPageController {

    private BookService bookService;
    private AuthorService authorService;

    @Autowired
    public AuthorsPageController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public String reloadAuthors(Model model){
        model.addAttribute("bookList",bookService.getBookData());
        model.addAttribute("authors",authorService.getMapAuthors(authorService.getAuthorsList()));
        return "/authors/index";
    }

    @GetMapping("/authors/genres")
    public String genresPage(){
        Logger.getLogger(MainPageController.class.getName()).info("Opened page genres");
        return "/genres/index.html";
    }

    @GetMapping("/authors/bookshop")
    public String mainOpen(Model model){
        Logger.getLogger(GenresPageController.class.getName()).info("Back on the main-page");
        model.addAttribute("bookData", bookService.getBookData());
        return "index.html";
    }
    /*@GetMapping("/authors")
    public String authorPage(){
        Logger.getLogger(MainPageController.class.getName()).info("Opened page authors");
        return "/authors/index.html";
    }*/
    @GetMapping("/authors/{id}")
    public String slugPage(@PathVariable("id") int id, Model model){
        //Logger.getLogger(MainPageController.class.getName()).info("Opened page slug");
        model.addAttribute("authors",authorService.getAuthorId(id).getAuthor());
        model.addAttribute("bio",authorService.getAuthorId(id).getBiography());
        model.addAttribute("photo",authorService.getAuthorId(id).getPhoto());
        model.addAttribute("books",authorService.getIdAuthorBook(id));
        Logger.getLogger(MainPageController.class.getName()).info("Opened page slug"/*+model.getAttribute("bio")*/);
        return "/authors/slugs/slug.html";
    }

    @ApiOperation("method to get map of authors")
    @GetMapping("/api/authors")
    @ResponseBody
    public Map<String, List<Authors>> authors(){
        return authorService.getMapAuthors(authorService.getAuthorsList());
    }
}
