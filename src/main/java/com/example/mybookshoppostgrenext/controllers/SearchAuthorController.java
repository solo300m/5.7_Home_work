package com.example.mybookshoppostgrenext.controllers;

import com.example.mybookshoppostgrenext.Service.AuthorService;
import com.example.mybookshoppostgrenext.data.Book;
import com.example.mybookshoppostgrenext.Service.BookService;
import com.example.mybookshoppostgrenext.data.BooksPageDto;
import com.example.mybookshoppostgrenext.data.SearchWordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchAuthorController {
    private final BookService bookService;
    private AuthorService authorService;

    @Autowired
    public SearchAuthorController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto(){
        return new SearchWordDto();
    }
    @ModelAttribute("searchResults")
    public List<Book> searchResults(){
        return new ArrayList<>();
    }

    /*@GetMapping("/search")
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
    }*/
    @GetMapping(value = {"/search","/search/{searchWord}"})
    public String getSearchResults(@PathVariable(value = "searchWord", required = false) SearchWordDto searchWordDto,
                                   Model model){
        model.addAttribute("searchWordDto",searchWordDto);
        model.addAttribute("searchResults", bookService.getPageOfSearchResultBooks(searchWordDto.getExample(),0,5).getContent());
        return "/search/index";
    }
    @GetMapping("/search/page/{searchWord}")
    @ResponseBody
    public BooksPageDto getNextSearchPage(@RequestParam("offset") Integer offset,
                                          @RequestParam("limit") Integer limit,
                                          @PathVariable(value = "searchWord", required = false) SearchWordDto searchWordDto){
        return new BooksPageDto(bookService.getPageOfSearchResultBooks(searchWordDto.getExample(),offset,limit).getContent());
    }
}
