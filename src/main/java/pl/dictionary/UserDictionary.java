package pl.dictionary;

import pl.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class UserDictionary {
    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private String name;
    @OneToMany
    private Set<UserWord> words = new HashSet<>();

    //It is good ?
    @ManyToOne
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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

    public Set<UserWord> getWords() {
        return words;
    }

    public void setWords(Set<UserWord> words) {
        this.words = words;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDictionary that = (UserDictionary) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return words != null ? words.equals(that.words) : that.words == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (words != null ? words.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserDictionary{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", words=" + words +
                '}';
    }
}
