package hr.fer.backend.repository;

import hr.fer.backend.model.Pozari;
import hr.fer.backend.model.PrimaryKeyId;
import org.springframework.data.repository.CrudRepository;

public interface PozariRepository extends CrudRepository<Pozari, PrimaryKeyId> {
}
