package pl.user.dictionary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.translator.Word;
import pl.translator.WordRepository;
import pl.user.User;
import pl.user.UserRepository;
import pl.user.UserService;

import java.util.List;

/**
 * Created by maciek on 8/25/17.
 */
@Service
public class DictionaryService {
    private final UserService userService;
    private final WordRepository wordRepository;
    private final UserRepository userRepository;

    @Autowired
    public DictionaryService(UserService userService, WordRepository wordRepository, UserRepository userRepository) {
        this.userService = userService;
        this.wordRepository = wordRepository;
        this.userRepository = userRepository;
    }

    public List<Word> getAll(){
        User user = userService.getUser();
        List<Word> words = user.getWords();
        return words;
    }

    public void add(long wordId){
        User user = userService.getUser();
        Word word = wordRepository.findById(wordId);
        user.addWord(word);
        userRepository.save(user);
    }

    public void delete(long wordId){
        User user = userService.getUser();
        Word word = wordRepository.findById(wordId);
        user.deleteWord(word);
        userRepository.save(user);
    }



}
