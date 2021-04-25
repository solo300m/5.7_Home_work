package com.example.mybookshoppostgrenext.controllers;

import com.example.mybookshoppostgrenext.Service.AuthorService;
import com.example.mybookshoppostgrenext.Service.BookService;
import com.example.mybookshoppostgrenext.Service.GenreService;
import com.example.mybookshoppostgrenext.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
//@RequestMapping("/genres")
public class GenresPageController {
    private BookService bookService;
    private AuthorService authorService;
    private GenreService genreService;
    private Integer genre_old = 0;
    private Integer offset = 0;
    private Integer limit = 5;
    private boolean flag = true;
    SearchIdDto searchIdDto = new SearchIdDto();
    List<Book2Genre>listBook = new ArrayList<>();

    @Autowired
    public GenresPageController(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }
    @ModelAttribute("searchIdDto")
    public SearchIdDto searchIdDto(){
        return new SearchIdDto();
    }
    @ModelAttribute("listBook")
    public List<Book2Genre>listBook(){
        return new ArrayList<>();
    }

    @GetMapping("/genres")
    public String rederectGenres(Model model){
        model.addAttribute("genre",bookService.getGenreAll());
        model.addAttribute("b2g",genreService.getMapBook2Genre());
        Logger.getLogger(GenresPageController.class.getName()).info("Reload genres page");
        return "/genres/index";
    }
    @GetMapping("/genres/bookshop")
    public String mainOpen(Model model){
        Logger.getLogger(GenresPageController.class.getName()).info("Back on the main-page");
        model.addAttribute("bookData", bookService.getBookData());
        return "index.html";
    }
    @GetMapping("/genres/authors")
    public String authorPage(Model model){
        Logger.getLogger(MainPageController.class.getName()).info("Opened page authors from Genres_Page");
        model.addAttribute("authors",authorService.getMapAuthors(authorService.getAuthorsList()));
        return "/authors/index.html";
    }

    @GetMapping("/books/{genre}")
    public String genreSlugPage(@PathVariable(value = "genre",required = false) Integer genre, Model model){
        genre_old = genre;
        listBook.clear();
        offset = 0;
        model.addAttribute("gen",genreService.getGenreOfId(genre));
        //model.addAttribute("listBook",genreService.getListBookOfGenre(genreService.getMapBook2Genre(), genre));
        //model.addAttribute("listBook",genreService.getPageBookOfGenre(genre_old,0,5));
//        List<Book2Genre>book2GenreList = genreService.getPageBookOfGenre(genre_old,0,5).getContent();
//        List<Book>bookList = new ArrayList<>();
//        for(Book2Genre b2:book2GenreList){
//            bookList.add(b2.getBook());
//        }
        searchIdDto.setIdParam(genre);
        listBook.addAll(genreService.getPageBookOfGenre(genre_old,offset,limit).getContent());
        model.addAttribute("searchIdDto",searchIdDto);
        model.addAttribute("listBook",listBook);
        model.addAttribute("count",genreService.getListBookOfGenre(genreService.getMapBook2Genre(), genre).size());
        if((genreService.getPageBookOfGenre(genre_old,offset,limit).getContent().size())==limit){
            ++offset;
            if((genreService.getPageBookOfGenre(genre_old,offset,limit).getContent().size())==0)
                --offset;
        }
        return "/genres/slug.html";
    }
    @GetMapping("/next/page/")
    public String getNextPageIdGenre(Model model){
        if(flag) {
            listBook.addAll(genreService.getPageBookOfGenre(genre_old, offset, limit).getContent());
            flag = false;
        }
        model.addAttribute("gen",genreService.getGenreOfId(genre_old));
        model.addAttribute("listBook", listBook);
        model.addAttribute("searchIdDto",searchIdDto);
        model.addAttribute("count",genreService.getListBookOfGenre(genreService.getMapBook2Genre(), genre_old).size());
        if((genreService.getPageBookOfGenre(genre_old,offset,limit).getContent().size())==limit &&
                (genreService.getPageBookOfGenre(genre_old,offset+1,limit).getContent().size())!=0){
             ++offset;
            flag = true;
        }
        return "/genres/slug.html";
    }

    /*@GetMapping("/books/gonre/{genre}")
    @ResponseBody
    public Book2GenrePageDto getNextGenrePage(@RequestParam("offset") Integer offset,
                                         @RequestParam("limit") Integer limit,
                                         @PathVariable(value = "genre",required = false) Integer genre){
//        List<Book2Genre>book2GenreList = genreService.getPageBookOfGenre(genre.getIdParam(),offset,limit).getContent();
//        List<Book>bookList = new ArrayList<>();
//        for(Book2Genre b2:book2GenreList){
//            bookList.add(b2.getBook());
//        }
        return new Book2GenrePageDto(genreService.getPageBookOfGenre(searchIdDto.getIdParam(),offset,limit).getContent());
    }*/
}
