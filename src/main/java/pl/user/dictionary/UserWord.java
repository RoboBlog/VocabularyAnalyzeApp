package pl.user.dictionary;

import pl.translator.Word;

import javax.persistence.*;

@Entity
public class UserWord {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    private Word word;
    private int correctness;
    private boolean isKnow;


    public UserWord() {

    }

    public UserWord(Word word) {
        this.word = word;
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

    @Override
    public String toString() {
        return "UserWord{" +
                "id=" + id +
                ", word=" + word +
                ", correctness=" + correctness +
                ", isKnow=" + isKnow +
                '}';
    }
}
