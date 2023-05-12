package capston.kmouReserveApp.domain.user.entity;

import capston.kmouReserveApp.domain.BaseTimeEntity;
import capston.kmouReserveApp.domain.reservation.entity.Reservation;
import capston.kmouReserveApp.domain.user.dto.SignUpRequest;
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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false,unique = true)
    private String uuid;

    private String name;

    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Column(length = 20, nullable = false)
    private String password;

    @Column(name = "phone_num",unique = true)
    private String phoneNum;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();

    @Builder
    public User(Long id, String uuid, String name, String email, String password, String phoneNum) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNum = phoneNum;
    }

    public static User of(SignUpRequest signUpRequest){
        return User.builder()
                .name(signUpRequest.getName())
                .email(signUpRequest.getEmail())
                .password(signUpRequest.getPassword())
                .phoneNum(signUpRequest.getPhoneNum())
                .uuid(signUpRequest.getUuid())
                .build();
    }
}
