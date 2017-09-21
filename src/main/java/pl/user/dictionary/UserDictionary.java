package pl.user.dictionary;

import pl.translator.Word;

import javax.persistence.*;
import java.util.List;

@Entity
public class UserDictionary {
    @Id
    @GeneratedValue
    private long id;

    @OneToMany//prepare entity?
    private List<Word> words;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void addWord(Word word){
        words.add(word);
    }

    public void removeWord(Word word){
        words.remove(word);
    }
    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }
}
