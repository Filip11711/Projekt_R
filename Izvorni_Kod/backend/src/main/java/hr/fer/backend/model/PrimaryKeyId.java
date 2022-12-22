package hr.fer.backend.model;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class PrimaryKeyId implements Serializable {
    private Date datum;
    private String longitude;
    private String latitude;
}
