package pl.familiamed.FamiliaNET.repositories;

import pl.familiamed.FamiliaNET.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByParentIdIsNull();
    Category findByNameAndParentIdIsNull( String name );


}
