package hr.fer.backend.responseClasses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BioluminiscentniPlanktoniResponse {
    private Date datum;
    private String longitude;
    private String latitude;
    private Integer prisutnost;
}
