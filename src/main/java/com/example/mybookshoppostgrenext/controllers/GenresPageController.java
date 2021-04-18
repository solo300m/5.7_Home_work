package com.example.mybookshoppostgrenext.controllers;

import com.example.mybookshoppostgrenext.Service.AuthorService;
import com.example.mybookshoppostgrenext.Service.BookService;
import com.example.mybookshoppostgrenext.Service.GenreService;
import com.example.mybookshoppostgrenext.data.Book;
import com.example.mybookshoppostgrenext.data.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/genres")
public class GenresPageController {
    private BookService bookService;
    private AuthorService authorService;
    private GenreService genreService;

    @Autowired
    public GenresPageController(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @GetMapping
    public String rederectGenres(Model model){
        model.addAttribute("genre",bookService.getGenreAll());
        model.addAttribute("b2g",genreService.getMapBook2Genre());
        Logger.getLogger(GenresPageController.class.getName()).info("Reload genres page");
        return "/genres/index";
    }
    @GetMapping("/bookshop")
    public String mainOpen(Model model){
        Logger.getLogger(GenresPageController.class.getName()).info("Back on the main-page");
        model.addAttribute("bookData", bookService.getBookData());
        return "index.html";
    }
    @GetMapping("/authors")
    public String authorPage(Model model){
        Logger.getLogger(MainPageController.class.getName()).info("Opened page authors from Genres_Page");
        model.addAttribute("authors",authorService.getMapAuthors(authorService.getAuthorsList()));
        return "/authors/index.html";
    }

    @GetMapping("/slug/{gen}")
    public String genreSlugPage(@PathVariable("gen") Integer gen,Model model){
        model.addAttribute("gen",genreService.getGenreOfId(gen));
        //model.addAttribute("listBook",genreService.getListBookOfGenre(genreService.getMapBook2Genre(), genre));
        model.addAttribute("listBook",genreService.getPageBookOfGenre(gen,0,5));
        model.addAttribute("count",genreService.getListBookOfGenre(genreService.getMapBook2Genre(), gen).size());
        return "/genres/slug.html";
    }
}
