package pl.familiamed.FamiliaNET.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.familiamed.FamiliaNET.model.Category;
import pl.familiamed.FamiliaNET.model.Post;
import pl.familiamed.FamiliaNET.repositories.PostRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;


    public List<Post> getAll(){
        return postRepository.findAll();
    }

    public List<Post> getByCategory(Category category) { return  postRepository.findByCategory(category); }

    public Post getById (Long id){
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(String.format("Cannot find post with id: " + id)));
    }

    public Post create (Post post){
        return postRepository.saveAndFlush(post);
    }
    public Post update (Post post){
        return postRepository.save(post);
    }
    public void delete (Long postId){ postRepository.deleteById(postId);}





}
