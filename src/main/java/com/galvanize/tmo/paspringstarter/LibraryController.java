package com.galvanize.tmo.paspringstarter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class LibraryController {

    @Autowired
    private BookService bookService;

    HashMap<String, Object> library;

    ArrayList<Book> books = new ArrayList<Book>();

    int id;

    LibraryController() {
        library = new HashMap<String, Object>();
        id = 0;
    }

    @GetMapping("/health")
    public void health() {
    }

    @DeleteMapping("/api/book/")
    public ResponseEntity<Object> deleteBooks() {
        library.clear();
        books.clear();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/api/book/")
    public ResponseEntity<Object> getBooks() {
        return new ResponseEntity<>(library, HttpStatus.OK);
    }

    @PostMapping("/api/book/")
    public <T> ResponseEntity<BookService> saveBook(@RequestBody Book book) {
        id++;
        book = this.bookService.saveBook(book, id);
        System.out.println(library);
        books.add(book);
        sort(books);
        library.clear();
        library.put("books", books);

        return (ResponseEntity<BookService>) new ResponseEntity<T>((T) book, HttpStatus.CREATED);
    }

    public static void sort(ArrayList<Book> list)
    {
        list.sort(Comparator.comparing(Book::getTitle));
    }

}

class Book {
    private Integer id;
    private String author;
    private String title;
    private String yearPublished;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(String yearPublished) {
        this.yearPublished = yearPublished;
    }
}
