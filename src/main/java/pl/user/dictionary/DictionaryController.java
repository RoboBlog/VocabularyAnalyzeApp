package pl.user.dictionary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.translator.Word;

import java.util.List;

@RestController
@RequestMapping("/user/dictionary/")
public class DictionaryController {

    private final  DictionaryService dictionaryService;

    @Autowired
    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }


    @GetMapping("/words/{language}/get/all")
    public List<Word> getAll(@PathVariable String language){
        List<Word> words =  dictionaryService.getAll();
        return words;
    }

    @GetMapping("/add/word/{id}")//get?
    public HttpStatus add(@PathVariable long id){
        dictionaryService.add(id);
        return HttpStatus.OK;
    }

    @DeleteMapping("/delete/word/{id}")
    public HttpStatus delete(@PathVariable long id){
        dictionaryService.delete(id);

        return HttpStatus.OK;
    }


    //pagination


}
