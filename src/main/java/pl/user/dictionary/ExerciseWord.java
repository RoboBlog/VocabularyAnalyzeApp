package pl.user.dictionary;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ExerciseWord {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    private UserWord userWord;
}
