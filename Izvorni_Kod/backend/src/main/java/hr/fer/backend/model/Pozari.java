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
@Entity(name = "Požari")
public class Pozari {
    @EmbeddedId
    private PrimaryKeyId primaryKeyId;

    private Integer prisutnost;
}
