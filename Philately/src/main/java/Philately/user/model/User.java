package Philately.user.model;

import Philately.stamp.model.Stamp;
import Philately.stamp.model.WishedStamp;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<Stamp> stamps;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<WishedStamp> wishedStamps;

}
