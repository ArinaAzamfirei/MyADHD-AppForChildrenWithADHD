package my.adhd.MyADHD.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


import java.util.Set;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Child {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private Integer age;

    @Column()
    private String gender;

    @Column(nullable = false)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User userId;

    @OneToMany(mappedBy = "childId", fetch=FetchType.LAZY, cascade = CascadeType.MERGE)
    @JsonIgnore
    private Set<Tests> childTest;

    @OneToMany(mappedBy = "childId", fetch=FetchType.LAZY, cascade = CascadeType.MERGE)
    @JsonIgnore
    private Set<GameResults> gameResults;
}
