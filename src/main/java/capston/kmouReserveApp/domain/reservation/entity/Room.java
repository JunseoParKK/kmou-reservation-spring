package capston.kmouReserveApp.domain.reservation.entity;

import capston.kmouReserveApp.domain.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "room")
public class Room extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Reservation> reservations = new ArrayList<>();

    @Column(name = "max_num")
    private int maxNum;

    @Builder
    public Room(Long id, String name, int maxNum) {
        this.id = id;
        this.name = name;
        this.maxNum = maxNum;
    }
}
