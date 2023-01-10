package hr.fer.backend.controllers;

import hr.fer.backend.model.Naoblake;
import hr.fer.backend.model.Pozari;
import hr.fer.backend.model.PrimaryKeyId;
import hr.fer.backend.responseClasses.BioluminiscentniPlanktoniResponse;
import hr.fer.backend.responseClasses.DatumResponse;
import hr.fer.backend.services.BioluminiscentniPlanktoniService;
import hr.fer.backend.services.NaoblakeService;
import hr.fer.backend.services.PolarnaSvijetlostService;
import hr.fer.backend.services.PozariService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    ResponseEntity<?> getDataByDatum(@Valid @RequestParam LocalDateTime DatumVrijeme) throws IOException {
        LocalDate date = DatumVrijeme.toLocalDate();
        Date datum = Date.valueOf(DatumVrijeme.toLocalDate());
        Timestamp datumvrijeme = Timestamp.valueOf(DatumVrijeme);

        if (!naoblakeService.existsByDatum(datum) && !pozariService.existsByDatum(datum)) {
            if(!naoblakeService.downloadData(datum) && !pozariService.downloadData(datum)) {
                return ResponseEntity.ok("Error");
            }
        }

        List<BioluminiscentniPlanktoniResponse> planktioni = bioluminiscentniPlanktoniService.getBioluminiscentnePlanktoneByDatum(date);
        List<Pozari> pozari = pozariService.getPozariByDatum(datum);
        List<Naoblake> naoblake = naoblakeService.getNaoblakeByDatum(datum);
        return ResponseEntity.ok(new DatumResponse(naoblake, planktioni, pozari));
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/api/getByDatumAndCoordinates")
    void getCSVFile(HttpServletResponse servletResponse, @Valid @RequestParam LocalDateTime DatumVrijeme, Integer longitude, Integer latitude) throws IOException {
        LocalDate date = DatumVrijeme.toLocalDate();
        Date datum = Date.valueOf(DatumVrijeme.toLocalDate());
        Timestamp datumvrijeme = Timestamp.valueOf(DatumVrijeme);
        Boolean check = true;
        if (!naoblakeService.existsByDatum(datum) && !pozariService.existsByDatum(datum)) {
            check = (naoblakeService.downloadData(datum) && pozariService.downloadData(datum));
        }
        Naoblake naoblaka = naoblakeService.getNaoblakaByDatumAndLocation(datum, longitude, latitude);
        
        if (naoblaka == null) {
            naoblaka = new Naoblake(new PrimaryKeyId(datum, longitude, latitude), 1);
        }
        BioluminiscentniPlanktoniResponse plaktoni = bioluminiscentniPlanktoniService.getgetBioluminiscentnePlanktoneByDatumAndCoordinates(date, longitude, latitude);

        Pozari pozar = pozariService.getPozariByDatumAndLocation(datum, longitude, latitude);
        if (pozar == null) {
            pozar = new Pozari(new PrimaryKeyId(datum, longitude, latitude), 1);
        }
        
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"PrirodnePojave.csv\"");
        try (CSVPrinter csvPrinter = new CSVPrinter(servletResponse.getWriter(), CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("Vremenska Oznaka", "Longitude", "Latitude", "Naoblake", "Bioluminiscentni planktoni", "Pozari");
            if (check) {
                csvPrinter.printRecord(DatumVrijeme, longitude, latitude, naoblaka.getPrisutnost(), plaktoni.getPrisutnost(), pozar.getPrisutnost());
            } else {
                csvPrinter.printRecord("Error");
            }
        } catch (IOException e) {

        }

    }

    @GetMapping("/api/test")
    void testmethod() throws IOException {
        polarnaSvijetlostService.downloadDataPolarna();
    }
}
