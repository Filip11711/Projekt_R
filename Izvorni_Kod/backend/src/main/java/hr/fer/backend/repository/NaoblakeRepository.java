package hr.fer.backend.repository;

import hr.fer.backend.model.Naoblake;
import hr.fer.backend.model.PrimaryKeyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface NaoblakeRepository extends JpaRepository<Naoblake, PrimaryKeyId> {
    Naoblake findByPrimaryKeyId(PrimaryKeyId primaryKeyId);
    Boolean existsByPrimaryKeyId_Datum(Date datum);
}
