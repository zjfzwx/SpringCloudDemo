package com.itlaoqi.springcloud.book.service;

import com.itlaoqi.springcloud.book.client.MemberClient;
import com.itlaoqi.springcloud.book.client.MemberDTO;
import com.itlaoqi.springcloud.book.client.MemberResult;
import com.itlaoqi.springcloud.book.entity.Book;
import com.itlaoqi.springcloud.book.entity.Borrow;
import com.itlaoqi.springcloud.book.repository.BookRepository;
import com.itlaoqi.springcloud.book.repository.BorrowRepository;
import com.itlaoqi.springcloud.book.service.exception.BookNotFoundException;
import com.itlaoqi.springcloud.book.service.exception.MemberNotFoundException;
import com.itlaoqi.springcloud.book.service.exception.NotEnoughStockException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Resource
    private BookRepository bookRepository;
    @Resource
    private BorrowRepository borrowRepository;
    @Resource
    private MemberClient memberClient;

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Book getInfo(Long bid){
        Optional<Book> optional =  bookRepository.findById(bid);
        Book book = null;
        if(optional.isPresent()){
            book = optional.get();
        }else{
            throw new BookNotFoundException("BOOKID:" + bid + " not found");
        }
        return book;

    }
    @Transactional
    public void borrow(Long bid , String mobile , Date takeDate , Date returnDate){
        Optional<Book> optional = bookRepository.findById(bid);
        Book book = null;
        if(!optional.isPresent()){
            throw new BookNotFoundException("BOOKID:" + bid + " not found");
        }else{
            book = optional.get();
        }
        if(book.getStock() == 0){
            throw new NotEnoughStockException("Stock Not Enough");
        }
        MemberDTO memberDTO = null;
        MemberResult mr = memberClient.checkMobile(mobile);
        if(mr.getCode().equals("0")){
            memberDTO = mr.getData();
        }else{
            throw new MemberNotFoundException("Member Not Found");
        }

        Borrow borrow = new Borrow();
        borrow.setBid(bid);
        borrow.setMid(memberDTO.getMid());
        borrow.setTakedate(takeDate);
        borrow.setReturndate(returnDate);
        borrow.setCreatetime(new Date());
        borrowRepository.saveAndFlush(borrow);
        book.setStock(book.getStock() - 1);
        bookRepository.saveAndFlush(book);
    }
}
