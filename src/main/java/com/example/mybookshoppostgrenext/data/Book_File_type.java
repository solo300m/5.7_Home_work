package com.example.mybookshoppostgrenext.data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

enum Fyle_Type{
    PDF,
    EPUB,
    FB2
}
@Entity
@Table(name = "book_file_type")
public class Book_File_type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Fyle_Type name;
    @Type(type = "text")
    private String discription;
    @OneToMany(mappedBy = "book_file_type")
    private List<Book2User>book2Users = new ArrayList<>();
    @OneToMany(mappedBy = "book_file_type")
    private List<Book_File> book_files = new ArrayList<>();

    public Book_File_type() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Fyle_Type getName() {
        return name;
    }

    public void setName(Fyle_Type name) {
        this.name = name;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
}
