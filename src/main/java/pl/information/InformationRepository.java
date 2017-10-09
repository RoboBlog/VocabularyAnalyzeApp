package pl.information;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InformationRepository extends CrudRepository<Information, Long> {
    List<Information> getAllByActiveTrue();

    List<Information> getAll();

    Information getById(long informationId);
}
