package hr.fer.backend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import hr.fer.backend.model.Naoblake;
import hr.fer.backend.model.PolarnaSvijetlost;
import hr.fer.backend.model.Pozari;
import hr.fer.backend.model.PrimaryKeyId;
import hr.fer.backend.requstClasses.DatumAndCoordinatesRequest;
import hr.fer.backend.requstClasses.RequestList;
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
import java.util.ArrayList;
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

        /*if (!naoblakeService.existsByDatum(datum)) {
            if(!naoblakeService.downloadData(datum)) {
                return ResponseEntity.ok("Error");
            }
        }

        if (!pozariService.existsByDatum(datum)) {
            if(!pozariService.downloadData(datum)) {
                return ResponseEntity.ok("Error");
            }
        }*/

        List<BioluminiscentniPlanktoniResponse> planktioni = bioluminiscentniPlanktoniService.getBioluminiscentnePlanktoneByDatum(date);
        List<Pozari> pozari = pozariService.getPozariByDatum(datum);
        List<Naoblake> naoblake = naoblakeService.getNaoblakeByDatum(datum);

        Timestamp closest = polarnaSvijetlostService.existByDatum(datumvrijeme);
        List<PolarnaSvijetlost> polarna = polarnaSvijetlostService.getPolarnaSvijetlostByDatum(closest);

        return ResponseEntity.ok(new DatumResponse(naoblake, planktioni, pozari, polarna));
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/api/getByDatumAndCoordinates")
    void getCSVFile(HttpServletResponse servletResponse, @Valid @RequestBody String json) throws IOException {
        RequestList requestList = new ObjectMapper().registerModule(new JSR310Module()).readValue(json, RequestList.class);

        DatumResponse datumResponse = new DatumResponse(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        for(DatumAndCoordinatesRequest request: requestList.getRequestList()) {
            LocalDate date = request.getDatumVrijeme().toLocalDate();
            Date datum = Date.valueOf(date);
            Timestamp datumvrijeme = Timestamp.valueOf(request.getDatumVrijeme());

            if (!naoblakeService.existsByDatum(datum)) {
                naoblakeService.downloadData(datum);
            }
            Naoblake naoblaka = naoblakeService.getNaoblakaByDatumAndLocation(datum, request.getLongitude(), request.getLatitude());

            if (naoblaka == null) {
                naoblaka = new Naoblake(new PrimaryKeyId(datum, request.getLongitude(), request.getLatitude()), 1);
            }
            BioluminiscentniPlanktoniResponse plaktoni = bioluminiscentniPlanktoniService.getgetBioluminiscentnePlanktoneByDatumAndCoordinates(date, request.getLongitude(), request.getLatitude());

            //if (!pozariService.existsByDatum(datum)) {
              //  pozariService.downloadData(datum);
            //}

            Pozari pozar = pozariService.getPozariByDatumAndLocation(datum, request.getLongitude(), request.getLatitude());
            if (pozar == null) {
                pozar = new Pozari(new PrimaryKeyId(datum, request.getLongitude(), request.getLatitude()), 0);
            }

            Timestamp closest = polarnaSvijetlostService.existByDatum(datumvrijeme);
            PolarnaSvijetlost polarna = polarnaSvijetlostService.getPolarnaSvijetlostByDatumAndCoordinates(closest, request.getLongitude(), request.getLatitude());

            datumResponse.getNaoblake().add(naoblaka);
            datumResponse.getPlanktoni().add(plaktoni);
            datumResponse.getPozari().add(pozar);
            datumResponse.getPolarna().add(polarna);
        }
        
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"PrirodnePojave.csv\"");
        try (CSVPrinter csvPrinter = new CSVPrinter(servletResponse.getWriter(), CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("Vremenska Oznaka", "Longitude", "Latitude", "Naoblake", "Bioluminiscentni planktoni", "Pozari", "Polarna svijetlost");
            for(int i = 0; i < datumResponse.getNaoblake().size(); i++) {
                csvPrinter.printRecord(requestList.getRequestList().get(i).getDatumVrijeme(),
                                        datumResponse.getNaoblake().get(i).getPrimaryKeyId().getLongitude(),
                                        datumResponse.getNaoblake().get(i).getPrimaryKeyId().getLatitude(),
                                        datumResponse.getNaoblake().get(i).getPrisutnost(),
                                        datumResponse.getPlanktoni().get(i).getPrisutnost(),
                                        datumResponse.getPozari().get(i).getPrisutnost(),
                                        datumResponse.getPolarna().get(i).getPrisutnost());
            }
        } catch (IOException e) {

        }

    }
}
