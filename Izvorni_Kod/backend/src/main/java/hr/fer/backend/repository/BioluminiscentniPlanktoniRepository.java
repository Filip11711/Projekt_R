package hr.fer.backend.repository;

import hr.fer.backend.model.BioluminiscentniPlanktoni;
import hr.fer.backend.model.PrimaryKeyId;
import org.springframework.data.repository.CrudRepository;

public interface BioluminiscentniPlanktoniRepository extends CrudRepository<BioluminiscentniPlanktoni, PrimaryKeyId> {
}
