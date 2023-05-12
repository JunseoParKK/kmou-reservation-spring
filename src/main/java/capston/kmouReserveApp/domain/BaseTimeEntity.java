package capston.kmouReserveApp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity{

    @CreatedDate
    @JsonIgnore
    @Column(name = "create_date")
    private LocalDateTime createDate;

    @CreatedDate
    @JsonIgnore
    @Column(name = "update_date")
    private LocalDateTime modifiedDate;
}
