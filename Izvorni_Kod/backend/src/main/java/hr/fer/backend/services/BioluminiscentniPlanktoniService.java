package hr.fer.backend.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
@AllArgsConstructor
public class BioluminiscentniPlanktoniService {

    List<BioluminiscentniPlanktoniResponse> list = new ArrayList<BioluminiscentniPlanktoniResponse>();
    for(float latitude = -90.00000; latitude <= 90.00000; latitude+=0.00001){
        for(float longitude = -90.00000; longitude <= 90.00000; longitude+=0.00001){
            for (LocalDate date = LocalDate.now().minusDays(365); date.isBefore(date.plusDays(365)); date = date.plusDays(1)){

                if(latitude <= -34.99065 && latitude >=-35.13755    // Jervis Bay, Australia
                        && longitude >= 150.67139 && longitude <= 150.78752
                        && (date.getMonthValue() == 12 || date.getMonthValue() ==1 || date.getMonthValue() ==2){
                    list.add(BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= -38.10605 && latitude >=-38.19360   // Gippsland Lakes, Australia
                        && longitude >= 147.39955 && longitude <= 147.51379
                        && (date.getMonthValue() == 12 || date.getMonthValue() ==1 || date.getMonthValue() ==2){
                    list.add(BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= -41.09586 && latitude >=-41.10272   // Preservation Bay, Australia
                        && longitude >= 144.04034 && longitude <= 146.05398
                        && (date.getMonthValue() == 12 || date.getMonthValue() ==1 || date.getMonthValue() ==2){
                    list.add(BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 5.20978 && latitude >= 5.20437   // Mudhdhoo, Maldives
                        && longitude >= 73.08028 && longitude <= 73.08844
                        && (date.getMonthValue() >= 7 && date.getMonthValue() <= 12){
                    list.add(BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 10.79093 && latitude >= 10.64082   // Koh Rong Islands, Cambodia
                        && longitude >= 103.17263 && longitude <= 103.33056
                        && (date.getMonthValue() >= 6 && date.getMonthValue() <= 9){
                    list.add(BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 10.60800 && latitude >=  10.57156  // Saracen Bay, Cambodia
                        && longitude >= 103.30046 && longitude <= 103.33205
                        && (date.getMonthValue() >= 6 || date.getMonthValue() <= 9){
                    list.add(BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 7.68210 && latitude >= 7.67624   // Maya Bay, Thailand
                        && longitude >= 98.76347 && longitude <= 98.76697
                        && (date.getMonthValue() >= 1 && date.getMonthValue() <= 12){
                    list.add(BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 18.10575 && latitude >= 18.09719   // Mosquito Bay, Puerto Rico
                        && longitude >= -65.45165 && longitude <= -65.43908
                        && (date.getMonthValue() == 12 || date.getMonthValue() <= 4){
                    list.add(BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 37.06203 && latitude >= 36.75017   // Toyama Bay, Japan
                        && longitude >= 136.96762 && longitude <= 137.40364
                        && (date.getMonthValue() >= 3 && date.getMonthValue() <= 5){
                    list.add(BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 26.34844 && latitude >= 26.34871   // Matsu Islands, Taiwan
                        && longitude >= 120.21563 && longitude <= 120.23079
                        && (date.getMonthValue() >= 4 && date.getMonthValue() <= 8){
                    list.add(BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 28.37063 && latitude >= 28.28284   // Cocoa Beach, Florida
                        && longitude >= -80.64883 && longitude <= -80.60952
                        && (date.getMonthValue() >= 5 && date.getMonthValue() <= 9){
                    list.add(BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 30.38308 && latitude >= 30.37776   // Navarre Beach, Florida
                        && longitude >= -86.89349 && longitude <= -86.82605
                        && (date.getMonthValue() >= 5 && date.getMonthValue() <= 9){
                    list.add(BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 32.79100 && latitude >= 32.76054   // Mission Bay, California
                        && longitude >= -117.25243 && longitude <= -117.23394
                        && (date.getMonthValue() >= 5 && date.getMonthValue() <= 9){
                    list.add(BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 32.85095 && latitude >= 32.85024   // La Jolla Cove, California
                        && longitude >= -117.27299 && longitude <= -117.27198
                        && (date.getMonthValue() >= 5 && date.getMonthValue() <= 9){
                    list.add(BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 32.97579 && latitude >= 32.93294   // Torrey Pines Beach, California
                        && longitude >= -117.27232 && longitude <= -117.26031
                        && (date.getMonthValue() >= 5 && date.getMonthValue() <= 9){
                    list.add(BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 18.48379 && latitude >= 18.48249   // The Luminous Lagoon, Jamaica
                        && longitude >= -77.63065 && longitude <= -77.62851
                        && (date.getMonthValue() >= 1 && date.getMonthValue() <= 12){
                    list.add(BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 15.86978 && latitude >= 15.85463   // Manialtepec Lagoon, Mexico
                        && longitude >= -97.09874 && longitude <= -97.06128
                        && (date.getMonthValue() >= 7 && date.getMonthValue() <= 12){
                    list.add(BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 21.59040 && latitude >= 21.50000  // Isla Holbox, Mexico
                        && longitude >= -87.40330 && longitude <= -87.08779
                        && (date.getMonthValue() >= 7 && date.getMonthValue() <= 12){
                    list.add(BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 21.07812 && latitude >= 21.03613  // Nichupte’ Lagoon, Mexico
                        && longitude >= -86.81759 && longitude <= -86.78416
                        && (date.getMonthValue() >= 7 && date.getMonthValue() <= 12){
                    list.add(BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 12.30256 && latitude >= 12.27438   // Little Corn Island, Nicaragua
                        && longitude >= -82.99196 && longitude <= -82.96953
                        && (date.getMonthValue() >= 1 && date.getMonthValue() <= 12){
                    list.add(BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= -9.57283 && latitude >= -8.94257   // Bocas del Toro, Panama
                        && longitude >= -82.56231 && longitude <= -82.00475
                        && (date.getMonthValue() >= 1 && date.getMonthValue() <= 12){
                    list.add(BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 6.49802 && latitude >= 6.47858  // Ko Lipe, Thailand
                        && longitude >= 99.28247  && longitude <= 99.31221
                        && (date.getMonthValue() >= 11 || date.getMonthValue() <= 5){
                    list.add(BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 20.73039 && latitude >= 20.72632   // Hair of the Dog Beach, Vietnam
                        && longitude >= 107.03760 && longitude <= 107.04041
                        && (date.getMonthValue() >= 8 && date.getMonthValue() <= 10){
                    list.add(BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 10.44613 && latitude >= 10.42855   // Kaoh Tonsay, Cambodia
                        && longitude >= 104.31380 && longitude <= 104.33577
                        && (date.getMonthValue() >= 6 || date.getMonthValue() <= 9){
                    list.add(BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= -8.51952 && latitude >= -8.54257   // Padang Bai, Bali, Indonesia
                        && longitude >= 115.50862 && longitude <= 115.51531
                        && (date.getMonthValue() == 12 || date.getMonthValue() <= 3){
                    list.add(BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 9.90116 && latitude >= 9.85102   // Kumbalangi, Kerala, India
                        && longitude >= 76.27221 && longitude <= 76.29728
                        && (date.getMonthValue() >= 3 && date.getMonthValue() <= 4){
                    list.add(BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 15.29535 && latitude >= 15.23726   // Betalbatim beach, Goa, India
                        && longitude >= 73.90657 && longitude <= 73.92482
                        && (date.getMonthValue() >= 11 || date.getMonthValue() == 1){
                    list.add(BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 51.50649 && latitude >= 51.49605   // Lough Hyne Nature Reserve, Ireland
                        && longitude >= -9.30917 && longitude <= -9.29381
                        && (date.getMonthValue() >= 6 || date.getMonthValue() <= 9){
                    list.add(BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 51.35468 && latitude >= 51.34072   // Zeebrugge, Belgium
                        && longitude >= 3.11697 && longitude <= 3.27420
                        && (date.getMonthValue() >= 1 && date.getMonthValue() <= 12){
                    list.add(BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else{ list.add(BioluminiscentniPlanktoniResponse(date, longitude, latitude, 0)); }
            }
        }
    }
    return list;
}