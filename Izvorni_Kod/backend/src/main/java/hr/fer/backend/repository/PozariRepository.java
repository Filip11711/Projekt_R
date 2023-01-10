package hr.fer.backend.repository;

import hr.fer.backend.model.Pozari;
import hr.fer.backend.model.PrimaryKeyId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.sql.Date;
import java.util.List;

@Repository
public interface PozariRepository extends JpaRepository<Pozari, PrimaryKeyId> {
    List<Pozari> findAllByPrimaryKeyId_DatumAndPrisutnostIsGreaterThanEqual(Date datum, Integer prisutnost);
    Pozari findByPrimaryKeyId(PrimaryKeyId primaryKeyId);
    Boolean existsByPrimaryKeyId_Datum(Date datum);

    @Transactional
    @Modifying
    @Query(value = "insert into Pozari(datum, longitude, latitude, prisutnost) values (:datum, :longitude, :latitude, :prisutnost)", nativeQuery = true) 
    void insertPozar(@Param("datum") Date datum, @Param("longitude") Integer longitude, @Param("latitude") Integer latitude, @Param("prisutnost") Integer prisutnost);
}
