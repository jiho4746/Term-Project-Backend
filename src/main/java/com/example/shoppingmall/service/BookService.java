package com.example.shoppingmall.service;

import com.example.shoppingmall.model.BookEntity;
import com.example.shoppingmall.persistence.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class BookService {
    //TodoRepository 인터페이스를 구현한 클래스를 스프링 데이터 JPA가 자동으로 만들고,객체가 자동으로 주입됨
    @Autowired
    private BookRepository repository;
    public String testService(){
        BookEntity entity = BookEntity.builder().title("My first Book item").build(); //BookEntity 생성
        repository.save(entity); //BookEntity 저장
        BookEntity savedEntity = repository.findById(entity.getId()).get(); //리포지터리에서 검색
        return savedEntity.getTitle();
    }
    public List<BookEntity> create(final BookEntity entity){

        validate(entity);
        repository.save(entity);
        log.info("Entity Id : {} is saved", entity.getId());
        return repository.findByUserId(entity.getUserId());
    }
    //리팩토링한 메서드
    private static void validate(BookEntity entity) {
        if (entity ==null){
            log.warn("Entity cannot be null.");
            throw new RuntimeException("Entity cannot be null");
        }
        if (entity.getUserId() ==null){
            log.warn("Unkown user.");
            throw new RuntimeException("Unkown user");
        }
    }
    public List<BookEntity>retrieve(final String userId){
        return repository.findByUserId(userId);
    }

    public List<BookEntity>update(final BookEntity entity){
        validate(entity);
        final Optional<BookEntity> original = repository.findById(entity.getId());
        original.ifPresent(todo -> {
            todo.setTitle(entity.getTitle());
            todo.setDone(entity.isDone());
            repository.save(todo);
        });
        return retrieve(entity.getUserId());
    }
    public List<BookEntity>delete(final BookEntity entity){
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