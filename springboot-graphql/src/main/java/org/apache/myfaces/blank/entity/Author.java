package org.apache.myfaces.blank.entity;

import java.util.Arrays;
import java.util.List;

/**
 * @author: yinchao
 * @ClassName: Author
 * @Description:
 * @team wuhan operational dev.
 * @date: 2022/12/27 16:51
 */
public class Author {

    private String id;
    private String firstName;
    private String lastName;

    public Author(String id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    private static List<Author> authors = Arrays.asList(
            new Author("author-1", "Joanne", "Rowling"),
            new Author("author-2", "Herman", "Melville"),
            new Author("author-3", "Anne", "Rice")
    );

    public static Author getById(String id) {
        return authors.stream().filter(author -> author.getId().equals(id)).findFirst().orElse(null);
    }

    public String getId() {
        return id;
    }
}
