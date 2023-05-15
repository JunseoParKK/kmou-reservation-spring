package capston.kmouReserveApp.domain.reservation.dto;

import capston.kmouReserveApp.domain.reservation.entity.Reservation;
import capston.kmouReserveApp.domain.reservation.entity.Room;
import capston.kmouReserveApp.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
public class ValidateFindByIdDto {
    private User user;
    private Room room;
    private Reservation reservation;

    @Builder
    public ValidateFindByIdDto(User user, Room room, Reservation reservation) {
        this.user = user;
        this.room = room;
        this.reservation = reservation;
    }
}
