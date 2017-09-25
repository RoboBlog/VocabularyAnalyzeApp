package pl.quiz;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by maciek on 8/25/17.
 */
@Repository
public interface ExerciseRepository extends CrudRepository<Exercise, Long> {
}
