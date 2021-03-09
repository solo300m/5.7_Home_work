package com.example.mybookshoppostgrenext.Repositories;

import com.example.mybookshoppostgrenext.data.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {

    @Query("from Book where author.id=:id_author")
    List<Book> selectBookIdAuthor(@Param("id_author") Integer id_author);

//    @Query("select b.id, b.id_author, a.author, b.title, b.priceOld, b.price from Book as b inner join Authors a on b.id_author=a.id")
//    List<AuthorsBook> selectAllBooks();

}
