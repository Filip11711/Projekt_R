package hr.fer.backend.services;

import hr.fer.backend.model.Naoblake;
import hr.fer.backend.model.PrimaryKeyId;
import hr.fer.backend.repository.NaoblakeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

@Service
@AllArgsConstructor
public class NaoblakeService {
    private final NaoblakeRepository naoblakeRepository;

    public boolean downloadData(Date datum) {

        try (Scanner scanner = new Scanner(new File("book.csv"));) {
            Integer Latitude = 90;
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                try (Scanner rowScanner = new Scanner(line);) {
                    Integer Longitude = -179;
                    rowScanner.useDelimiter(",");
                    while(rowScanner.hasNext()) {
                        Float value = rowScanner.nextFloat();
                        if (!(value >= 0.0 && value <= 1.0)) {
                            naoblakeRepository.save(new Naoblake(new PrimaryKeyId(datum, Longitude, Latitude), 2));
                        } else if (value < 0.5) {
                            naoblakeRepository.save(new Naoblake(new PrimaryKeyId(datum, Longitude, Latitude), 0));
                        } else {
                            naoblakeRepository.save(new Naoblake(new PrimaryKeyId(datum, Longitude, Latitude), 1));
                        }
                        Longitude += 1;
                    }
                } catch (Exception exc) {
                    return false;
                }
                Latitude -= 1;
            }
        } catch (Exception exc) {
            return false;
        }
        return true;
    }

    public List<Naoblake> getNaoblakeByDatum(Date datum) {
        return naoblakeRepository.findAllByPrimaryKeyId_DatumAndPrisutnostIsGreaterThanEqual(datum, 1);
    }

    public Naoblake getNaoblakaByDatumAndLocation(Date datum, Integer Longitude, Integer Latitude) {
        return naoblakeRepository.findByPrimaryKeyId(new PrimaryKeyId(datum, Longitude, Latitude));
    }

    public Boolean existsByDatum(Date datum) {
        return naoblakeRepository.existsByPrimaryKeyId_Datum(datum);
    }
}
