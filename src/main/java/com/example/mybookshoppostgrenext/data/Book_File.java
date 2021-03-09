package com.example.mybookshoppostgrenext.data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book_file")
public class Book_File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String hash;

    @ManyToOne
    @JoinColumn(name = "type_id",referencedColumnName = "id")
    private Book_File_type book_file_type;
    //private Integer type_id;
    private String path;

    public Book_File() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Book_File_type getBook_file_type() {
        return book_file_type;
    }

    public void setBook_file_type(Book_File_type book_file_type) {
        this.book_file_type = book_file_type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
