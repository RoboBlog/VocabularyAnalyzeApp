package pl.quiz;

import pl.dictionary.UserWord;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Exercise {
    @Id
    @GeneratedValue
    private long id;
    private boolean isCorrect;
    @ManyToOne
    private UserWord userWord;
    private String answer;

    public Exercise() {
    }

    public Exercise(UserWord userWord, String answer) {
        this.userWord = userWord;
        this.answer = answer;
        this.isCorrect = false;
    }

    public Exercise(UserWord userWord) {
        this.userWord = userWord;
        this.isCorrect = false;
    }

    public long getId() {
        return id;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public UserWord getUserWord() {
        return userWord;
    }

    public void setUserWord(UserWord userWord) {
        this.userWord = userWord;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", isCorrect=" + isCorrect +
                ", userWord=" + userWord +
                ", answer='" + answer + '\'' +
                '}';
    }
}
