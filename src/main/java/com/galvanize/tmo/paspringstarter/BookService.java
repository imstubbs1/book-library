package com.galvanize.tmo.paspringstarter;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        Book book = new Book();
        books.add(book);
        return books;
    }

    public Book saveBook(Book book, int id) {
        book.setId(id);
        book.setAuthor(book.getAuthor());
        book.setTitle(book.getTitle());
        book.setYearPublished(book.getYearPublished());
        return book;
    }
}
