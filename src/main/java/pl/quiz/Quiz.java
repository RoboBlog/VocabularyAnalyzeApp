package pl.quiz;

import pl.dictionary.UserDictionary;
import pl.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Quiz {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    private UserDictionary userDictionary;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Exercise> exercises = new ArrayList<>();
    private boolean done;

    //TODO cascade
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    public Quiz(List<Exercise> exercises, User user) {
        this.exercises = exercises;
        this.done = false;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Quiz() {
        this.done = false;
    }

    public void addExercise(Exercise exercise){
        exercises.add(exercise);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserDictionary getUserDictionary() {
        return userDictionary;
    }

    public void setUserDictionary(UserDictionary userDictionary) {
        this.userDictionary = userDictionary;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", userDictionary=" + userDictionary +
                ", exercises=" + exercises +
                ", done=" + done +
                '}';
    }
}
