package pl.user.dictionary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.translator.Word;
import pl.translator.WordRepository;
import pl.user.User;
import pl.user.UserRepository;
import pl.user.UserService;

import java.util.List;

@Service
public class DictionaryService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final WordRepository wordRepository;
    private final UserDictionariesService userDictionariesService;

    @Autowired
    public DictionaryService(UserRepository userRepository, UserService userService, WordRepository wordRepository, UserDictionariesService userDictionariesService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.wordRepository = wordRepository;
        this.userDictionariesService = userDictionariesService;
    }

    public List<Word> getAll(long dictionaryId){
        UserDictionary dictionary = userDictionariesService.getDictionary(dictionaryId);
        List<Word> words = dictionary.getWords();
        return words;
    }

    public void add(long dictionaryId, long wordId){
//        User user = userService.getUser();
        //SECURRITY
        UserDictionary dictionary = userDictionariesService.getDictionary(dictionaryId);
        Word word = wordRepository.findById(wordId);
        dictionary.addWord(word);
        userDictionariesService.save(dictionary);
    }

    public void delete(long dictionaryId, long wordId){
        UserDictionary dictionary = userDictionariesService.getDictionary(dictionaryId);
        Word word = wordRepository.findById(wordId);
        dictionary.removeWord(word);
        userDictionariesService.save(dictionary);
    }

}
