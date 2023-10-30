package my.adhd.MyADHD.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Getter
@Setter
@Entity
public class Tests {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer testId;

    @Column(nullable = false)
    private String timestamp;

    @Column
    private String diagnostic;

    @Column
    private Integer percentage;

    @Column
    private String childCode;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "childId")
    private Child childId;

}
