package hr.fer.backend.repository;

import hr.fer.backend.model.Naoblake;
import hr.fer.backend.model.PrimaryKeyId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface NaoblakeRepository extends CrudRepository<Naoblake, PrimaryKeyId> {
    List<Naoblake> findAllByPrimaryKeyId_DatumAndPrisutnostIsGreaterThanEqual(Date datum, Integer prisutnost);
    Naoblake findByPrimaryKeyId(PrimaryKeyId primaryKeyId);
    Boolean existsByPrimaryKeyId_Datum(Date datum);
}
