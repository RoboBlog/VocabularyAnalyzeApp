package pl.user.dictionary;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class UserDictionary {
    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private String name;
    @OneToMany
    private List<UserWord> words;

    public UserDictionary() {
    }

    public UserDictionary(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
