package pl.translator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
    Word findByEnglishWord(String englishWord);
    List<Word> findByEnglishWordIn(List<String> words);
    Word findById(long id);
}
