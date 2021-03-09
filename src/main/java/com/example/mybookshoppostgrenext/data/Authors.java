package com.example.mybookshoppostgrenext.data;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class Authors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String author;
    private String groupId;
    @Type(type = "text")
    private String biography;
    private String photo;

    @OneToMany(mappedBy = "author")
    private List<Book> bookList = new ArrayList<>();
    @OneToMany(mappedBy = "authors")
    private List<Book2Author>book2Authors = new ArrayList<>();



    public List<Book> getBookList(){
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public Authors(){

    }

    public Authors(Integer id, String author, String groupId, String biography, String photo, List<Book> bookList) {
        this.id = id;
        this.author = author;
        this.groupId = groupId;
        this.biography = biography;
        this.photo = photo;
        this.bookList = bookList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Authors{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", bookList=" + bookList +
                '}';
    }
}
