package pl.familiamed.FamiliaNET.model;

import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import pl.familiamed.FamiliaNET.model.enums.Prioryty;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name ;
    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    private Prioryty priority;

    @Type(type="text")
    private String main;

    @OneToOne
    private Category category;

    @OneToMany
    @JoinColumn (name = "post_id" )
    @Nullable
    private List<FileDB> fileDBList;

    @Nullable
    @ElementCollection
    private List<String> userNames;
}
