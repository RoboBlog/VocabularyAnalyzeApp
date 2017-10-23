package pl.dictionary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.translator.WordRepository;
import pl.user.User;
import pl.user.UserRepository;
import pl.user.UserService;

import java.util.List;
import java.util.Set;

@Service
public class UserDictionariesService {
    private final UserService userService;
    private final WordRepository wordRepository;
    private final UserRepository userRepository;
    private final UserDictionaryRepository userDictionaryRepository;
    private final LearnService learnService;

    @Autowired
    public UserDictionariesService(UserService userService, WordRepository wordRepository, UserRepository userRepository, UserDictionaryRepository userDictionaryRepository, LearnService learnService) {
        this.userService = userService;
        this.wordRepository = wordRepository;
        this.userRepository = userRepository;
        this.userDictionaryRepository = userDictionaryRepository;
        this.learnService = learnService;
    }

    public List<UserDictionary> getAllDictionaries(){
        User user = userService.getUser();
        List<UserDictionary> dictionaries = user.getDictionaries();
        return dictionaries;
    }

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
        Set<UserWord> words = dictionary.getWords();
        int size = words.size();

        learnService.decrementationAmountWords(size);
        userDictionaryRepository.delete(dictionary);
    }

    public UserDictionary getDictionary(long dictionaryId){
        UserDictionary dictionary = userDictionaryRepository.getById(dictionaryId);
        return dictionary;
    }

    public void save(UserDictionary userDictionary){
        userDictionaryRepository.save(userDictionary);
    }
}
