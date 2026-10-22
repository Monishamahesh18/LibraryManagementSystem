package com.example.LibraryManagementSystem.mapper;

import com.example.LibraryManagementSystem.dto.BookDto;
import com.example.LibraryManagementSystem.model.Book;

public class BookMapper {
    public static Book ConvertDtoToEntity(BookDto bookDto){
        Book book= new Book();
        book.setTitle(bookDto.title());
        book.setAuthor(bookDto.author());
        book.setIsbn(bookDto.isbn());
        book.setPublicationYear(bookDto.publicationYear());
        return book;
    }
}
