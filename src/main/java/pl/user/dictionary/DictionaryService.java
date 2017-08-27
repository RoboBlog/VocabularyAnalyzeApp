package pl.user.dictionary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.translator.Word;
import pl.translator.WordRepository;
import pl.user.User;
import pl.user.UserService;

import javax.persistence.Column;
import java.util.List;

/**
 * Created by maciek on 8/25/17.
 */
@Service
public class DictionaryService {
    private final UserService userService;
    private final WordRepository wordRepository;

    @Autowired
    public DictionaryService(UserService userService, WordRepository wordRepository) {
        this.userService = userService;
        this.wordRepository = wordRepository;
    }

    @Column
    public List<Word> getAll(){
        User user = userService.getUser();
        List<Word> words = user.getWords();
        return words;
    }

    public void add(long wordId){
        User user = userService.getUser();
        Word word = wordRepository.findById(wordId);
        user.addWord(word);
    }//HttpResponse in controller? A serwis, w ktorym dodajemy cos do bazy [powinien byc void czy jakos inaczej?

    public void delete(){

    }


    //metody tutaj powine byc nazwane. NOWA SUPER METODA COS TAM czy po prostu po nazwie pakietu mozna domyslic sie  kontekstu
}
