package com.itlaoqi.springcloud.book.repository;

import com.itlaoqi.springcloud.book.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepository  extends JpaRepository<Borrow , Long>{
}
