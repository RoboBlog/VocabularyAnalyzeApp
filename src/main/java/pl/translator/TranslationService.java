package pl.translator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;

@Repository
public class TranslationService {
    private final WordRepository wordRepository;

    @Autowired
    public TranslationService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

//TODO logger
    public String translateEnglishToPolish(String word){
        Word wordpolish = wordRepository.findByEnglishWord(word);
        if(wordpolish!= null) {
            return wordpolish.getPolishWord();
        }
        else {
            throw new NoSuchElementException("Non translation: " + word);
        }
    }
}
