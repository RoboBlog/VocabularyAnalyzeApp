package pl.user.dictionary;

import pl.translator.Word;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class UserWord {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    private Word word;
    private int correctness;
    private boolean isKnow;

    public UserWord() {
    }

    public UserWord(Word word) {
        this.word = word;
        this.correctness = 0;
        this.isKnow = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public int getCorrectness() {
        return correctness;
    }

    public void setCorrectness(int correctness) {
        this.correctness = correctness;
    }

    public boolean isKnow() {
        return isKnow;
    }

    public void setKnow(boolean know) {
        isKnow = know;
    }
}
