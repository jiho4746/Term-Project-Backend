package com.example.demo.service;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TodoDTO;
import com.example.demo.model.TodoEntity;
import com.example.demo.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
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

        validate(entity);
        repository.save(entity);
        log.info("Entity Id : {} is saved", entity.getId());
        return repository.findByUserId(entity.getUserId());
    }
    //리팩토링한 메서드
    private static void validate(TodoEntity entity) {
        if (entity ==null){
            log.warn("Entity cannot be null.");
            throw new RuntimeException("Entity cannot be null");
        }
        if (entity.getUserId() ==null){
            log.warn("Unkown user.");
            throw new RuntimeException("Unkown user");
        }
    }
    public List<TodoEntity>retrieve(final String userId){
        return repository.findByUserId(userId);
    }

    public List<TodoEntity>update(final TodoEntity entity){
        validate(entity);
        final Optional<TodoEntity> original = repository.findById(entity.getId());
        original.ifPresent(todo -> {
            todo.setTitle(entity.getTitle());
            todo.setDone(entity.isDone());
            repository.save(todo);
        });
        return retrieve(entity.getUserId());
    }
    public List<TodoEntity>delete(final TodoEntity entity){
        validate(entity);

        try{
            repository.delete(entity);
        }catch (Exception e){
            log.error("error deleting entity", entity.getId(), e);
            throw new RuntimeException("erroer deleting entity " + entity.getId());
        }
        return retrieve(entity.getUserId());
    }
}
