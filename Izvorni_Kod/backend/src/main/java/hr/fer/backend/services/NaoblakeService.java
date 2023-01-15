package hr.fer.backend.services;

import hr.fer.backend.model.Naoblake;
import hr.fer.backend.model.PrimaryKeyId;
import hr.fer.backend.repository.NaoblakeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
@AllArgsConstructor
public class NaoblakeService {
    private final NaoblakeRepository naoblakeRepository;

    public boolean downloadData(Date datum) {
        try {
            FileUtils.copyURLToFile(
                    new URL("https://neo.gsfc.nasa.gov/servlet/RenderData?si=1845943&cs=rgb&format=CSV&width=360&height=180"),
                    new File("DataFolder\\Naoblaka.csv"));
        } catch (Exception exc) {
            return false;
        }

        List<Naoblake> naoblake = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("DataFolder\\Naoblaka.csv"));) {
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
                                naoblake.add(new Naoblake(new PrimaryKeyId(datum, Longitude, Latitude), 2));
                            } else if (value < 0.3) {
                                naoblake.add(new Naoblake(new PrimaryKeyId(datum, Longitude, Latitude), 0));
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
        insertAll(naoblake);
        return true;
    }

    public List<Naoblake> getNaoblakeByDatum(Date datum) {
        List<Naoblake> naoblake = new ArrayList<>();
        for (int i = -179; i <= 180; i++) {
            for (int j = 90; j >= -90; j--) {
                Naoblake naoblaka = naoblakeRepository.findByPrimaryKeyId(new PrimaryKeyId(datum, i, j));
                if (naoblaka == null) {
                    naoblake.add(new Naoblake(new PrimaryKeyId(datum, i, j), 1));
                } else {
                    if (naoblaka.getPrisutnost() == 2) {
                        naoblake.add(naoblaka);
                    }
                }
            }
        }
        return naoblake;
    }

    public Naoblake getNaoblakaByDatumAndLocation(Date datum, Integer Longitude, Integer Latitude) {
        return naoblakeRepository.findByPrimaryKeyId(new PrimaryKeyId(datum, Longitude, Latitude));
    }

    public Boolean existsByDatum(Date datum) {
        return naoblakeRepository.existsByPrimaryKeyId_Datum(datum);
    }

    public void insertAll(List<Naoblake> naoblake) {
        int batchSize = 50;
        int total = naoblake.size();

        for (int i = 0; i < total; i = i + batchSize) {
            if( i+ batchSize > total){
                List<Naoblake> naoblake1 = naoblake.subList(i, total - 1);
                naoblakeRepository.saveAll(naoblake1);
                naoblakeRepository.flush();
                break;
            }
            List<Naoblake> naoblake1 = naoblake.subList(i, i + batchSize);
            naoblakeRepository.saveAll(naoblake1);
            naoblakeRepository.flush();
        }
    }
}
