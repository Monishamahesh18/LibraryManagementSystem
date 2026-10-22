package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.dto.BookDto;
import com.example.LibraryManagementSystem.exception.ResourceNotFoundException;
import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    public void getAllBooksTest() {
        Book book1 = new Book("Clean Code", "Robert C. Martin", "9780132350884", 2008);
        Book book2 = new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", "9780747532699", 1997);

        when(bookRepository.findAll()).thenReturn(List.of(book1, book2));

        assertEquals(2, bookService.getAllBooks().size());

        verify(bookRepository, times(1)).findAll();
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void getBookByIsbnTest() {
        Book book = new Book("Clean Code", "Robert C. Martin", "9780132350884", 2008);

        when(bookRepository.findBookByIsbn("9780132350884")).thenReturn(Optional.of(book));

        Book result = bookService.getBookByIsbn("9780132350884");

        assertEquals("Clean Code", result.getTitle());
        assertEquals("Robert C. Martin", result.getAuthor());

        verify(bookRepository, times(1)).findBookByIsbn("9780132350884");
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void getBookByIsbnTest_Invalid() {
        when(bookRepository.findBookByIsbn("0000000000")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> bookService.getBookByIsbn("0000000000"));

        verify(bookRepository, times(1)).findBookByIsbn("0000000000");
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void insertBookTest() {
        // mock: this info normally comes from postman via the controller
        BookDto bookDto = new BookDto("Clean Code", "Robert C. Martin", "9780132350884", 2008);

        // when save is called, don't go to DB, just assume it saved
        when(bookRepository.save(any(Book.class))).thenReturn(new Book());

        // actual call
        bookService.insertBook(bookDto);

        // the call still happens even though it is mocked, so verify it
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    public void updateBookTest() {
        Book existing = new Book("Old Title", "Old Author", "9780132350884", 2000);
        existing.setId(1L);
        BookDto bookDto = new BookDto("New Title", "New Author", "9780132350884", 2008);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(bookRepository.save(any(Book.class))).thenReturn(existing);

        bookService.updateBook(1L, bookDto);

        assertEquals("New Title", existing.getTitle());
        assertEquals("New Author", existing.getAuthor());
        assertEquals(2008, existing.getPublicationYear());

        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).save(any(Book.class));
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void updateBookTest_Invalid() {
        BookDto bookDto = new BookDto("New Title", "New Author", "9780132350884", 2008);

        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> bookService.updateBook(99L, bookDto));

        verify(bookRepository, times(1)).findById(99L);
        verify(bookRepository, times(0)).save(any(Book.class));
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void deleteByIsbnTest() {
        Book book = new Book("Clean Code", "Robert C. Martin", "9780132350884", 2008);

        when(bookRepository.findBookByIsbn("9780132350884")).thenReturn(Optional.of(book));

        bookService.deleteByIsbn("9780132350884");

        verify(bookRepository, times(1)).findBookByIsbn("9780132350884");
        verify(bookRepository, times(1)).delete(book);
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void deleteByIsbnTest_Invalid() {
        when(bookRepository.findBookByIsbn("0000000000")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> bookService.deleteByIsbn("0000000000"));

        verify(bookRepository, times(1)).findBookByIsbn("0000000000");
        verify(bookRepository, times(0)).delete(any(Book.class));
        verifyNoMoreInteractions(bookRepository);
    }
}