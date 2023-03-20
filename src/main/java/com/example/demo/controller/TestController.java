package com.example.demo.controller;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TestRequestBodyDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("/testGetMapping")
    public String testController(){
        return "Hello World, 박지호";
    }

    @GetMapping("/{id}")
    public String testControllerWithPathVariables(@PathVariable(required = false) int id)
    {
        return "Hello World! 박지호 " + id;
    }

    @GetMapping("/testRequestParam")
    public String testControllerRequestParam(@RequestParam(required = false)int id){
        return "Hello World! 박지호" + id;
    }

    @GetMapping("/testRequestBody")
    public String testControllerRequestBody(@RequestBody TestRequestBodyDTO testRequestBodyDTO){
        return "Hello World! 박지호" + testRequestBodyDTO.getId() + "Message : " +
                testRequestBodyDTO.getMessage();
    }

    @GetMapping("/testRequestBody")
    public ResponseDTO<String> testControllerRequestBody(){
        List<String> list = new ArrayList<>();
        list.add("pjh1");
        list.add("pjh2");
        list.add("pjh3");

        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        return response;
    }

    @GetMapping("/testRequestEntity")
    public ResponseEntity<?> testControllerResponseEntity(){
        List<String> list = new ArrayList<>();
        list.add("pjh1");
        list.add("pjh2");
        list.add("pjh3");

        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        //return ResponseEntity.ok().body(response);
        return ResponseEntity.badRequest().body(response);
    }
}
