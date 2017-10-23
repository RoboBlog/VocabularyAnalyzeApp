package pl.dictionary;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserWordRepository extends CrudRepository<UserWord, Long> {
    UserWord findById(long id);
    List<UserWord> findAll();
}
