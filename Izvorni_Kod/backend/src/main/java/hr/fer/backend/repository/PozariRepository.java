package hr.fer.backend.repository;

import hr.fer.backend.model.Pozari;
import hr.fer.backend.model.PrimaryKeyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.sql.Date;
import java.util.List;

@Repository
public interface PozariRepository extends JpaRepository<Pozari, PrimaryKeyId> {
    List<Pozari> findAllByPrimaryKeyId_DatumAndPrisutnostIsGreaterThanEqual(Date datum, Integer prisutnost);

    Pozari findByPrimaryKeyId(PrimaryKeyId primaryKeyId);

    Boolean existsByPrimaryKeyId_Datum(Date datum);

}
