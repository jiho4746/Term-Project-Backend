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

    //Book 리스트 반환을 위해 findByUserId( ) 이용
    public List<BookEntity>retrieve(final String userId){
        return repository.findByUserId(userId);
    }

    // 텀프로젝트3 (추가)
    public List<BookEntity> retrieveAll() {
        List<BookEntity> entities = repository.findAll();

        if (entities.isEmpty()) {
            log.info("No books found.");
            throw new RuntimeException("No books found.");
        } else {
            log.info("Books found.");
            return entities;
        }
    }

    //제품 검색 기능(Title)
    public List<BookEntity> searchTitle(final BookEntity entity){
        log.info("Entity Title : {} is searched", entity.getTitle());
        return repository.findByTitle(entity.getTitle());
    }

    //제품 수정(해당 정보만)
    public Optional<BookEntity> update(final BookEntity entity){
        //(1) 저장할 엔티티가 유효한지 확인
        validate(entity);
        //(2) 넘겨받은 엔티티id를 이용해 BookEntity를 가져옴
        final Optional<BookEntity> original = repository.findById(entity.getId());
        original.ifPresent(todo -> {
            //(3) 반환된 BookEntity가 존재하면 값을 새 entity 값으로 덮어 씌움
            todo.setTitle(entity.getTitle());
            todo.setDone(entity.isDone());
            //(4) 데이터베이스에 새 값을 저장
            repository.save(todo);
        });
        return repository.findById(entity.getId());
    }

    //제품 수정(전체 반환)
    public List<BookEntity>updateareturnall(final BookEntity entity){
        //(1) 저장할 엔티티가 유효한지 확인
        validate(entity);
        //(2) 넘겨받은 엔티티id를 이용해 BookEntity를 가져옴
        final Optional<BookEntity> original = repository.findById(entity.getId());
        original.ifPresent(todo -> {
            //(3) 반환된 BookEntity가 존재하면 값을 새 entity 값으로 덮어 씌움
            todo.setTitle(entity.getTitle());
            todo.setDone(entity.isDone());
            //(4) 데이터베이스에 새 값을 저장
            repository.save(todo);
        });
        //사용자의 모든 Book리스트를 리턴
        return retrieve(entity.getUserId());
    }

    public List<BookEntity>delete(final BookEntity entity){
        //(1) 저장할 엔티티가 유효한지 확인
        validate(entity);

        try{
            //(2) 엔티티 삭제
            repository.delete(entity);
        }catch (Exception e){
            //(3) exception 발생 시 id와 exception로깅
            log.error("error deleting entity", entity.getId(), e);
            //(4) 컨트롤러로 exception 보냄
            throw new RuntimeException("erroer deleting entity " + entity.getId());
        }
        //(5) 새 Book 리스트를 가져와 리턴
        return retrieve(entity.getUserId());
    }
}