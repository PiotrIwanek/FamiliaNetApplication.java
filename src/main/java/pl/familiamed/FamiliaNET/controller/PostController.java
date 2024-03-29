package pl.familiamed.FamiliaNET.controller;


import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.familiamed.FamiliaNET.api.model.FileDTO;
import pl.familiamed.FamiliaNET.api.model.PostDTO;
import pl.familiamed.FamiliaNET.model.Category;
import pl.familiamed.FamiliaNET.model.FileDB;
import pl.familiamed.FamiliaNET.model.Post;
import pl.familiamed.FamiliaNET.services.CategoryService;
import pl.familiamed.FamiliaNET.services.FileDBService;
import pl.familiamed.FamiliaNET.services.PostService;

@RestController
@RequestMapping("v1/post")
@CrossOrigin("*")
@AllArgsConstructor
public class PostController {

  private final PostService postService;
  private final CategoryService categoryService;
  private final FileDBService fileDBService;

  @GetMapping
  public ResponseEntity<List<PostDTO>> getAll() {
    List<PostDTO> postDTOList = postService.getAll().stream().map(post -> {

      List<FileDTO> fileDTOList = post.getFileDBList().stream().map(fileDB -> {
        String fileUrl = ServletUriComponentsBuilder
            .fromCurrentContextPath()
            .path("/v1/file/files/")
            .path(fileDB.getId())
            .toUriString();
        return new FileDTO(fileDB.getId(), fileDB.getName(), fileDB.getType(), fileUrl, fileDB.isUploaded(),
            fileDB.getSize());
      }).collect(
          Collectors.toList());
      return new PostDTO(post.getId(), post.getName(), post.getDateTime(), post.getDeadLineDate(),
          post.isShouldBeSign(), post.getPriority().toString(), post.getMain(), post.getCategory(),
          fileDTOList, post.getUsers(), post.getSigns());
    }).collect(Collectors.toList());

    return ResponseEntity.ok(postDTOList);
  }

  @GetMapping("/byCategory/{categoryId}")
  public ResponseEntity<List<PostDTO>> getByCategory(@PathVariable Long categoryId) {
    List<PostDTO> postDTOList = postService.getByCategory(categoryService.getCategoryById(categoryId)).stream()
        .map(post -> {

          List<FileDTO> fileDTOList = post.getFileDBList().stream().map(fileDB -> {
            String fileUrl = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/v1/file/")
                .path(fileDB.getId())
                .toUriString();
            return new FileDTO(fileDB.getId(), fileDB.getName(), fileDB.getType(), fileUrl, fileDB.isUploaded(),
                fileDB.getSize());
          }).collect(
              Collectors.toList());
          return new PostDTO(post.getId(), post.getName(), post.getDateTime(), post.getDeadLineDate(),
              post.isShouldBeSign(), post.getPriority().toString(), post.getMain(), post.getCategory(),
              fileDTOList, post.getUsers(), post.getSigns());
        }).collect(Collectors.toList());

    return ResponseEntity.ok(postDTOList);
  }


  @PostMapping
  public ResponseEntity<PostDTO> add(@RequestBody Post post) {
    return ResponseEntity.ok(PostDTO.fromPost(postService.create(post)));
  }

  @PostMapping("/news")
  public  ResponseEntity<PostDTO> addNews (@RequestBody Post post){
    Category news;
    if (categoryService.getSpecificCategory("News") == null) {
      news = categoryService.addCategory(null, "News");
    } else {
      news = categoryService.getSpecificCategory("News");
    }
    post.setCategory(news);
    return ResponseEntity.ok(PostDTO.fromPost(postService.create(post)));
  }

  @PutMapping("/addFile/{postId}")
  public ResponseEntity<PostDTO> addFile(@PathVariable Long postId, @RequestParam String fileId) {
    Post post = postService.getById(postId);
    FileDB fileDB = fileDBService.getFile(fileId);
    post.getFileDBList().add(fileDB);
    postService.update(post);

    return ResponseEntity.ok(PostDTO.fromPost(post));
  }

  @PutMapping("/deleteFile/{postId}")
  public ResponseEntity<PostDTO> deleteFile(@PathVariable Long postId, @RequestParam String fileId) {
    Post post = postService.getById(postId);
    FileDB fileDB = fileDBService.getFile(fileId);

    post.getFileDBList().stream().filter(file -> file.getId() != fileId);
    fileDBService.delete(fileId);
    postService.update(post);
    return ResponseEntity.ok(PostDTO.fromPost(post));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable Long id) {
    postService.delete(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/news")
  public ResponseEntity<List<PostDTO>> getNews() {
    Category news;
    if (categoryService.getSpecificCategory("News") == null) {
      news = categoryService.addCategory(null, "News");
    } else {
      news = categoryService.getSpecificCategory("News");
    }
    return ResponseEntity.ok(postService.getByCategory(news).stream().map(PostDTO::fromPost).collect(
        Collectors.toList()));
  }
}


