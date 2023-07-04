package org.radel.restcontroller;

import org.radel.model.Book;
import org.radel.service.BookServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookRestController {

    private final BookServiceImpl bookService;

    public BookRestController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }
}
