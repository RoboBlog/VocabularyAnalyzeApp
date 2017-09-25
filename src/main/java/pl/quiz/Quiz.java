package pl.quiz;

import pl.user.dictionary.UserDictionary;

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
    @OneToMany
    private List<Exercise> exercises = new ArrayList<>();
    private boolean done;

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
}
