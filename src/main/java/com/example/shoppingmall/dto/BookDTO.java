package com.example.demo.dto;

import com.example.demo.model.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

public class BookDTO {
    private String title;
    private String author;
    private String publisher;

    public BookDTO(final BookEntity entity){ //BookEntity를 받아서 생성됨
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.publisher = entity.getPublisher();
        //useId는 숨김
    }

    //컨트롤러는 BookDTO를 클라이언트로부터 받아서 BookEntity로 변환해서 저장해야 함 -> toEntity( )가 필요
    public static BookEntity todoEntity(final BookDTO dto){
        return BookEntity.builder()
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .publisher(dto.getPublisher())
                .build();
    }
}
