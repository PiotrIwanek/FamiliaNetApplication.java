package pl.familiamed.FamiliaNET.api.model;

import java.time.LocalDateTime;
import java.util.List;

import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;
import pl.familiamed.FamiliaNET.model.Acceptors;
import pl.familiamed.FamiliaNET.model.Category;

import pl.familiamed.FamiliaNET.model.Post;
import pl.familiamed.FamiliaNET.model.Sign;
import pl.familiamed.FamiliaNET.model.User;
import pl.familiamed.FamiliaNET.model.enums.Prioryty;

@Getter
@Setter
public class PostDTO {

  private Long id;

  private String name;
  private LocalDateTime dateTime;
  private LocalDateTime deadLineDate;
  private boolean shouldBeSign;

  private String priority;

  private String main;

  private Category category;

  private List<FileDTO> fileDBList;
  private List<Acceptors> users;
  private List<Sign> signs;



  public PostDTO(long id, String name, LocalDateTime dateTime, LocalDateTime deadLineDate , Boolean shouldBeSign , String priority, String main, Category category
      , List<FileDTO> fileDBList , List<Acceptors> users , List<Sign> signs) {

    this.id = id;
    this.name = name;
    this.dateTime = dateTime;
    this.deadLineDate = deadLineDate;
    this.shouldBeSign = shouldBeSign;
    this.priority = priority;
    this.main = main;
    this.category = category;
    this.fileDBList = fileDBList;
    this.users = users;
    this.signs = signs;

  }

  public static PostDTO fromPost(Post post) {
    return new PostDTO(post.getId(), post.getName(), post.getDateTime(), post.getDeadLineDate() ,
        post.isShouldBeSign() , post.getPriority().toString(), post.getMain(), post.getCategory(),
        post.getFileDBList().stream().map(FileDTO::fromFile).collect(Collectors.toList()), post.getUsers(),
        post.getSigns());
  }

}
