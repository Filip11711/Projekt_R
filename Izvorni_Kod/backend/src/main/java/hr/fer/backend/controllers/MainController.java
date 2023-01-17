package hr.fer.backend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import hr.fer.backend.model.*;
import hr.fer.backend.requstClasses.DatumAndCoordinatesRequest;
import hr.fer.backend.requstClasses.RequestList;
import hr.fer.backend.responseClasses.BioluminiscentniPlanktoniResponse;
import hr.fer.backend.responseClasses.DatumResponse;
import hr.fer.backend.services.BioluminiscentniPlanktoniService;
import hr.fer.backend.services.NaoblakeService;
import hr.fer.backend.services.PolarnaSvijetlostService;
import hr.fer.backend.services.PozariService;
import jakarta.servlet.http.HttpServletResponse;
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
    ResponseEntity<?> getDataByDatum( @RequestParam LocalDateTime DatumVrijeme) {
        LocalDate date = DatumVrijeme.toLocalDate();
        Date datum = Date.valueOf(DatumVrijeme.toLocalDate());
        Timestamp datumvrijeme = Timestamp.valueOf(DatumVrijeme);

        List<BioluminiscentniPlanktoniResponse> planktioni = bioluminiscentniPlanktoniService.getBioluminiscentnePlanktoneByDatum(date);
        List<Pozari> pozari = pozariService.downloadDataByDatum(datum);
        List<Naoblake> naoblake = naoblakeService.downloadDataByDatum(datum);

        Timestamp closest = polarnaSvijetlostService.existByDatum(datumvrijeme);
        List<PolarnaSvijetlost> polarna = polarnaSvijetlostService.getPolarnaSvijetlostByDatum(closest);

        return ResponseEntity.ok(new DatumResponse(naoblake, planktioni, pozari, polarna));
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/api/getByDatumAndCoordinates")
    void getCSVFile(HttpServletResponse servletResponse, @RequestBody String json) throws IOException {
        RequestList requestList = new ObjectMapper().registerModule(new JSR310Module()).readValue(json, RequestList.class);

        DatumResponse datumResponse = new DatumResponse(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        for(DatumAndCoordinatesRequest request: requestList.getRequestList()) {
            LocalDate date = request.getDatumVrijeme().toLocalDate();
            Date datum = Date.valueOf(date);
            Timestamp datumvrijeme = Timestamp.valueOf(request.getDatumVrijeme());

            Naoblake naoblaka = naoblakeService.downloadDataByDatumAndCoordinates(datum, request.getLongitude(), request.getLatitude());

            if (naoblaka == null) {
                naoblaka = new Naoblake(new PrimaryKeyId(datum, request.getLongitude(), request.getLatitude()), 2);
            }
            BioluminiscentniPlanktoniResponse plaktoni = bioluminiscentniPlanktoniService.getgetBioluminiscentnePlanktoneByDatumAndCoordinates(date, request.getLongitude(), request.getLatitude());

            Pozari pozar = pozariService.downloadDataByDatumAndCoordinates(datum, request.getLongitude(), request.getLatitude());
            if (pozar == null) {
                pozar = new Pozari(new PrimaryKeyId(datum, request.getLongitude(), request.getLatitude()), 2);
            }

            Timestamp closest = polarnaSvijetlostService.existByDatum(datumvrijeme);
            PolarnaSvijetlost polarna = polarnaSvijetlostService.getPolarnaSvijetlostByDatumAndCoordinates(closest, request.getLongitude(), request.getLatitude());
            if (polarna == null) {
                polarna = new PolarnaSvijetlost(new PrimaryKey(datumvrijeme, request.getLongitude(), request.getLatitude()), 0);
            }

            datumResponse.getNaoblake().add(naoblaka);
            datumResponse.getPlanktoni().add(plaktoni);
            datumResponse.getPozari().add(pozar);
            datumResponse.getPolarna().add(polarna);
        }
        
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"PrirodnePojave.csv\"");
        try (CSVPrinter csvPrinter = new CSVPrinter(servletResponse.getWriter(), CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("Vremenska Oznaka", "Latitude", "Longitude", "Naoblake", "Pozari", "Polarna svijetlost", "Bioluminiscentni planktoni");
            for(int i = 0; i < datumResponse.getNaoblake().size(); i++) {
                csvPrinter.printRecord(requestList.getRequestList().get(i).getDatumVrijeme(),
                                        datumResponse.getNaoblake().get(i).getPrimaryKeyId().getLongitude(),
                                        datumResponse.getNaoblake().get(i).getPrimaryKeyId().getLatitude(),
                                        datumResponse.getNaoblake().get(i).getPrisutnost(),
                                        datumResponse.getPozari().get(i).getPrisutnost(),
                                        datumResponse.getPolarna().get(i).getPrisutnost(),
                                        datumResponse.getPlanktoni().get(i).getPrisutnost());
            }
        } catch (IOException e) {

        }

    }
}
