package pl.dictionary;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserWord userWord = (UserWord) o;

        if (id != userWord.id) return false;
        if (correctness != userWord.correctness) return false;
        if (isKnow != userWord.isKnow) return false;
        return word != null ? word.equals(userWord.word) : userWord.word == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (word != null ? word.hashCode() : 0);
        result = 31 * result + correctness;
        result = 31 * result + (isKnow ? 1 : 0);
        return result;
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
