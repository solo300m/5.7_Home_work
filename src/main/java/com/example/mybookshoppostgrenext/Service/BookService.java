package com.example.mybookshoppostgrenext.Service;

import com.example.mybookshoppostgrenext.Repositories.AuthorsRepository;
import com.example.mybookshoppostgrenext.Repositories.BookRepository;
import com.example.mybookshoppostgrenext.Repositories.CustomerRepository;
import com.example.mybookshoppostgrenext.data.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {

    private final JdbcTemplate jdbcTemplate;
    private BookRepository bookRepository;
    private AuthorsRepository authorsRepository;
    private CustomerRepository customerRepository;
    private HibernateService hibernateService;


    public BookService(JdbcTemplate jdbcTemplate, BookRepository bookRepository, AuthorsRepository authorsRepository, CustomerRepository customerRepository, HibernateService hibernateService) {
        this.jdbcTemplate = jdbcTemplate;
        this.bookRepository = bookRepository;
        this.authorsRepository = authorsRepository;
        this.customerRepository = customerRepository;
        this.hibernateService = hibernateService;
    }

    public List<Book> getBookData(){
        List<Book>books = bookRepository.findAll();
        /*List<Authors> authors = authorsRepository.findAll();
        for(Book b:books){
            for(Authors a: authors){
                if(b.getId_author()==a.getId())
                    b.setAuthor(a.getAuthor());
            }
        }*/
        //List<Authors>b2 = authorsRepository.customAuthorsAll();
        /*for(Book b : books)
            Logger.getLogger(BookService.class.getName()).info(b.toString());*/
        //List<Book2Authors>test2 = hibernateService.getBookData2();
        return books;
    }

    //Замена на функцию из BookRepository
    /*public List<Book> getBookData() {
       List<Book> books = jdbcTemplate.query("SELECT books.id, id_author, price, price_old, title, author\n" +
               "\tFROM public.books, public.authors WHERE id_author = authors.id",(ResultSet rs, int rowNum)->{
           Book book = new Book();
           book.setId(rs.getInt("id"));
           book.setId_author(rs.getInt("id_author"));
           book.setAuthor(rs.getString("author"));
           book.setTitle(rs.getString("title"));
           book.setPriceOld(rs.getString("price_old"));
           book.setPrice(rs.getString("price"));
           return book;
       });
       return new ArrayList<>(books);
    }*/

    //Замена на функцию из BookRepository
    /*public List<Book> getIdAuthorBook(Integer id){
        List<Book> books = jdbcTemplate.query("SELECT books.id, id_author, price, price_old, title, author " +
                "FROM books, authors WHERE id_author="+id+"",(ResultSet rs, int rowNum)->{
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setId_author(rs.getInt("id_author"));
            book.setAuthor(rs.getString("author"));
            book.setTitle(rs.getString("title"));
            book.setPriceOld(rs.getString("price_old"));
            book.setPrice(rs.getString("price"));
            return book;
        });
        return new ArrayList<>(books);
    }*/
//    public List<Authors> getAuthorsData(char a) {
//        List<Authors> authors = jdbcTemplate.query("SELECT * FROM authors WHERE author LIKE '"+a+"%'", (ResultSet rs, int rowNum) -> {
//            Authors auth = new Authors();
//            auth.setId(rs.getInt("id"));
//            auth.setAuthor(rs.getString("author"));
//            return auth;
//        });
//        if(authors.size()!=0)
//            return new ArrayList<>(authors);
//        else{
//            authors.add(new Authors("нет данных"));
//            return new ArrayList<>(authors);
//        }
//    }
    /*public List<Authors> getAuthorsList(){
        return authorsRepository.customAuthorsAll();
    }*/

}
