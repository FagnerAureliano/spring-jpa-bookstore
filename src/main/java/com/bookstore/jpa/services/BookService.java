package com.bookstore.jpa.services;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.jpa.dtos.BookRecordDTO;
import com.bookstore.jpa.models.BookModel;
import com.bookstore.jpa.models.ReviewModel;
import com.bookstore.jpa.repositories.AuthorRepository;
import com.bookstore.jpa.repositories.BookRepository;
import com.bookstore.jpa.repositories.PublisherRepository;


@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }
    @Transactional
    public BookModel saveBook(BookRecordDTO bookRecordDTO) {
        BookModel bookModel = new BookModel();
        bookModel.setTitle(bookRecordDTO.title());
        bookModel.setPublisher(publisherRepository.findById(bookRecordDTO.publisherId()).get());
        bookModel.setAuthors(authorRepository.findAllById(bookRecordDTO.authorIds()).stream().collect(Collectors.toSet()));

        ReviewModel reviewModel = new ReviewModel();
        reviewModel.setComment(bookRecordDTO.reviewComment());
        reviewModel.setBook(bookModel);
        bookModel.setReview(reviewModel);

        return bookRepository.save(bookModel);
    }


}
