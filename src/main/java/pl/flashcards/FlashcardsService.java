package pl.flashcards;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.translator.Word;
import pl.user.UserService;
import pl.user.dictionary.UserDictionariesService;
import pl.user.dictionary.UserDictionary;
import pl.user.dictionary.UserWord;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class FlashcardsService {
    private final UserService userService;
    private final UserDictionariesService dictionariesService;
    
    @Autowired
    public FlashcardsService(UserService userService, UserDictionariesService dictionariesService) {
        this.userService = userService;
        this.dictionariesService = dictionariesService;
    }

    //TODO SECURITY.
    public Word getFlashcard(long dictionaryId){
        UserDictionary dictionary = dictionariesService.getDictionary(dictionaryId);
        Set<UserWord> words = dictionary.getWords();
        UserWord randomUserWord = getRandomUserWord(words);

        return randomUserWord.getWord();
    }

    private UserWord getRandomUserWord(Set<UserWord> words){
        int rand = ThreadLocalRandom.current().nextInt(words.size());
        int i = 0;
        UserWord randomWord = null;
        for(UserWord rw : words) {
            if (i == rand) {
                randomWord = rw;
            }
            i++;
        }
        return randomWord;
    }
}
