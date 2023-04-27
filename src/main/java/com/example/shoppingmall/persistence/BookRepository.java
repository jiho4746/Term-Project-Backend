package com.example.shoppingmall.persistence;

import com.example.shoppingmall.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, String> {
    List<BookEntity> findByUserId(String userId); //파싱하여 자동으로 쿼리를 작성한 후 실행함
}
