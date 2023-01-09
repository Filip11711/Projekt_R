package hr.fer.backend.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PolarnaSvijetlost")
public class PolarnaSvijetlost {
    @EmbeddedId
    private PrimaryKey primaryKey;

    private Integer prisutnost;
}
