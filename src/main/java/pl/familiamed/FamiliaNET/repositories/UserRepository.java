package pl.familiamed.FamiliaNET.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import pl.familiamed.FamiliaNET.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
