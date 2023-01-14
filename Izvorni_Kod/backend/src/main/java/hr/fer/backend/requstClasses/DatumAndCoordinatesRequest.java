package hr.fer.backend.requstClasses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DatumAndCoordinatesRequest {
    private LocalDateTime DatumVrijeme;
    private Integer longitude;
    private Integer latitude;
}
