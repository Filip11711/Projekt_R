package hr.fer.backend.repository;

import hr.fer.backend.model.Naoblake;
import hr.fer.backend.model.PrimaryKeyId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NaoblakeRepository extends CrudRepository<Naoblake, PrimaryKeyId> {
}
