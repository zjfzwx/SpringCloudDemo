package com.itlaoqi.springcloud.book.repository;

import com.itlaoqi.springcloud.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long>{

}
