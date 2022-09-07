package jtoon.jmao5.duckdns.org.domain.users;

import jtoon.jmao5.duckdns.org.domain.config.BaseTime;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 20)
    private String userName;

    @Column(length = 80)
    private String email;

    @Column(length = 512)
    private String profileImage;
}
