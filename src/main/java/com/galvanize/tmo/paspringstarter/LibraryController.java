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

    @DeleteMapping("/api/books/")
    public ResponseEntity<Object> deleteBooks() {
        library.clear();
        books.clear();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/api/books/")
    public ResponseEntity<Object> getBooks() {
        return new ResponseEntity<>(library, HttpStatus.OK);
    }

    @PostMapping("/api/books/")
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
