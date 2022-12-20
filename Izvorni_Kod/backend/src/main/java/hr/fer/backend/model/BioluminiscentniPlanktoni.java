package hr.fer.backend.model;

import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "BioluminiscentniPlanktoni")
public class BioluminiscentniPlanktoni {
    @EmbeddedId
    private PrimaryKeyId primaryKeyId;

    private Integer prisutnost;
}
