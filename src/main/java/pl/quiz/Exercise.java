package pl.quiz;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Exercise {
    @Id
    @GeneratedValue
    private long id;
    private boolean isCorrect;
    private String englishWord;
    private String polishWord;
    private String answer;


    public Exercise(String englishWord, String polishWord) {
        this.isCorrect = false;
        this.englishWord = englishWord;
        this.polishWord = polishWord;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }

    public String getPolishWord() {
        return polishWord;
    }

    public void setPolishWord(String polishWord) {
        this.polishWord = polishWord;
    }
}
