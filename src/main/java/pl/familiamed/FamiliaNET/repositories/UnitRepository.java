package pl.familiamed.FamiliaNET.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.familiamed.FamiliaNET.model.Unit;

@Repository
public interface UnitRepository  extends JpaRepository <Unit, Long > {

}
