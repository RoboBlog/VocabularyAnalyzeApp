package pl.flashcards;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.translator.Word;

@CrossOrigin(origins = "http://localhost:8081")
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
