package hr.fer.backend.services;

import org.apache.commons.io.FileUtils;
import hr.fer.backend.model.Pozari;
import hr.fer.backend.repository.PozariRepository;
import hr.fer.backend.model.PrimaryKeyId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import java.net.URL;
import java.io.File;
import java.util.ArrayList;
import java.net.URL;
import java.io.IOException;
import java.io.FileNotFoundException;

@Service
@AllArgsConstructor
public class PozariService {
    private final PozariRepository pozariRepository;

    public boolean downloadData(Date datum) {

        try {
            FileUtils.copyURLToFile(
                    new URL("https://neo.gsfc.nasa.gov/servlet/RenderData?si=1846176&cs=rgb&format=CSV&width=360&height=180"),
                    new File("DataFolder\\Pozari.csv"));
        } catch (Exception exc) {
            return false;
        }
        List<Pozari> pozari = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("DataFolder\\Pozari.csv"));) {
            
            Integer Latitude = 90;
            
            while(scanner.hasNextLine()) {
                
                String line = scanner.nextLine();
               
                try (Scanner rowScanner = new Scanner(line);) {
                    
                    Integer Longitude = -179;
                    rowScanner.useDelimiter(",");
                    
                    while(rowScanner.hasNext()) {
                        
                        try {
                            Float value = Float.valueOf(rowScanner.next());
                            if (!(value >= 0.0 && value <= 1.0)) {
                                pozari.add(new Pozari(new PrimaryKeyId(datum, Longitude, Latitude), 2));
                            } else if (value >= 0.2) {
                                pozari.add(new Pozari(new PrimaryKeyId(datum, Longitude, Latitude), 1));
                            }
                        } catch (Exception exc) {}

                        Longitude += 1;
                    }
                }
                Latitude -= 1;
            }
        } catch (FileNotFoundException exc) {
            return false;
        }
    
        insertAll(pozari);
        return true;
    }

    public List<Pozari> getPozariByDatum(Date datum) {
        return pozariRepository.findAllByPrimaryKeyId_DatumAndPrisutnostIsGreaterThanEqual(datum, 1);
    }

    public Pozari getPozariByDatumAndLocation(Date datum, Integer Longitude, Integer Latitude) {
        return pozariRepository.findByPrimaryKeyId(new PrimaryKeyId(datum, Longitude, Latitude));
    }

    public Boolean existsByDatum(Date datum) {
        return pozariRepository.existsByPrimaryKeyId_Datum(datum);
    }

    public void insertAll(List<Pozari> pozari) {

        int batchSize = 50;         //iteracije kroz epohu (2.pot)
        int total = pozari.size();

        for (int i = 0; i < total; i = i + batchSize) {

            if( i+ batchSize > total){
                List<Pozari> pozari1 = pozari.subList(i, total - 1);
                pozariRepository.saveAll(pozari1);
                pozariRepository.flush();
                break;
            }

            List<Pozari> pozari1 = pozari.subList(i, i + batchSize);
            pozariRepository.saveAll(pozari1);
            pozariRepository.flush();
        }
    }
}
