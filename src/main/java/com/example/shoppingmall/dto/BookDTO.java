package com.example.shoppingmall.dto;

import com.example.shoppingmall.model.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

public class BookDTO {
    private String id;
    private String title;
    private boolean done;
    private String userId;

    public BookDTO(final BookEntity entity){ //BookEntity를 받아서 생성됨
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.done = entity.isDone();
        this.userId = entity.getUserId();
        //useId는 숨김
    }

    //컨트롤러는 BookDTO를 클라이언트로부터 받아서 BookEntity로 변환해서 저장해야 함 -> toEntity( )가 필요
    public static BookEntity todoEntity(final BookDTO dto){
        return BookEntity.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .done(dto.isDone())
                .userId(dto.getUserId())
                .build();
    }
}
