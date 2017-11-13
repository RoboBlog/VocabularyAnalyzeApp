package pl.other;

public class QuizIsDoneException extends RuntimeException {

    public QuizIsDoneException(String message){
        super(message);
    }

}