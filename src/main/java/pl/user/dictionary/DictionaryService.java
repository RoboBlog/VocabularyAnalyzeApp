package pl.user.dictionary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.translator.Word;
import pl.translator.WordRepository;
import pl.user.User;
import pl.user.UserRepository;
import pl.user.UserService;

import java.util.Set;

@Service
public class DictionaryService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final UserWordRepository userWordRepository;
    private final UserDictionariesService userDictionariesService;
    private final WordRepository wordRepository;
    private final LearnService learnService;

    @Autowired
    public DictionaryService(UserRepository userRepository, UserService userService, UserWordRepository userWordRepository, UserDictionariesService userDictionariesService, WordRepository wordRepository, LearnService learnService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.userWordRepository = userWordRepository;
        this.userDictionariesService = userDictionariesService;
        this.wordRepository = wordRepository;
        this.learnService = learnService;
    }

    public Set<UserWord> getAll(long dictionaryId) {
        UserDictionary dictionary = userDictionariesService.getDictionary(dictionaryId);
        Set<UserWord> words = dictionary.getWords();
        return words;
    }


    public void add(long dictionaryId, long wordId){
        User user = userService.getUser();
        //SECURITY
        UserDictionary dictionary = userDictionariesService.getDictionary(dictionaryId);
        //add word
        Word word = wordRepository.findById(wordId);
        UserWord userWord = new UserWord(word);
        userWordRepository.save(userWord);
        dictionary.addWord(userWord);
        userDictionariesService.save(dictionary);
        learnService.incrementationAmountWords();
    }

    public void delete(long dictionaryId, long wordId){
        UserDictionary dictionary = userDictionariesService.getDictionary(dictionaryId);
        UserWord word = userWordRepository.findById(wordId);
        dictionary.removeWord(word);
        userDictionariesService.save(dictionary);
        learnService.decrementationAmountWords();
    }

}
