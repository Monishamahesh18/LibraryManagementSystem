package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.dto.BookDto;
import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("api/book")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }


    @PostMapping("api/book/add")
    public void addBook(@Valid @RequestBody BookDto bookDto){
        bookService.insertBook(bookDto);
    }

    @GetMapping("/api/book/{isbn}")
    public Book getBookByIsbn(@PathVariable String isbn) {
        return bookService.getBookByIsbn(isbn);
    }

    @PutMapping("/api/book/update/{id}")
    public void updateBook(@PathVariable Long id,@Valid @RequestBody BookDto bookDto){
        bookService.updateBook(id, bookDto);
    }

    @DeleteMapping("/api/book/delete/{isbn}")
    public void deleteByIsbn(@PathVariable String isbn){
        bookService.deleteByIsbn(isbn);
    }



}
