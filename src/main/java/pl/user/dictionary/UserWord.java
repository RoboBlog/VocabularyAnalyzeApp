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
    private int learningLevel;
    private boolean isKnow;

    public UserWord() {
    }

    public UserWord(Word word) {
        this.word = word;
        this.learningLevel = 0;
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

    public int getLearningLevel() {
        return learningLevel;
    }

    public void setLearningLevel(int learningLevel) {
        this.learningLevel = learningLevel;
    }

    public boolean isKnow() {
        return isKnow;
    }

    public void setKnow(boolean know) {
        isKnow = know;
    }
}
