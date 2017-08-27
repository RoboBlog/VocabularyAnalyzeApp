package pl.translator;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WordRepository extends CrudRepository<Word, Long> {
    Word findByEnglishWord(String englishWord);
    Word findById(long id);
}
