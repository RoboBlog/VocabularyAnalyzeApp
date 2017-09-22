package pl.flashcards;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.translator.Word;
import pl.user.User;
import pl.user.UserService;
import pl.user.dictionary.UserDictionariesService;
import pl.user.dictionary.UserDictionary;
import pl.user.dictionary.UserWord;

import java.util.List;
import java.util.Random;

@Service
public class FlashcardsService {
    private final UserService userService;
    private final UserDictionariesService dictionariesService;
    @Autowired
    public FlashcardsService(UserService userService, UserDictionariesService dictionariesService) {
        this.userService = userService;
        this.dictionariesService = dictionariesService;
    }

    //TODO SECURITY
    public Word getFlashcard(long dictionaryId){
        User user = userService.getUser();
        UserDictionary dictionary = dictionariesService.getDictionary(dictionaryId);
        List<UserWord> words = dictionary.getWords();
        int rand = randomLong(words.size());
        return words.get(rand).getWord();
    }

    int randomLong(long max){
        Random random = new Random(max+1);
        int randomLong = random.nextInt();
        return randomLong;
    }
}
