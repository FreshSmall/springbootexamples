package org.apache.myfaces.blank.config;

import graphql.schema.idl.RuntimeWiring;
import org.apache.myfaces.blank.service.BookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

/**
 * @author: yinchao
 * @ClassName: GraphQLServiceConfiguration
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/2/11 17:07
 */
@Configuration
public class GraphQLServiceConfiguration {

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer(BookService bookService) {
        return builder -> {
            builder.type(
                    "Query",
                    wiring ->
                            wiring
                                    .dataFetcher("fetchBooks", environment -> bookService.fetchBooks()));
            builder.type(
                    "Book",
                    wiring ->
                            wiring.dataFetcher("fetchAuthor", env -> bookService.fetchAuthor(env.getSource())));
        };
    }
}
