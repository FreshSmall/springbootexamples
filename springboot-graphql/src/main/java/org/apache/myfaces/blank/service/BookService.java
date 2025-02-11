package org.apache.myfaces.blank.service;

import org.apache.myfaces.blank.entity.Author;
import org.apache.myfaces.blank.entity.Book;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: yinchao
 * @ClassName: BookService
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/2/11 17:10
 */
@Service
public class BookService {

    private final Map<Integer, Book> booksRepository = new HashMap<>();
    private final Map<Integer, Author> authorRepository = new HashMap<>();

    public BookService() {
        booksRepository.put(1, new Book("book-1","Anna Karenina",10,"author-1"));
        booksRepository.put(2, new Book("book-2","Madame Bovary",12,"author-2"));
        booksRepository.put(3, new Book("book-3","War and Peace",14,"author-3"));
        booksRepository.put(4, new Book("book-4","The Great Gatsby",16,"author-4"));
        booksRepository.put(5, new Book("book-5","Lolita",18,"author-5"));

        authorRepository.put(1, new Author("author-1", "Joanne-1", "Rowling-1"));
        authorRepository.put(2, new Author("author-2", "Joanne-2", "Rowling-2"));
        authorRepository.put(3, new Author("author-3", "Joanne-3", "Rowling-3"));
        authorRepository.put(4, new Author("author-4", "Joanne-4", "Rowling-4"));
        authorRepository.put(5, new Author("author-5", "Joanne-5", "Rowling-5"));
    }

    public List<Author> fetchAuthor(Book book) {
        System.out.println("方法开始执行");
        return authorRepository
                .values().
                stream()
                .filter(rating -> rating.getId().equals(book.getAuthorId())).collect(Collectors.toList());
    }

    public List<Book> fetchBooks() {
        return new ArrayList<>(booksRepository.values());
    }
}
