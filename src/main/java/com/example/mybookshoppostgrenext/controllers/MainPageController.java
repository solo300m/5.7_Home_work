package com.example.mybookshoppostgrenext.controllers;

import com.example.mybookshoppostgrenext.Service.AuthorService;
import com.example.mybookshoppostgrenext.data.Book;
import com.example.mybookshoppostgrenext.Service.BookService;
import com.example.mybookshoppostgrenext.Service.HibernateService;
import com.example.mybookshoppostgrenext.data.BooksPageDto;
import com.example.mybookshoppostgrenext.data.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

@Controller
//@RequestMapping("/bookshop")
public class MainPageController {

    private final BookService bookService;
    private AuthorService authorService;
    private HibernateService hibernateService;
    private Integer teg_old = 0;

    @Autowired
    public MainPageController(BookService bookService, AuthorService authorService, HibernateService hibernateService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.hibernateService = hibernateService;
    }

    @ModelAttribute("searchResults")
    public List<Book> searchResults(){
        return new ArrayList<>();
    }

    @ModelAttribute("recommendedBooks")
    public List<Book> recommendedBooks(){
        //bookService.setAuthorsData(bookService.getBookData());
        //bookService.updateBookIdAuthors();
        return bookService.getPageOfRecommendedBooks(0,6).getContent();
    }

    @ModelAttribute("newBooks")
    public List<Book> newBooks(){
        return bookService.getPageOfNewBooks(0,6).getContent();
    }

    @ModelAttribute("bestseller")
    public List<Book> bestsellerBook(){
        return bookService.getPageOfBestseller(0,6).getContent();
    }

    @ModelAttribute("tegs")
    public List<Tag> tegsBook(){

        return bookService.getTegs();
    }

    @GetMapping("/")
    public String reloadMainPage(Model model){
        Map<Tag,List<Book>> tagListMap = bookService.getMapTegs(bookService.getBookData());
        model.addAttribute("tagmap",tagListMap);
        model.addAttribute("avrg",bookService.averigMatTegListSize(tagListMap));
        Logger.getLogger(MainPageController.class.getName()).info("Reload great page!");
        return "index";
    }

    @GetMapping("/books/recommended")
    @ResponseBody
    public BooksPageDto getBooksPage(@RequestParam("offset") Integer offset,
                                     @RequestParam("limit") Integer limit){
      return new BooksPageDto(bookService.getPageOfRecommendedBooks(offset,limit).getContent());
    }

    @GetMapping("/books/recent")
    @ResponseBody
    public BooksPageDto getNewBooksPage(@RequestParam("offset") Integer offset,
                                        @RequestParam("limit") Integer limit){
        return new BooksPageDto(bookService.getPageOfNewBooks(offset,limit).getContent());
    }

    @GetMapping("/books/popular")
    @ResponseBody
    public BooksPageDto getBestsellersPage(@RequestParam("offset") Integer offset,
                                           @RequestParam("limit") Integer limit){
        return new BooksPageDto(bookService.getPageOfBestseller(offset, limit).getContent());
    }

    @GetMapping("/bookshop")
    public String mainPage(Model model){
        model.addAttribute("book2author",hibernateService.getBookData2());
        Map<Tag,List<Book>> tagListMap = bookService.getMapTegs(bookService.getBookData());
        model.addAttribute("tagmap",tagListMap);
        model.addAttribute("avrg",bookService.averigMatTegListSize(tagListMap));
        Logger.getLogger(MainPageController.class.getName()).info("Hy, it is great page!");
        return "index";
    }
    @GetMapping("/bookshop/genres")
    public String genresPage(Model model){
        model.addAttribute("genre",bookService.getGenreAll());
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

    @GetMapping("/bookshop/{teg}")
    public String tegPage(@PathVariable("teg") Integer teg, Model model){
        teg_old = teg;
        model.addAttribute("searchResults", bookService.getPageOfSearchBooksTeg(teg_old,0,5).getContent());
        model.addAttribute("tagName", bookService.getTegName(teg));
        model.addAttribute("count",bookService.getBookOfTeg(teg_old).size());
        Logger.getLogger(MainPageController.class.getName()).info("Open teg page");
        return "/tags/index.html";
    }

    @GetMapping("/books/tag/{teg}")
    @ResponseBody
    public BooksPageDto getNextTagsPage(@RequestParam("offset")Integer offset,//@PathVariable(value = "teg" ,required = false) Integer teg
                                        @RequestParam("limit") Integer limit){
        //teg = teg_old;
        //model.addAttribute("searchResults", bookService.getPageOfSearchBooksTeg(teg_old,0,5).getContent());
        return new BooksPageDto(bookService.getPageOfSearchBooksTeg(teg_old,offset,limit).getContent());
    }

}
