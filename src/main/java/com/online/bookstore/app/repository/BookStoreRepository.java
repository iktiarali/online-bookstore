package com.online.bookstore.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.online.bookstore.app.entity.Book;

@Repository
public interface BookStoreRepository extends JpaRepository<Book, Long>{
	
}
