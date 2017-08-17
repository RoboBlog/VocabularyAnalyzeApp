package pl.user;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue
    @Column(name="userid")
    private Long id;

    @Column(name = "username", unique = true)
    private String username;

    private String password;

    private String email;

    private int enabled;

    private String activationCode;

    public User(String username, String password, String email, int enabled) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
    }

    public User(User user) {
        this.id = user.id;
        this.username = user.username;
        this.email = user.email;
        this.password = user.password;
        this.enabled=user.enabled;
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
