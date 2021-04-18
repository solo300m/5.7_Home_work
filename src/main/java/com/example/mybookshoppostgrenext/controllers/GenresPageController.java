package com.example.mybookshoppostgrenext.controllers;

import com.example.mybookshoppostgrenext.Service.AuthorService;
import com.example.mybookshoppostgrenext.Service.BookService;
import com.example.mybookshoppostgrenext.Service.GenreService;
import com.example.mybookshoppostgrenext.data.Book;
import com.example.mybookshoppostgrenext.data.Book2Genre;
import com.example.mybookshoppostgrenext.data.BooksPageDto;
import com.example.mybookshoppostgrenext.data.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/genres")
public class GenresPageController {
    private BookService bookService;
    private AuthorService authorService;
    private GenreService genreService;
    private Integer genre_old = 0;

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
        genre_old = gen;
        model.addAttribute("gen",genreService.getGenreOfId(gen));
        //model.addAttribute("listBook",genreService.getListBookOfGenre(genreService.getMapBook2Genre(), genre));
        //model.addAttribute("listBook",genreService.getPageBookOfGenre(genre_old,0,5));
        List<Book2Genre>book2GenreList = genreService.getPageBookOfGenre(genre_old,0,5).getContent();
        List<Book>bookList = new ArrayList<>();
        for(Book2Genre b2:book2GenreList){
            bookList.add(b2.getBook());
        }
        model.addAttribute("listBook",bookList);
        model.addAttribute("count",genreService.getListBookOfGenre(genreService.getMapBook2Genre(), gen).size());
        return "/genres/slug.html";
    }

    @GetMapping("/slug/gen/{gen}")
    @ResponseBody
    public BooksPageDto getNextGenrePage(@RequestParam("offset") Integer offset,
                                         @RequestParam("limit") Integer limit){
        List<Book2Genre>book2GenreList = genreService.getPageBookOfGenre(genre_old,offset,limit).getContent();
        List<Book>bookList = new ArrayList<>();
        for(Book2Genre b2:book2GenreList){
            bookList.add(b2.getBook());
        }
        return new BooksPageDto(bookList);
    }
}
