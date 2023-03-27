package com.example.demo.service;

import com.example.demo.dto.TodoDTO;
import com.example.demo.model.TodoEntity;
import com.example.demo.persistence.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class TodoService {
    @Autowired
    private TodoRepository repository;
    public String testService(){
        //return "Test Service";
        TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
        repository.save(entity);
        TodoEntity savedEntity = repository.findById(entity.getId()).get();
        return savedEntity.getTitle();
    }
    public List<TodoEntity> create(final TodoEntity entity){
        if (entity ==null){
            log.warn("Entity cannot be null.");
            throw new RuntimeException("Entity cannot be null");
        }
        if (entity.getUserId() ==null){
            log.warn("Unkown user.");
            throw new RuntimeException("Unkown user");
        }
        repository.save(entity);
        log.info("Entity Id : {} is saved", entity.getId());
        return repository.findByUserId(entity.getUserId());
    }

}
