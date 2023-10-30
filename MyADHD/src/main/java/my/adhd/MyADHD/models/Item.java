package my.adhd.MyADHD.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Entity
@Getter
@Setter
public class Item {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "\"description\"")
    private String description;

    @ManyToMany(fetch=FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "qitem",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private Set<Question> qItemQuestions;

//    @ManyToMany(fetch=FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinTable(
//            name = "item_test",
//            joinColumns = @JoinColumn(name = "item_id"),
//            inverseJoinColumns = @JoinColumn(name = "tests_test_id")
//    )
//    private Set<Tests> itemTest;

}
