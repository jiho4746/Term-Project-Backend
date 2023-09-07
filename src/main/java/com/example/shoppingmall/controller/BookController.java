package com.example.shoppingmall.controller;



import com.example.shoppingmall.dto.BookDTO;
import com.example.shoppingmall.dto.ResponseDTO;
import com.example.shoppingmall.model.BookEntity;
import com.example.shoppingmall.security.TokenProvider;
import com.example.shoppingmall.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService service;

    // 텀프로젝트 3 (추가)
    @Autowired
    private TokenProvider tokenProvider;


    @GetMapping("/test")
    public ResponseEntity<?> testBook() {
        String str = service.testService();
        List<String> list = new ArrayList<>();
        list.add(str);
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        //ResponseEntity에 status code 리턴하도록 작성
        return ResponseEntity.ok().body(response);
    }

    // 제품 추가
    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody BookDTO dto) {
        try {
            String temporaryUserId = "ParkJiho";
            //(1) BookEntity로 변환
            BookEntity entity = BookDTO.todoEntity(dto);
            //(2) id를 null로 초기화, 생성 당시에는 id가 없어야 함
            entity.setId(null);
            //(3) 임시 사용자 아이디 설정
            entity.setUserId(temporaryUserId);
            //(4) 서비스를 이용해 엔티티 생성(서비스 계층에 요청)
            List<BookEntity> entities = service.create(entity);
            //(5) 자바 스트림을 이용해 리턴된 엔티티 리스트를 BookDTO 리스트로 변환
            List<BookDTO> dtos = entities.stream().map(BookDTO::new).collect(Collectors.toList());
            //(6) 변환된 BookDTO 리스트를 이용해 ResponseDTO를 초기화
            ResponseDTO<BookDTO> response = ResponseDTO.<BookDTO>builder().data(dtos).build();
            //(7) ResponseDTO 리턴
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            //(8) 예외가 있는 경우 dto 대신 error에 메시지를 넣어 리턴
            String error = e.getMessage();
            ResponseDTO<BookDTO> response = ResponseDTO.<BookDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    //제품 검색 (Title) - 원래 코드
    @GetMapping
    public ResponseEntity<?> searchBook(@RequestBody BookDTO dto) {
        try {
            //String temporaryUserId = "ParkJiho";
            //(1) BookEntity로 변환
            BookEntity entity = BookDTO.todoEntity(dto);
            //(4) 서비스를 이용해 엔티티 생성(서비스 계층에 요청)
            List<BookEntity> entities = service.searchTitle(entity);
            //(5) 자바 스트림을 이용해 리턴된 엔티티 리스트를 BookDTO 리스트로 변환
            List<BookDTO> dtos = entities.stream().map(BookDTO::new).collect(Collectors.toList());
            //(6) 변환된 BookDTO 리스트를 이용해 ResponseDTO를 초기화
            ResponseDTO<BookDTO> response = ResponseDTO.<BookDTO>builder().data(dtos).build();
            //(7) ResponseDTO 리턴
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            //(8) 예외가 있는 경우 dto 대신 error에 메시지를 넣어 리턴
            String error = e.getMessage();
            ResponseDTO<BookDTO> response = ResponseDTO.<BookDTO>builder().error(error).build();

            return ResponseEntity.badRequest().body(response);
        }
    }

    //제품 검색 (id) 전체 출력
    @GetMapping("/id")
    public ResponseEntity<?> retrieveBookIdList() {
        String temporaryUserId = "ParkJiho";
        //(1) 서비스 메서드의 retrieve() 메서드를 사용해 Book 리스트를 가져옴
        List<BookEntity> entities = service.retrieve(temporaryUserId);
        //(2) 자바 스트림을 이용해 리턴된 엔티티 리스트를 BookDTO 리스트로 변환
        List<BookDTO> dtos = entities.stream().map(BookDTO::new).collect(Collectors.toList());
        //(3) 변환된 TodoDTO 리스트를 이용해 ResponseDTO를 초기화
        ResponseDTO<BookDTO> response = ResponseDTO.<BookDTO>builder().data(dtos).build();
        //(4) ResponseDTO 리턴
        return ResponseEntity.ok().body(response);
    }

    //제품 수정 - 전체
    @PutMapping("/updateAll")
    public ResponseEntity<?> updateBookreturnAll(@RequestBody BookDTO dto) {
        String temporaryUserId = "ParkJiho";
        //(1) dto를 entity로 변환
        BookEntity entity = BookDTO.todoEntity(dto);
        //(2) id를 temporaryUserId로 초기화
        entity.setUserId(temporaryUserId);
        //(3) 서비스를 이용해 entity를 업데이트
        List<BookEntity> entities = service.updateareturnall(entity);
        //(4) 자바 스트림을 이용해 리턴된 엔티티 리스트를 BookDTO 리스트로 변환
        List<BookDTO> dtos = entities.stream().map(BookDTO::new).collect(Collectors.toList());
        //(5) 변환된 BookDTO 리스트를 이용해 ResponseDTO 초기화
        ResponseDTO<BookDTO> response = ResponseDTO.<BookDTO>builder().data(dtos).build();
        //(6) ResponseDTO 리턴
        return ResponseEntity.ok().body(response);

    }

    //제품 수정 - 해당 정보만(추가 작성)
    @PutMapping("/update")
    public ResponseEntity<?> updatesearch(@RequestBody BookDTO dto) {
        String temporaryUserId = "ParkJiho";
        BookEntity entity = BookDTO.todoEntity(dto);
        entity.setUserId(temporaryUserId);
        Optional<BookEntity> entities = service.update(entity);
        List<BookDTO> dtos = entities.stream().map(BookDTO::new).collect(Collectors.toList());
        ResponseDTO<BookDTO> response = ResponseDTO.<BookDTO>builder().data(dtos).build();
        return ResponseEntity.ok().body(response);
    }

    //제품 수정 - 해당 정보만
    @PutMapping
    public ResponseEntity<?> update(@RequestBody BookDTO dto) {
        String temporaryUserId = "ParkJiho";
        //(1) dto를 entity로 변환
        BookEntity entity = BookDTO.todoEntity(dto);
        //(2) id를 temporaryUserId로 초기화
        entity.setUserId(temporaryUserId);
        //(3) 서비스를 이용해 entity를 업데이트
        Optional<BookEntity> entities = service.update(entity);
        //(4) 자바 스트림을 이용해 리턴된 엔티티 리스트를 BookDTO 리스트로 변환
        List<BookDTO> dtos = entities.stream().map(BookDTO::new).collect(Collectors.toList());
        //(5) 변환된 BookDTO 리스트를 이용해 ResponseDTO 초기화
        ResponseDTO<BookDTO> response = ResponseDTO.<BookDTO>builder().data(dtos).build();
        //(6) ResponseDTO 리턴
        return ResponseEntity.ok().body(response);
    }

    //제품 삭제 - 삭제 후 전체
    @DeleteMapping
    public ResponseEntity<?> deleteBook(@RequestBody BookDTO dto) {
        try {
            String temporaryUserId = "ParkJiho";
            //(1) BookEntity로 변환
            BookEntity entity = BookDTO.todoEntity(dto);
            //(2) 임시 사용자 아이디를 설정
            entity.setUserId(temporaryUserId);
            //(3) 서비스를 이용해 entity 삭제
            List<BookEntity> entities = service.delete(entity);
            //(4) 자바 슽트림을 이용해 리턴된 엔티티 리스트를 BookDTO 리스트로 변환
            List<BookDTO> dtos = entities.stream().map(BookDTO::new).collect(Collectors.toList());
            //(5) 변환된 BookDTO 리스트를 이용해 ResponseDTO 초기화
            ResponseDTO<BookDTO> response = ResponseDTO.<BookDTO>builder().data(dtos).build();
            //(6) ResponseDTO 리턴
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            //(7) 예외가 있는 경우 dto 대신 error에 메시지를 넣어 리턴
            String error = e.getMessage();
            ResponseDTO<BookDTO> response = ResponseDTO.<BookDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }
    //텀프로젝트 3
    @GetMapping("/list")
    public ResponseEntity<ResponseDTO<BookDTO>> retrieveAllBookList() {
        List<BookEntity> entities = service.retrieveAll();
        List<BookDTO> dtos = entities.stream().map(BookDTO::new).collect(Collectors.toList());
        if (dtos.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            ResponseDTO<BookDTO> response = ResponseDTO.<BookDTO>builder().data(dtos).build();
            return ResponseEntity.ok().body(response);
        }
    }
}