package com.project.library_managment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.library_managment.models.Book;



public interface BookRepository extends JpaRepository<Book, Long> {}