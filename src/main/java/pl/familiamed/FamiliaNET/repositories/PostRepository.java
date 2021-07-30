package pl.familiamed.FamiliaNET.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.familiamed.FamiliaNET.model.Category;
import pl.familiamed.FamiliaNET.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

  List<Post> findByCategory (Category category);

}
