package pl.familiamed.FamiliaNET.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.familiamed.FamiliaNET.model.PriceRecord;

@Repository
public interface PriceRecordRepository extends JpaRepository <PriceRecord, Long> {

}
