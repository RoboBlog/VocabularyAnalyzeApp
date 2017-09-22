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
public class UserDictionariesService {
    private final UserService userService;
    private final WordRepository wordRepository;
    private final UserRepository userRepository;
    private final UserDictionaryRepository userDictionaryRepository;

    @Autowired
    public UserDictionariesService(UserService userService, WordRepository wordRepository, UserRepository userRepository, UserDictionaryRepository userDictionaryRepository) {
        this.userService = userService;
        this.wordRepository = wordRepository;
        this.userRepository = userRepository;
        this.userDictionaryRepository = userDictionaryRepository;
    }

    public List<UserDictionary> getAllDictionaries(){
        User user = userService.getUser();
        List<UserDictionary> dictionaries = user.getDictionaries();
        return dictionaries;
    }

    //TODO REPAIR IT !
    public void addDictionary(String name){
        User user = userService.getUser();
        UserDictionary dictionary = new UserDictionary(name);
        user.addDictionaries(dictionary);
        userDictionaryRepository.save(dictionary);
        userRepository.save(user);

    }

    public void removeDictionary(long dictionaryId){
        //TODO SECURITY
        UserDictionary dictionary = userDictionaryRepository.getById(dictionaryId);
        userDictionaryRepository.delete(dictionary);
    }

    public UserDictionary getDictionary(long dictionaryId){
        //TODO SECURITY
        UserDictionary dictionary = userDictionaryRepository.getById(dictionaryId);
        return dictionary;
    }
    public void save(UserDictionary userDictionary){
        userDictionaryRepository.save(userDictionary);
    }
}