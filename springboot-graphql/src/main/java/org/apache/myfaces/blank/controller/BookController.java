package org.apache.myfaces.blank.controller;

import org.apache.myfaces.blank.entity.Author;
import org.apache.myfaces.blank.entity.Book;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;


/**
 * @author: yinchao
 * @ClassName: BookController
 * @Description:
 * @team wuhan operational dev.
 * @date: 2022/12/27 16:29
 */
@Controller
public class BookController {

    @QueryMapping
    public Book bookById(@Argument String id) {
        return Book.getById(id);
    }

    @SchemaMapping
    public Author author(Book book) {
        return Author.getById(book.getAuthorId());
    }
}
