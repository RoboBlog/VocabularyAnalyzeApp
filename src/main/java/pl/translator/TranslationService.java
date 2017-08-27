package pl.translator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by maciek on 8/20/17.
 */
@Repository
public class TranslationService {
    private final WordRepository wordRepository;

    @Autowired
    public TranslationService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public String translateEnglishToPolish(String word){
        Word wordpolish = wordRepository.findByEnglishWord(word);
        if(wordpolish!= null)
        return wordpolish.getPolishWord();
        else
        return "non translation"+word;
    }
}
