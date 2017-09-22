package pl.flashcards;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.translator.Word;

@RestController
@RequestMapping("/api/user/flashcard/")
public class FlashcardsController {
    private final FlashcardsService flashcardsService;

    @Autowired
    public FlashcardsController(FlashcardsService flashcardsService) {
        this.flashcardsService = flashcardsService;
    }

    @GetMapping("/{dictionaryId}")
    public Word getFlashcard(@PathVariable long dictionaryId){
        Word flashcard = flashcardsService.getFlashcard(dictionaryId);
        return flashcard;
    }
}
