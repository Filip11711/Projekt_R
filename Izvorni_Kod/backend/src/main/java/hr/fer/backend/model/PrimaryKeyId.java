package hr.fer.backend.model;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class PrimaryKeyId implements Serializable {
    private LocalDateTime datum;
    private String koordinate;
}
