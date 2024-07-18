package com.bookstore.jpa.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.jpa.dtos.BookRecordDTO;
import com.bookstore.jpa.models.BookModel;
import com.bookstore.jpa.services.BookService;

@RestController
@RequestMapping("/bookstore/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookModel> save( @RequestBody BookRecordDTO bookRecordDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveBook(bookRecordDTO));
    }

    @GetMapping
    public ResponseEntity<List<BookModel>> list(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id){
        bookService.deleteBookById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Book deleted successfully");
    }
}
