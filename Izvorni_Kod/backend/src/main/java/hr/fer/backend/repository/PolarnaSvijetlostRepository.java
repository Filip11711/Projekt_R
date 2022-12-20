package hr.fer.backend.repository;

import hr.fer.backend.model.PolarnaSvijetlost;
import hr.fer.backend.model.PrimaryKeyId;
import org.springframework.data.repository.CrudRepository;

public interface PolarnaSvijetlostRepository extends CrudRepository<PolarnaSvijetlost, PrimaryKeyId> {
}
