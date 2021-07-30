package pl.familiamed.FamiliaNET.api.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import pl.familiamed.FamiliaNET.model.Category;

import pl.familiamed.FamiliaNET.model.enums.Prioryty;

@Getter
@Setter
public class PostDTO {

  private Long id;

  private String name;
  private LocalDateTime dateTime;

  private Prioryty priority;

  private String main;

  private Category category;

  private List<FileDTO> fileDTOList;

  private List<String> userNames;

  public PostDTO(long id, String name, LocalDateTime dateTime, Prioryty priority, String main, Category category
      , List<FileDTO> fileDTOList, List<String> userNames) {

    this.id = id;
    this.name = name;
    this.dateTime = dateTime;
    this.priority = priority;
    this.main = main;
    this.category = category;
    this.fileDTOList = fileDTOList;
    this.userNames = userNames;
  }

//  public static PostDTO fromPost(Post post) {
//    return new PostDTO(post.getId(), post.getName(), post.getDateTime(), post.getPriority(), post.getMain(),
//        post.getCategory(),
//        post.getFileDBList().stream().map(FileDTO::fromFile).collect(Collectors.toList()), post.getUserNames());
//  }

}
