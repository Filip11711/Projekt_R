package hr.fer.backend.responseClasses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BioluminiscentniPlanktoniResponse {
    private LocalDate datum;
    private Integer longitude;
    private Integer latitude;
    private Integer prisutnost;
}
