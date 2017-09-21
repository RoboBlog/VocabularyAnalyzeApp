package pl.user.dictionary;
import javax.persistence.*;
import java.util.List;

@Entity
public class UserDictionary {
    @Id
    @GeneratedValue
    private long id;

    @OneToMany
    private List<UserWord> words;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void addWord(UserWord word){
        words.add(word);
    }

    public void removeWord(UserWord word){
        words.remove(word);
    }

    public List<UserWord> getWords() {
        return words;
    }

    public void setWords(List<UserWord> words) {
        this.words = words;
    }
}
