package pl.flashcards;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.translator.Word;
import pl.user.UserService;
import pl.user.dictionary.UserDictionariesService;
import pl.user.dictionary.UserDictionary;
import pl.user.dictionary.UserWord;

import java.util.Set;

@Service
public class FlashcardsService {
    private final UserService userService;
    private final UserDictionariesService dictionariesService;
    @Autowired
    public FlashcardsService(UserService userService, UserDictionariesService dictionariesService) {
        this.userService = userService;
        this.dictionariesService = dictionariesService;
    }

    //TODO SECURITY. SET-RANDOM
    public Word getFlashcard(long dictionaryId){
//        User user = userService.getUser();
        UserDictionary dictionary = dictionariesService.getDictionary(dictionaryId);
        Set<UserWord> words = dictionary.getWords();
//        int rand = ThreadLocalRandom.current().nextInt(words.size());
//        System.out.println("RAND " + rand);
//        Collections.shuffle(words);
        return words.iterator().next().getWord();
//        return words.get(rand).getWord();
//        return words.iterator(
    }

}
