package pl.familiamed.FamiliaNET.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.familiamed.FamiliaNET.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
