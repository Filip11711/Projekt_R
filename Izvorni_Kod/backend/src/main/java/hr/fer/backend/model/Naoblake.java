package hr.fer.backend.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Naoblake")
public class Naoblake {
    @EmbeddedId
    private PrimaryKeyId primaryKeyId;

    private Integer prisutnost;
}
