package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.dto.BookDto;
import com.example.LibraryManagementSystem.exception.ResourceNotFoundException;
import com.example.LibraryManagementSystem.mapper.BookMapper;
import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.repository.BookRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;


    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public void insertBook(@Valid BookDto bookDto) {
        Book book= BookMapper.ConvertDtoToEntity(bookDto);
        bookRepository.save(book);
    }

    public Book getBookByIsbn(String isbn) {
        return bookRepository.findBookByIsbn(isbn)
                .orElseThrow(() -> new ResourceNotFoundException("Book with ISBN " + isbn + " not found"));
    }

    public void updateBook(Long id, @Valid BookDto bookDto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));
        book.setTitle(bookDto.title());
        book.setAuthor(bookDto.author());
        book.setPublicationYear(bookDto.publicationYear());
        bookRepository.save(book);

    }

    public void deleteByIsbn(String isbn) {
        Book book =bookRepository.findBookByIsbn(isbn).orElseThrow(()-> new ResourceNotFoundException("Book with ISBN " + isbn + " not found"));
        bookRepository.delete(book);
    }
}
