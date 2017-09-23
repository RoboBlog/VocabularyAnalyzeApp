package pl.user;


import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.Email;
import pl.other.Views;
import pl.translator.Word;
import pl.user.dictionary.UserDictionary;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue
    @Column(name="userid")
    @JsonView(Views.Internal.class)
    private Long id;

    @NotNull
    @Column(name = "username", unique = true)
    @JsonView(Views.Public.class)
    private String username;

    @NotNull
    @JsonView(Views.Internal.class)
    private String password;

    @Email
    @NotNull
    @JsonView(Views.Public.class)
    private String email;

    @JsonView(Views.Internal.class)
    private int enabled;

    @JsonView(Views.Internal.class)
    private String activationCode;

    @OneToMany
    @JsonView(Views.Public.class)
    private List<UserDictionary> dictionaries;

    @JsonView(Views.Public.class)
    private long amountWords;

    @JsonView(Views.Public.class)
    private long amountKnownWords;

    public List<UserDictionary> getDictionaries() {
        return dictionaries;
    }

    public void addDictionaries(UserDictionary dictionary){
        this.dictionaries.add(dictionary);
    }
    public void setDictionaries(List<UserDictionary> dictionaries) {
        this.dictionaries = dictionaries;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.activationCode = UUID.randomUUID().toString();
        this.amountWords=0;
        this.amountKnownWords=0;
    }

    public User(User user) {
        this.id = user.id;
        this.username = user.username;
        this.email = user.email;
        this.password = user.password;
        this.enabled=user.enabled;
        this.amountWords=0;
        this.amountKnownWords=0;
        this.activationCode = UUID.randomUUID().toString();
    }


    public long getAmountWords() {
        return amountWords;
    }

    public void setAmountWords(long amountWords) {
        this.amountWords = amountWords;
    }

    public long getAmountKnownWords() {
        return amountKnownWords;
    }

    public void setAmountKnownWords(long amountKnownWords) {
        this.amountKnownWords = amountKnownWords;
    }

    public User(){

    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }
}
