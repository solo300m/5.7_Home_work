package com.example.mybookshoppostgrenext.data;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="books")
/*@AttributeOverride(name = "id",
column = @Column(name = "id_author", nullable = false))*/
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate pub_date;
    private Byte is_bestseller;
    private String slug;
    private String image;
    @Type(type="text")
    private String discription;
    //private Integer id_author;
    //@Transient
    @ManyToOne
    @JoinColumn(name = "id_author", referencedColumnName = "id")
    private Authors author;
    @OneToMany(mappedBy = "book")
    private List<Book2Author>book2Authors = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    private List<Book_Review>bookReviews = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    private List<Book2User>book2Users = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    private List<Book2Genre>book2Genres = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    private List<File_Download>file_downloads = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    private List<Balance_Transaction>balance_transactions = new ArrayList<>();

    private String title;
    private String priceOld;
    private String price;

    public Book() {
    }

    public Book(LocalDate pub_date, Byte is_bestseller,
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
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getPub_date() {
        return pub_date;
    }

    public void setPub_date(LocalDate pub_date) {
        this.pub_date = pub_date;
    }

    public Byte getIs_bestseller() {
        return is_bestseller;
    }

    public void setIs_bestseller(Byte is_bestseller) {
        this.is_bestseller = is_bestseller;
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

    public String getPriceOld() {
        return priceOld;
    }

    public void setPriceOld(String priceOld) {
        this.priceOld = priceOld;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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
                ", priceOld='" + priceOld + '\'' +
                ", price='" + price + '\'';
    }
}
