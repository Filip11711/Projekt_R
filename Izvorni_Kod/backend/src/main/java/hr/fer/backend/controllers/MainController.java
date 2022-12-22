package hr.fer.backend.controllers;

import hr.fer.backend.model.Naoblake;
import hr.fer.backend.responseClasses.DatumResponse;
import hr.fer.backend.services.BioluminiscentniPlanktoniService;
import hr.fer.backend.services.NaoblakeService;
import hr.fer.backend.services.PolarnaSvijetlostService;
import hr.fer.backend.services.PozariService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;

@RestController
@AllArgsConstructor
public class MainController {
    private final NaoblakeService naoblakeService;
    private final PozariService pozariService;
    private final PolarnaSvijetlostService polarnaSvijetlostService;
    private final BioluminiscentniPlanktoniService bioluminiscentniPlanktoniService;

    @CrossOrigin(origins = "*")
    @GetMapping("api/getByDatum")
    ResponseEntity<?> getDataByDatum(@Valid @RequestBody Date datum) {
        if (!naoblakeService.existsByDatum(datum)) {
            naoblakeService.downloadData(datum);
        }
        List<Naoblake> naoblake = naoblakeService.getNaoblakeByDatum(datum);
        return ResponseEntity.ok(new DatumResponse(naoblake));
    }
}
