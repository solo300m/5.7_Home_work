package com.example.mybookshoppostgrenext.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="books")
@ApiModel("data model of books entity")
/*@AttributeOverride(name = "id",
column = @Column(name = "id_author", nullable = false))*/
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id of books",position = 1)
    private Integer id;
    @Type(type="org.hibernate.type.LocalDateType")
    @ApiModelProperty(value = "date enter of book to the database", position = 2)
    private LocalDate date;
    @ApiModelProperty(value = "1- book is bestseller, 0-it is not",position = 3)
    private Byte isbestseller;
    private String slug;
    private String image;
    @Type(type="text")
    private String discription;
    //private Integer id_author;
    //@Transient
    @ManyToOne
    @JoinColumn(name = "id_author", referencedColumnName = "id")
    @ApiModelProperty(value = "Author object", position = 5)
    private Authors author;

    @ManyToOne
    @JoinColumn(name = "idteg",referencedColumnName = "idteg")
    private Tag tag;

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<Book2Author>book2Authors = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<Book_Review>bookReviews = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<Book2User>book2Users = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<Book2Genre>book2Genres = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<File_Download>file_downloads = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<Balance_Transaction>balance_transactions = new ArrayList<>();

    @ApiModelProperty(value = "title of the book", position = 4)
    private String title;
    private Double priceOld;
    //@Column(name = "discount")
    private Double price;

    public Book() {
    }

    /*public Book(LocalDate pub_date, Byte is_bestseller,
                String slug, String image, String discription,
                Authors author, List<Book2Author> book2Authors,
                List<Book_Review> bookReviews, List<Book2User> book2Users,
                List<Book2Genre> book2Genres, List<File_Download> file_downloads,
                List<Balance_Transaction> balance_transactions, String title, String priceOld, String price) {
        this.pub_date = pub_date;
        this.is_bestseller = is_bestseller;
        this.slug = slug;
        this.image = image;
        this.discription = discription;
        this.author = author;
        this.book2Authors = book2Authors;
        this.bookReviews = bookReviews;
        this.book2Users = book2Users;
        this.book2Genres = book2Genres;
        this.file_downloads = file_downloads;
        this.balance_transactions = balance_transactions;
        this.title = title;
        this.priceOld = priceOld;
        this.price = price;
    }*/

    public Integer getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Byte getIsbestseller() {
        return isbestseller;
    }

    public void setIsbestseller(Byte isbestseller) {
        this.isbestseller = isbestseller;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public Authors getAuthor() {
        return author;
    }

    public void setAuthor(Authors author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPriceOld() {
        return priceOld;
    }

    public void setPriceOld(Double priceOld) {
        this.priceOld = priceOld;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Book2Author> getBook2Authors() {
        return book2Authors;
    }

    public void setBook2Authors(List<Book2Author> book2Authors) {
        this.book2Authors = book2Authors;
    }

    public List<Book_Review> getBookReviews() {
        return bookReviews;
    }

    public void setBookReviews(List<Book_Review> bookReviews) {
        this.bookReviews = bookReviews;
    }

    public List<Book2User> getBook2Users() {
        return book2Users;
    }

    public void setBook2Users(List<Book2User> book2Users) {
        this.book2Users = book2Users;
    }

    public List<Book2Genre> getBook2Genres() {
        return book2Genres;
    }

    public void setBook2Genres(List<Book2Genre> book2Genres) {
        this.book2Genres = book2Genres;
    }

    public List<File_Download> getFile_downloads() {
        return file_downloads;
    }

    public void setFile_downloads(List<File_Download> file_downloads) {
        this.file_downloads = file_downloads;
    }

    public List<Balance_Transaction> getBalance_transactions() {
        return balance_transactions;
    }

    public void setBalance_transactions(List<Balance_Transaction> balance_transactions) {
        this.balance_transactions = balance_transactions;
    }

    @Override
    public String toString() {
        return "Book id= " + id +
                ", id_author=" + author.getId() +
                ", author='" + author.getAuthor() + '\'' +
                ", title='" + title + '\'' +
                ", priceOld='" + priceOld.toString() + '\'' +
                ", price='" + price.toString() + '\'';
    }
}
