package pl.translator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TranslationService {
    private final WordRepository wordRepository;

    @Autowired
    public TranslationService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    //TODO LOGEGER EXCEPTION HANDLER
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
