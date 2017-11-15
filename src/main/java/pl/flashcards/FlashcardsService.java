package pl.flashcards;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import pl.dictionary.UserDictionariesService;
import pl.dictionary.UserDictionary;
import pl.dictionary.UserWord;
import pl.quiz.Quiz;
import pl.security.JwtAuthenticationTokenFilter;
import pl.translator.Word;
import pl.user.User;
import pl.user.UserService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class FlashcardsService {
    private final UserService userService;
    private final UserDictionariesService dictionariesService;
    private final Logger logger = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

    @Autowired
    public FlashcardsService(UserService userService, UserDictionariesService dictionariesService) {
        this.userService = userService;
        this.dictionariesService = dictionariesService;
    }

    //TODO LOGGER
    public Word getFlashcard(long dictionaryId){
        User user = userService.getUser();
        UserDictionary dictionary = dictionariesService.getDictionary(dictionaryId);

        if(user==dictionary.getUser()) {
            Set<UserWord> words = dictionary.getWords();
            UserWord randomUserWord = getRandomUserWord(words);
            return randomUserWord.getWord();
        }
        else{
            throw new AccessDeniedException("Access Denied!");
        }
    }

    public UserWord getRandomUserWord(Set<UserWord> words){
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
