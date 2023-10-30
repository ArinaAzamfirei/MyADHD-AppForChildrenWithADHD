package my.adhd.MyADHD.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Entity
@Getter
@Setter
public class Question {

//    @Id
//    @Column(nullable = false, updatable = false)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;

    @Id
    @Column(nullable = false)
    private Long number;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private String qTypeId;

    @ManyToMany(fetch=FetchType.EAGER, mappedBy = "qItemQuestions")
    @JsonIgnore
    private Set<Item> qItemItems;

//    @ManyToMany(fetch=FetchType.EAGER, mappedBy = "qVariant")
//    private Set<User> qVariantUsers;

}
