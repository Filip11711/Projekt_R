package hr.fer.backend.responseClasses;

import hr.fer.backend.model.Naoblake;
import hr.fer.backend.model.Pozari;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DatumResponse {
    private List<Naoblake> naoblake;
}