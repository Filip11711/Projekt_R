package hr.fer.backend.repository;

import hr.fer.backend.model.PolarnaSvijetlost;
import hr.fer.backend.model.PrimaryKey;
import hr.fer.backend.model.PrimaryKeyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface PolarnaSvijetlostRepository extends JpaRepository<PolarnaSvijetlost, PrimaryKeyId> {
    List<PolarnaSvijetlost> findAllByPrimaryKey_Datumvrijeme(Timestamp datumvrijeme);
    PolarnaSvijetlost findByPrimaryKey(PrimaryKey primaryKey);

    @Query(value = "select datumvrijeme from polarna order by abs(datumvrijeme, :datumvrijeme)", nativeQuery = true)
    Timestamp findNearestTimestamp(@Param("datumvrijeme") Timestamp datumvrijeme);
}
