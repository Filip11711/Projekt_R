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
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

@Service
@AllArgsConstructor
public class NaoblakeService {
    private final NaoblakeRepository naoblakeRepository;

    public List<Naoblake> downloadDataByDatum(Date datum) {
        String dateForLink = datum.toString();
        HashMap<String, String> links = new HashMap<String, String>();
        links.put("2023-01-17","https://neo.gsfc.nasa.gov/servlet/RenderData?si=1845946&cs=rgb&format=CSV&width=360&height=180");
        links.put("2023-01-16","https://neo.gsfc.nasa.gov/servlet/RenderData?si=1845945&cs=rgb&format=CSV&width=360&height=180");
        links.put("2023-01-15","https://neo.gsfc.nasa.gov/servlet/RenderData?si=1845944&cs=rgb&format=CSV&width=360&height=180");
        links.put("2023-01-14","https://neo.gsfc.nasa.gov/servlet/RenderData?si=1845943&cs=rgb&format=CSV&width=360&height=180");
        links.put("2023-01-13","https://neo.gsfc.nasa.gov/servlet/RenderData?si=1845943&cs=rgb&format=CSV&width=360&height=180");
        links.put("2023-01-12","https://neo.gsfc.nasa.gov/servlet/RenderData?si=1845941&cs=rgb&format=CSV&width=360&height=180");
        links.put("2023-01-11","https://neo.gsfc.nasa.gov/servlet/RenderData?si=1845940&cs=rgb&format=CSV&width=360&height=180");
        links.put("2023-01-10","https://neo.gsfc.nasa.gov/servlet/RenderData?si=1845960&cs=rgb&format=CSV&width=360&height=180");

        try {
            FileUtils.copyURLToFile(
                    new URL(links.get(dateForLink)),
                    new File("DataFolder\\Naoblaka.csv"));
        } catch (Exception exc) {
            return new ArrayList<>();
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
                            if (Longitude % 3 == 0 && Latitude % 2 == 0) {
                                if (!(value >= 0.0 && value <= 1.0)) {
                                    naoblake.add(new Naoblake(new PrimaryKeyId(datum, Longitude, Latitude), 2));
                                } else if (value >= 0.3) {
                                    naoblake.add(new Naoblake(new PrimaryKeyId(datum, Longitude, Latitude), 1));
                                }
                            }
                        } catch (Exception exc) {}
                        Longitude += 1;
                    }
                }
                Latitude -= 1;
            }
        } catch (FileNotFoundException exc) {
            return new ArrayList<>();
        }
        return naoblake;
    }

    public Naoblake downloadDataByDatumAndCoordinates(Date datum, int longitude, int latitude) {
        String dateForLink = datum.toString();
        HashMap<String, String> links = new HashMap<String, String>();
        links.put("2023-01-17","https://neo.gsfc.nasa.gov/servlet/RenderData?si=1845946&cs=rgb&format=CSV&width=360&height=180");
        links.put("2023-01-16","https://neo.gsfc.nasa.gov/servlet/RenderData?si=1845945&cs=rgb&format=CSV&width=360&height=180");
        links.put("2023-01-15","https://neo.gsfc.nasa.gov/servlet/RenderData?si=1845944&cs=rgb&format=CSV&width=360&height=180");
        links.put("2023-01-14","https://neo.gsfc.nasa.gov/servlet/RenderData?si=1845943&cs=rgb&format=CSV&width=360&height=180");
        links.put("2023-01-13","https://neo.gsfc.nasa.gov/servlet/RenderData?si=1845943&cs=rgb&format=CSV&width=360&height=180");
        links.put("2023-01-12","https://neo.gsfc.nasa.gov/servlet/RenderData?si=1845941&cs=rgb&format=CSV&width=360&height=180");
        links.put("2023-01-11","https://neo.gsfc.nasa.gov/servlet/RenderData?si=1845940&cs=rgb&format=CSV&width=360&height=180");
        links.put("2023-01-10","https://neo.gsfc.nasa.gov/servlet/RenderData?si=1845960&cs=rgb&format=CSV&width=360&height=180");

        try {
            FileUtils.copyURLToFile(
                    new URL(links.get(dateForLink)),
                    new File("DataFolder\\Naoblaka.csv"));
        } catch (Exception exc) {
            return null;
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
                            if (Longitude == longitude && Latitude == latitude) {
                                if (!(value >= 0.0 && value <= 1.0)) {
                                    return new Naoblake(new PrimaryKeyId(datum, Longitude, Latitude), 2);
                                } else if (value >= 0.3) {
                                    return new Naoblake(new PrimaryKeyId(datum, Longitude, Latitude), 1);
                                } else {
                                    return new Naoblake(new PrimaryKeyId(datum, Longitude, Latitude), 0);
                                }
                            }
                        } catch (Exception exc) {}
                        Longitude += 1;
                    }
                }
                Latitude -= 1;
            }
        } catch (FileNotFoundException exc) {
            return null;
        }
        return null;
    }
}
