package hr.fer.backend.services;

import org.apache.commons.io.FileUtils;
import hr.fer.backend.model.Pozari;
import hr.fer.backend.repository.PozariRepository;
import hr.fer.backend.model.PrimaryKeyId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.net.URL;
import java.io.File;
import java.util.ArrayList;
import java.io.FileNotFoundException;

@Service
@AllArgsConstructor
public class PozariService {
    private final PozariRepository pozariRepository;

    public List<Pozari> downloadDataByDatum(Date datum) {
        String dateForLink = datum.toString();
        HashMap<String, String> links = new HashMap<String, String>();
        links.put("2023-01-17","https://neo.gsfc.nasa.gov/servlet/RenderData?si=1846568&cs=rgb&format=CSV&width=360&height=180");
        links.put("2023-01-16","https://neo.gsfc.nasa.gov/servlet/RenderData?si=1846559&cs=rgb&format=CSV&width=360&height=180");
        links.put("2023-01-15","https://neo.gsfc.nasa.gov/servlet/RenderData?si=1846567&cs=rgb&format=CSV&width=360&height=180");
        links.put("2023-01-14","https://neo.gsfc.nasa.gov/servlet/RenderData?si=1846558&cs=rgb&format=CSV&width=360&height=180");
        links.put("2023-01-13","https://neo.gsfc.nasa.gov/servlet/RenderData?si=1846556&cs=rgb&format=CSV&width=360&height=180");
        links.put("2023-01-12","https://neo.gsfc.nasa.gov/servlet/RenderData?si=1846562&cs=rgb&format=CSV&width=360&height=180");
        links.put("2023-01-11","https://neo.gsfc.nasa.gov/servlet/RenderData?si=1846563&cs=rgb&format=CSV&width=360&height=180");
        links.put("2023-01-10","https://neo.gsfc.nasa.gov/servlet/RenderData?si=1846390&cs=rgb&format=CSV&width=360&height=180");

        try {
            FileUtils.copyURLToFile(
                    new URL(links.get(dateForLink)),
                    new File("DataFolder\\Pozari.csv"));
        } catch (Exception exc) {
            return new ArrayList<>();
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
                             if (value >= 0.1001 && value <= 1.0) {
                                pozari.add(new Pozari(new PrimaryKeyId(datum, Longitude, Latitude), 1));
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
        return pozari;
    }

    public Pozari downloadDataByDatumAndCoordinates(Date datum, int longitude, int latitude) {
        String dateForLink = datum.toString();
        HashMap<String, String> links = new HashMap<String, String>();
        links.put("2023-01-17","https://neo.gsfc.nasa.gov/servlet/RenderData?si=1846568&cs=rgb&format=CSV&width=360&height=180");
        links.put("2023-01-16","https://neo.gsfc.nasa.gov/servlet/RenderData?si=1846559&cs=rgb&format=CSV&width=360&height=180");
        links.put("2023-01-15","https://neo.gsfc.nasa.gov/servlet/RenderData?si=1846567&cs=rgb&format=CSV&width=360&height=180");
        links.put("2023-01-14","https://neo.gsfc.nasa.gov/servlet/RenderData?si=1846558&cs=rgb&format=CSV&width=360&height=180");
        links.put("2023-01-13","https://neo.gsfc.nasa.gov/servlet/RenderData?si=1846556&cs=rgb&format=CSV&width=360&height=180");
        links.put("2023-01-12","https://neo.gsfc.nasa.gov/servlet/RenderData?si=1846562&cs=rgb&format=CSV&width=360&height=180");
        links.put("2023-01-11","https://neo.gsfc.nasa.gov/servlet/RenderData?si=1846563&cs=rgb&format=CSV&width=360&height=180");
        links.put("2023-01-10","https://neo.gsfc.nasa.gov/servlet/RenderData?si=1846390&cs=rgb&format=CSV&width=360&height=180");

        try {
            FileUtils.copyURLToFile(
                    new URL(links.get(dateForLink)),
                    new File("DataFolder\\Pozari.csv"));
        } catch (Exception exc) {
            return null;
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
                            if (Longitude == longitude && Latitude == latitude) {
                                if (value > 0.2 && value <= 1.0) {
                                    return new Pozari(new PrimaryKeyId(datum, Longitude, Latitude), 1);
                                } else {
                                    return new Pozari(new PrimaryKeyId(datum, Longitude, Latitude), 0);
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
