package capston.kmouReserveApp.domain.user.entity;

import capston.kmouReserveApp.domain.BaseTimeEntity;
import capston.kmouReserveApp.domain.reservation.entity.Reservation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private UUID id;

    private String name;

    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Column(length = 20, nullable = false)
    private String password;

    @Column(name = "phone_num")
    private String phoneNum;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();

    @Builder
    public User(UUID id, String name, String email, String password, String phoneNum) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNum = phoneNum;
    }
}
