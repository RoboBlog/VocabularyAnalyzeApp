package pl.user;


import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.Email;
import pl.dictionary.UserDictionary;
import pl.other.Views;
import pl.quiz.Quiz;
import pl.security.Authority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.*;



@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="userid")
    @JsonView(Views.Internal.class)
    private Long userId;

    @NotNull
    @Column(name = "username", unique = true)
    @JsonView(Views.Public.class)
    private String username;

    @NotNull
    @JsonView(Views.Internal.class)
    private String password;

    @Email
    @NotNull
    @Column(name = "email", unique = true)
    @JsonView(Views.Public.class)
    private String email;

    @JsonView(Views.Internal.class)
    private boolean enabled;

    @JsonView(Views.Internal.class)
    private String activationCode;

    @OneToMany
    @JsonView(Views.Public.class)
    private List<UserDictionary> dictionaries;

    @JsonView(Views.Public.class)
    private long amountWords;

    @JsonView(Views.Public.class)
    private long amountKnownWords;

    @JsonView(Views.Internal.class)
    private long scoreV2;

    @JsonView(Views.Public.class)
    private long score;

    @JsonView(Views.Public.class)
    private long dayScore;

    @JsonView(Views.Public.class)
    private LocalDateTime createDate;

    @OneToMany
    @JsonView(Views.Public.class)
    private List<Quiz> quizes = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastPasswordResetDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "userid")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")})
    private List<Authority> authorities = new LinkedList<>();


//    public static long getSerialVersionUID() {
//        return serialVersionUID;
//    }

    public User(User user) {
        this.userId = user.userId;
        this.username = user.username;
        this.email = user.email;
        this.password = user.password;
        this.enabled = user.enabled;
        this.amountWords = 0;
        this.amountKnownWords = 0;
        this.activationCode = UUID.randomUUID().toString();
        this.score = 0;
        this.scoreV2 = 0;
        this.dayScore = 0;
        this.createDate = LocalDateTime.now();
    }


    public List<Authority> getAuthorities() {
        return authorities;
    }

    //    public UserDictionary getDictionary(dic){
//        dictionaries.@
//    }

    public void addAuthority(Authority authority){
        this.authorities.add(authority);
    }

    public void addQuiz(Quiz quiz){
        quizes.add(quiz);
    }
    public List<Quiz> getQuizes() {
        return quizes;
    }

    public void setQuizes(List<Quiz> quizes) {
        this.quizes = quizes;
    }

    public List<UserDictionary> getDictionaries() {
        return dictionaries;
    }

    public void addDictionaries(UserDictionary dictionary){
        this.dictionaries.add(dictionary);
    }
    public void setDictionaries(List<UserDictionary> dictionaries) {
        this.dictionaries = dictionaries;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public Long getUserId() {
        return userId;
    }

    public User() {
        this.amountWords = 0;
        this.amountKnownWords = 0;
        this.activationCode = UUID.randomUUID().toString();
        this.score = 0;
        this.scoreV2 = 0;
        this.dayScore = 0;
        this.createDate = LocalDateTime.now();
    }



    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.activationCode = UUID.randomUUID().toString();
        this.amountWords=0;
        this.amountKnownWords=0;
        this.score = 0;
        this.scoreV2 = 0;
        this.dayScore = 0;
        this.createDate = LocalDateTime.now();

    }

    public long getScoreV2() {
        return scoreV2;
    }

    public void setScoreV2(long scoreV2) {
        this.scoreV2 = scoreV2;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public long getDayScore() {
        return dayScore;
    }

    public void setDayScore(long dayScore) {
        this.dayScore = dayScore;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
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

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", enabled=" + enabled +
                ", activationCode='" + activationCode + '\'' +
                ", dictionaries=" + dictionaries +
                ", amountWords=" + amountWords +
                ", amountKnownWords=" + amountKnownWords +
                ", scoreV2=" + scoreV2 +
                ", score=" + score +
                ", dayScore=" + dayScore +
                ", createDate=" + createDate +
                ", quizes=" + quizes +
                ", lastPasswordResetDate=" + lastPasswordResetDate +
                ", authorities=" + authorities +
                '}';
    }
}
