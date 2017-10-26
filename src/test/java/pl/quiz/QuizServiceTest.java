package pl.quiz;

import org.junit.Test;
import org.springframework.security.access.AccessDeniedException;
import pl.dictionary.UserDictionariesService;
import pl.dictionary.UserWord;
import pl.translator.Word;
import pl.user.ScoreService;
import pl.user.User;
import pl.user.UserService;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//TODO INTEgration test scoreSercixe
public class QuizServiceTest {

    @Test
    public void getAllQuizes_OneQuiz(){
        UserDictionariesService userDictionariesService = mock(UserDictionariesService.class);
        UserService userService = mock(UserService.class);
        QuizRepository quizRepository = mock(QuizRepository.class);
        ExerciseRepository exerciseRepository = mock(ExerciseRepository.class);
        ScoreService scoreService = mock(ScoreService.class);

        List<Exercise> exercises = new LinkedList<>();
        exercises.add(new Exercise(new UserWord(new Word("test", "test")),"test"));

        User user = new User("test", "test", "test@gmail.com");

        Quiz quiz = new Quiz(exercises, user);

        user.addQuiz(quiz);
        when(userService.getUser()).thenReturn(user);

        QuizService quizService = new QuizService(userDictionariesService, userService, quizRepository, exerciseRepository, scoreService);

        List<Quiz> allQuizes = quizService.getAllQuizes();

        assertThat(allQuizes.get(0)).isEqualTo(quiz);
    }

    @Test
    public void getExercise_CorrectExercise_Exercise(){
        UserDictionariesService userDictionariesService = mock(UserDictionariesService.class);
        UserService userService = mock(UserService.class);
        QuizRepository quizRepository = mock(QuizRepository.class);
        ExerciseRepository exerciseRepository = mock(ExerciseRepository.class);
        ScoreService scoreService = mock(ScoreService.class);

        List<Exercise> exercises = new LinkedList<>();
        exercises.add(new Exercise(new UserWord(new Word("test", "test")),"test"));

        User user = new User("test", "test", "test@gmail.com");

        Quiz quiz = new Quiz(exercises, user);

        user.addQuiz(quiz);
        when(userService.getUser()).thenReturn(user);
        when(quizRepository.findById(0L)).thenReturn(Optional.ofNullable(quiz));

        QuizService quizService = new QuizService(userDictionariesService, userService, quizRepository, exerciseRepository, scoreService);

        Exercise exercise = quizService.getExercise(0);

        assertThat(exercise).isEqualTo(exercises.get(0));
    }


    @Test(expected = AccessDeniedException.class)
    public void getExercise_NotAuthUser_AccessDeniedException(){
        UserDictionariesService userDictionariesService = mock(UserDictionariesService.class);
        UserService userService = mock(UserService.class);
        QuizRepository quizRepository = mock(QuizRepository.class);
        ExerciseRepository exerciseRepository = mock(ExerciseRepository.class);
        ScoreService scoreService = mock(ScoreService.class);

        List<Exercise> exercises = new LinkedList<>();
        exercises.add(new Exercise(new UserWord(new Word("test", "test")),"test"));

        User user = new User("test", "test", "test@gmail.com");
        User user2 = new User("tes1t", "test", "test@gmail.com");

        Quiz quiz = new Quiz(exercises, user);

        user.addQuiz(quiz);
        when(userService.getUser()).thenReturn(user2);
        when(quizRepository.findById(0L)).thenReturn(Optional.ofNullable(quiz));

        QuizService quizService = new QuizService(userDictionariesService, userService, quizRepository, exerciseRepository, scoreService);

        Exercise exercise = quizService.getExercise(0);
    }



    @Test
    public void checkAnswer_TypePeCorrectAnswer_isCorrect(){
        UserDictionariesService userDictionariesService = mock(UserDictionariesService.class);
        UserService userService = mock(UserService.class);
        QuizRepository quizRepository = mock(QuizRepository.class);
        ExerciseRepository exerciseRepository = mock(ExerciseRepository.class);
        ScoreService scoreService = mock(ScoreService.class);

        Exercise exercise = new Exercise(new UserWord(new Word("test", "test")));

        when(exerciseRepository.findExerciseById(0L)).thenReturn(exercise);
        QuizService quizService = new QuizService(userDictionariesService, userService, quizRepository, exerciseRepository, scoreService);

        String response = quizService.checkAnswer(0, "test", "pe");

        assertThat(response).isEqualTo("is Correct");
    }

    @Test
    public void checkAnswer_TypePeIncorrectAnswer_isIncorrect(){
        UserDictionariesService userDictionariesService = mock(UserDictionariesService.class);
        UserService userService = mock(UserService.class);
        QuizRepository quizRepository = mock(QuizRepository.class);
        ExerciseRepository exerciseRepository = mock(ExerciseRepository.class);
        ScoreService scoreService = mock(ScoreService.class);

        Exercise exercise = new Exercise(new UserWord(new Word("test", "test")));

        when(exerciseRepository.findExerciseById(0L)).thenReturn(exercise);
        QuizService quizService = new QuizService(userDictionariesService, userService, quizRepository, exerciseRepository, scoreService);

        String response = quizService.checkAnswer(0, "tes2t", "pe");

        assertThat(response).isEqualTo("is Incorrect");
    }

    @Test
    public void checkAnswer_TypeEpCorrectAnswer_isCorrect(){
        UserDictionariesService userDictionariesService = mock(UserDictionariesService.class);
        UserService userService = mock(UserService.class);
        QuizRepository quizRepository = mock(QuizRepository.class);
        ExerciseRepository exerciseRepository = mock(ExerciseRepository.class);
        ScoreService scoreService = mock(ScoreService.class);

        Exercise exercise = new Exercise(new UserWord(new Word("test", "test")));

        when(exerciseRepository.findExerciseById(0L)).thenReturn(exercise);
        QuizService quizService = new QuizService(userDictionariesService, userService, quizRepository, exerciseRepository, scoreService);

        String response = quizService.checkAnswer(0, "test", "ep");

        assertThat(response).isEqualTo("is Correct");
    }

    @Test
    public void checkAnswer_TypeEpIncorrectAnswer_isIncorrect(){
        UserDictionariesService userDictionariesService = mock(UserDictionariesService.class);
        UserService userService = mock(UserService.class);
        QuizRepository quizRepository = mock(QuizRepository.class);
        ExerciseRepository exerciseRepository = mock(ExerciseRepository.class);
        ScoreService scoreService = mock(ScoreService.class);

        Exercise exercise = new Exercise(new UserWord(new Word("test", "test")));

        when(exerciseRepository.findExerciseById(0L)).thenReturn(exercise);
        QuizService quizService = new QuizService(userDictionariesService, userService, quizRepository, exerciseRepository, scoreService);

        String response = quizService.checkAnswer(0, "tes3t", "ep");

        assertThat(response).isEqualTo("is Incorrect");
    }


    //TODO DAO TEST
    //    @Test
//    public void createNewQuiz(){
//        UserDictionariesService userDictionariesService = mock(UserDictionariesService.class);
//        UserService userService = mock(UserService.class);
//        QuizRepository quizRepository = mock(QuizRepository.class);
//        ExerciseRepository exerciseRepository = mock(ExerciseRepository.class);
//        ScoreService scoreService = mock(ScoreService.class);
//    }
}
