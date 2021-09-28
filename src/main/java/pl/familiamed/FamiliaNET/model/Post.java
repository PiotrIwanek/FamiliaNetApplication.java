package pl.familiamed.FamiliaNET.model;

import com.sun.istack.Nullable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private LocalDateTime deadLineDate;
    private boolean shouldBeSign;

    @Enumerated(EnumType.STRING)
    private Prioryty priority;

    @Column(columnDefinition="text")
    private String main;

    @OneToOne
    private Category category;

    @OneToMany
    @JoinColumn (name = "post_id" )
    @Nullable
    private List<FileDB> fileDBList;

    @OneToMany
    @JoinColumn (name = "post_id" )
    @Nullable
    private List<Acceptors> users;

    @OneToMany
    @JoinColumn (name = "post_id" )
    @Nullable
    private List<Sign> signs;


}

