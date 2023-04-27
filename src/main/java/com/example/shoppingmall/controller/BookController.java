package com.example.shoppingmall.controller;



import com.example.shoppingmall.dto.BookDTO;
import com.example.shoppingmall.dto.ResponseDTO;
import com.example.shoppingmall.model.BookEntity;
import com.example.shoppingmall.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping("/test")
    public ResponseEntity<?> testTodo(){
        String str = service.testService();
        List<String>list = new ArrayList<>();
        list.add(str);
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody BookDTO dto){
        try{
            String temporaryUserId = "temporary-user";

            BookEntity entity = BookDTO.todoEntity(dto);
            entity.setId(null);
            entity.setUserId(temporaryUserId);
            List<BookEntity> entities = service.create(entity);
            List<BookDTO> dtos = entities.stream().map(BookDTO::new).collect(Collectors.toList());
            ResponseDTO<BookDTO> response = ResponseDTO.<BookDTO>builder().data(dtos).build();
            return ResponseEntity.ok().body(response);
        }catch (Exception e){
            String error = e.getMessage();
            ResponseDTO<BookDTO>response = ResponseDTO.<BookDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public ResponseEntity<?>retrieveTodoList(){
        String temporaryUserId = "temporary-user";
        List<BookEntity>entities=service.retrieve(temporaryUserId);
        List<BookDTO>dtos = entities.stream().map(BookDTO::new).collect(Collectors.toList());
        ResponseDTO<BookDTO>response = ResponseDTO.<BookDTO>builder().data(dtos).build();
        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    public ResponseEntity<?> updateTodo(@RequestBody BookDTO dto){
        String temporaryUserId = "temporary-user";
        BookEntity entity = BookDTO.todoEntity(dto);
        entity.setUserId(temporaryUserId);
        List<BookEntity> entities = service.update(entity);
        List<BookDTO> dtos = entities.stream().map(BookDTO::new).collect(Collectors.toList());
        ResponseDTO<BookDTO>response = ResponseDTO.<BookDTO>builder().data(dtos).build();
        return ResponseEntity.ok().body(response);

    }

    @DeleteMapping
    public ResponseEntity<?> deleteTodo(@RequestBody BookDTO dto){
        try{
            String temporaryUserId = "temporary-user";
            BookEntity entity = BookDTO.todoEntity(dto);
            entity.setUserId(temporaryUserId);
            List<BookEntity>entities = service.delete(entity);
            List<BookDTO>dtos = entities.stream().map(BookDTO::new).collect(Collectors.toList());
            ResponseDTO<BookDTO>response = ResponseDTO.<BookDTO>builder().data(dtos).build();
            return ResponseEntity.ok().body(response);
        }catch (Exception e){
            String error = e.getMessage();
            ResponseDTO<BookDTO> response = ResponseDTO.<BookDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }
}