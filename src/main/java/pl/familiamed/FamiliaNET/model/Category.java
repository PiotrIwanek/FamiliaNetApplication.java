package pl.familiamed.FamiliaNET.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String name ;

    private Long parentId;

    @OneToMany(cascade = {CascadeType.ALL})
    private List <Category> children;




}
