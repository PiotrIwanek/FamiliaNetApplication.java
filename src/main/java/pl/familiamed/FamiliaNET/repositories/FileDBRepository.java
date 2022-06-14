package pl.familiamed.FamiliaNET.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.familiamed.FamiliaNET.model.FileDB;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {

  FileDB findByName (String name);

}
