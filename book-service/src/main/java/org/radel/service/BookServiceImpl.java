package org.radel.service;

import org.radel.model.Book;
import org.radel.repository.BookRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepo bookRepo;

    public BookServiceImpl(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    private static final List<Book> items = new ArrayList<>();

    static {
        items.add(new Book("1", "Мастер и Маргарита", "драма", "sss"));
        items.add(new Book("2", "Wind", "blockbusters", "eee"));
    }

    @Override
    @Transactional
    public List<Book> findAll() {
        return bookRepo.findAll();
    }

    @PostConstruct
    public void init() {
        bookRepo.saveAll(items);
    }
}

