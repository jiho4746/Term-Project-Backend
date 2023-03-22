package com.example.demo.controller;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TodoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {
    @GetMapping("/testTodo")
    public ResponseEntity<?>testTodo(){
        TodoDTO tododto = new TodoDTO();
        tododto.setId("123");
        tododto.setTitle("박지호");
        tododto.setDone(true);
        List<String> list = new ArrayList<>();

        //ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        //return ResponseEntity.ok().body(response);
        return new ResponseEntity<>(tododto, HttpStatus.OK);
    }
}
