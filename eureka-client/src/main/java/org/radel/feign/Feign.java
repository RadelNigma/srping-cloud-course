package org.radel.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.radel.model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name = "book-service")
@CircuitBreaker(name = "book-service", fallbackMethod = "fallback")
public interface Feign {
    @GetMapping("/api/books")
    List<Book> getAllBooks();

    static List<Book> fallback(Throwable ex) {
        final Logger log = LogManager.getLogger(Feign.class);
        log.info( ex.getMessage());
        List<Book> list = new ArrayList<>();
        list.add(new Book("Error", "the book service is not available", "using fallback", "@CircuitBreaker"));
        return list;
    }
}


