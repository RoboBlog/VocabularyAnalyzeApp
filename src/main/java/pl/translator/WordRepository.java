package pl.translator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
    Word findByEnglishWord(String englishWord);
    Word findById(long id);
}
