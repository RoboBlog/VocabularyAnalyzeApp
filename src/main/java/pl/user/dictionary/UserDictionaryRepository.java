package pl.user.dictionary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDictionaryRepository extends JpaRepository<UserDictionary, Long>{
    UserDictionary getById(long id);
}
