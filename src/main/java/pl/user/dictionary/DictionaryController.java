package pl.user.dictionary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/dictionary/")
public class DictionaryController {

    private final DictionaryService dictionaryService;
    private final UserDictionariesService userDictionariesService;

    @Autowired
    public DictionaryController(DictionaryService dictionaryService, UserDictionariesService userDictionariesService) {
        this.dictionaryService = dictionaryService;
        this.userDictionariesService = userDictionariesService;
    }

    @GetMapping("/get/all")
    public List<UserDictionary> getAllDictionaries(){
        List<UserDictionary> allDictionaries = userDictionariesService.getAllDictionaries();
        return allDictionaries;
    }

    @GetMapping("/get/{dictionaryId}/all")
    public List<UserWord> getAll(@PathVariable long dictionaryId){
        List<UserWord> allWords = dictionaryService.getAll(dictionaryId);
        return allWords;
    }

    @PostMapping("/add/{dictionaryId}/{wordId}")
    public void add(@PathVariable long dictionaryId, @PathVariable long wordId){
        dictionaryService.add(dictionaryId, wordId);
//        return HttpStatus.OK;
    }

    @DeleteMapping("/delete/{dictionaryId}/{wordId}")
    public void delete(@PathVariable long dictionaryId, @PathVariable long wordId){
        dictionaryService.delete(dictionaryId, wordId);
//        return HttpStatus.OK;
    }


    //pagination


}
