package pl.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

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
//

    @GetMapping("/{dictionaryId}/")
    public int quiz() {
//        Quiz quiz = new Quiz();
        Random r = new Random();
        int max = 10;
        int min = 2;
//        int wordNumber = r.nextInt((max - min)+1) + min;
        int wordNumber = r.nextInt(max + 1);
        return wordNumber;
//        Exercise exercise = new Exercise()
//        return "quiz";
    }
}
