package pl.familiamed.FamiliaNET.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.familiamed.FamiliaNET.model.ChartFileCounter;

public interface ChartFileCounterRepository extends JpaRepository <ChartFileCounter , Long> {

}
