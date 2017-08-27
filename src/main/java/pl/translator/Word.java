package pl.translator;


import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Word {
    @Id
    @GeneratedValue
    private Long id;
    private String englishWord;
    private String polishWord;

    public Word(String englishWord, String polishWord) {
        this.englishWord = englishWord;
        this.polishWord = polishWord;
    }

    public Word() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", englishWord='" + englishWord + '\'' +
                ", polishWord='" + polishWord + '\'' +
                '}';
    }
}
