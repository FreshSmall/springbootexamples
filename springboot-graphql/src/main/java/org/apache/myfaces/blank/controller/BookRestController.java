package org.apache.myfaces.blank.controller;

import org.apache.myfaces.blank.entity.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: yinchao
 * @ClassName: BookRestController
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/2/11 10:52
 */
@RestController
public class BookRestController {


    @GetMapping("/book")
    public List<Book> getBookById(@RequestParam String id) {
        return Book.getById(id);
    }
}
