package pl.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/quiz/")
public class QuizController {
    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    //
    @GetMapping("/all")
    public List<Quiz> getAllQuizes() {
        List<Quiz> allQuizes = quizService.getAllQuizes();
        return allQuizes;
    }

    @GetMapping("/{quizId}/exercise")
    public Exercise getExercise(@PathVariable long quizId) {
        Exercise exercise = quizService.getExercise(quizId);
        return exercise;
    }

    @GetMapping("/{dictionaryId}")
    public Quiz createNewQuiz(@PathVariable long dictionaryId) {
        Quiz newQuiz = quizService.createNewQuiz(dictionaryId);
        return newQuiz;
    }

    //ENUM
    @PostMapping("/{exerciseId}")
    public String checkAnswer(@PathVariable long exerciseId, @RequestParam String answer, @RequestParam String type){
        String response = quizService.checkAnswer(exerciseId, answer, type);
        return response;
    }


}
