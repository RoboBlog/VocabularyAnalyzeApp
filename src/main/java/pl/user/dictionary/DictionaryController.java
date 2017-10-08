package pl.user.dictionary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api/user/dictionary/")
public class DictionaryController {

    private final DictionaryService dictionaryService;
    private final UserDictionariesService userDictionariesService;

    @Autowired
    public DictionaryController(DictionaryService dictionaryService, UserDictionariesService userDictionariesService) {
        this.dictionaryService = dictionaryService;
        this.userDictionariesService = userDictionariesService;
    }

    @GetMapping("/all")
    public List<UserDictionary> getAllDictionaries(){
        List<UserDictionary> allDictionaries = userDictionariesService.getAllDictionaries();
        return allDictionaries;
    }

    @PostMapping("/")
    public void addDictionary(@RequestParam String dictionaryName){
        userDictionariesService.addDictionary(dictionaryName);
    }

    @DeleteMapping("/")
    public void removeDictionary(@RequestParam long dictionaryId){
        userDictionariesService.removeDictionary(dictionaryId);
    }

    @GetMapping("/{dictionaryId}/all")
    public List<UserWord> getAll(@PathVariable long dictionaryId){
        System.out.println(dictionaryId);
        List<UserWord> allWords = dictionaryService.getAll(dictionaryId);
        return allWords;
    }

    @PostMapping("/{dictionaryId}/{wordId}")
    public void addWord(@PathVariable long dictionaryId, @PathVariable long wordId) {
        dictionaryService.add(dictionaryId, wordId);
//        return HttpStatus.OK;
    }

    @DeleteMapping("/{dictionaryId}/{wordId}")
    public void deleteWord(@PathVariable long dictionaryId, @PathVariable long wordId) {
        dictionaryService.delete(dictionaryId, wordId);
//        return HttpStatus.OK;
    }


    //pagination


}
