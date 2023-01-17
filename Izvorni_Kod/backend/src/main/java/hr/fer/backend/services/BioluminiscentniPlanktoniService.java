package hr.fer.backend.services;

import hr.fer.backend.responseClasses.BioluminiscentniPlanktoniResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BioluminiscentniPlanktoniService {

    public List<BioluminiscentniPlanktoniResponse> getBioluminiscentnePlanktoneByDatum(LocalDate date) {
        List<BioluminiscentniPlanktoniResponse> list = new ArrayList<>();
        for(int latitude = -90; latitude <= 90; latitude+=1){
            for(int longitude = -179; longitude <= 180; longitude+=1){
                if(latitude <= -34.99065 && latitude >=-35.13755    // Jervis Bay, Australia
                        && longitude >= 150.67139 && longitude <= 151.78752
                        && (date.getMonthValue() == 12 || date.getMonthValue() ==1 || date.getMonthValue() ==2)){
                    list.add(new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= -38.10605 && latitude >=-39.19360   // Gippsland Lakes, Australia
                        && longitude >= 147.39955 && longitude <= 148.51379
                        && (date.getMonthValue() == 12 || date.getMonthValue() ==1 || date.getMonthValue() ==2)){
                    list.add(new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= -41.09586 && latitude >=-42.10272   // Preservation Bay, Australia
                        && longitude >= 144.04034 && longitude <= 146.05398
                        && (date.getMonthValue() == 12 || date.getMonthValue() ==1 || date.getMonthValue() ==2)){
                    list.add(new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 6.20978 && latitude >= 5.20437   // Mudhdhoo, Maldives
                        && longitude >= 73.08028 && longitude <= 74.08844
                        && date.getMonthValue() >= 7){
                    list.add(new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 11.79093 && latitude >= 10.64082   // Koh Rong Islands, Cambodia
                        && longitude >= 103.17263 && longitude <= 104.33056
                        && (date.getMonthValue() >= 6 && date.getMonthValue() <= 9)){
                    list.add(new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else // Maya Bay, Thailand
                    if(latitude <= 11.60800 && latitude >=  10.57156  // Saracen Bay, Cambodia
                        && longitude >= 102.30046 && longitude <= 103.33205
                        && (date.getMonthValue() >= 6 && date.getMonthValue() <= 9)){
                    list.add(new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 8.68210 && latitude >= 7.67624
                        && longitude >= 98.76347 && longitude <= 99.76697) {
                    list.add(new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 19 && latitude >= 18   // Mosquito Bay, Puerto Rico
                        && longitude >= -66 && longitude <= -65
                        && (date.getMonthValue() == 12 || date.getMonthValue() <= 4)){
                    list.add(new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 37.06203 && latitude >= 36.75017   // Toyama Bay, Japan
                        && longitude >= 136.96762 && longitude <= 137.40364
                        && (date.getMonthValue() >= 3 && date.getMonthValue() <= 5)){
                    list.add(new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 27.34844 && latitude >= 26.34871   // Matsu Islands, Taiwan
                        && longitude >= 120.21563 && longitude <= 121.23079
                        && (date.getMonthValue() >= 4 && date.getMonthValue() <= 8)){
                    list.add(new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 29.37063 && latitude >= 28.28284   // Cocoa Beach, Florida
                        && longitude >= -81.64883 && longitude <= -80.60952
                        && (date.getMonthValue() >= 5 && date.getMonthValue() <= 9)){
                    list.add(new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 31.38308 && latitude >= 30.37776   // Navarre Beach, Florida
                        && longitude >= -87.89349 && longitude <= -86.82605
                        && (date.getMonthValue() >= 5 && date.getMonthValue() <= 9)){
                    list.add(new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 33.79100 && latitude >= 32.76054   // Mission Bay, California
                        && longitude >= -118.25243 && longitude <= -117.23394
                        && (date.getMonthValue() >= 5 && date.getMonthValue() <= 9)){
                    list.add(new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 33.85095 && latitude >= 32.85024   // La Jolla Cove, California
                        && longitude >= -118.27299 && longitude <= -117.27198
                        && (date.getMonthValue() >= 5 && date.getMonthValue() <= 9)){
                    list.add(new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 33.97579 && latitude >= 32.93294   // Torrey Pines Beach, California
                        && longitude >= -117.27232 && longitude <= -116.26031
                        && (date.getMonthValue() >= 5 && date.getMonthValue() <= 9)){
                    list.add(new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 19.48379 && latitude >= 18.48249   // The Luminous Lagoon, Jamaica
                        && longitude >= -78.63065 && longitude <= -77.62851){
                    list.add(new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 16.86978 && latitude >= 15.85463   // Manialtepec Lagoon, Mexico
                        && longitude >= -98.09874 && longitude <= -97.06128
                        && date.getMonthValue() >= 7){
                    list.add(new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 22.59040 && latitude >= 21.50000  // Isla Holbox, Mexico
                        && longitude >= -88.40330 && longitude <= -87.08779
                        && date.getMonthValue() >= 7){
                    list.add(new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 22.07812 && latitude >= 21.03613  // Nichupte’ Lagoon, Mexico
                        && longitude >= -87.81759 && longitude <= -86.78416
                        && date.getMonthValue() >= 7){
                    list.add(new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 13.30256 && latitude >= 12.27438   // Little Corn Island, Nicaragua
                        && longitude >= -83.99196 && longitude <= -82.96953){
                    list.add(new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= -9.57283 && latitude >= -8.94257   // Bocas del Toro, Panama
                        && longitude >= -83.56231 && longitude <= -82.00475){
                    list.add(new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 7.49802 && latitude >= 6.47858  // Ko Lipe, Thailand
                        && longitude >= 99.28247  && longitude <= 100.31221
                        && (date.getMonthValue() >= 11 || date.getMonthValue() <= 5)){
                    list.add(new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 21.73039 && latitude >= 20.72632   // Hair of the Dog Beach, Vietnam
                        && longitude >= 107.03760 && longitude <= 108.04041
                        && (date.getMonthValue() >= 8 && date.getMonthValue() <= 10)){
                    list.add(new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 11.44613 && latitude >= 10.42855   // Kaoh Tonsay, Cambodia
                        && longitude >= 104.31380 && longitude <= 105.33577
                        && (date.getMonthValue() >= 6 && date.getMonthValue() <= 9)){
                    list.add(new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= -8.51952 && latitude >= -9.54257   // Padang Bai, Bali, Indonesia
                        && longitude >= 115.50862 && longitude <= 116.51531
                        && (date.getMonthValue() == 12 || date.getMonthValue() <= 3)){
                    list.add(new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 10.90116 && latitude >= 9.85102   // Kumbalangi, Kerala, India
                        && longitude >= 76.27221 && longitude <= 77.29728
                        && (date.getMonthValue() >= 3 && date.getMonthValue() <= 4)){
                    list.add(new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 15.29535 && latitude >= 14.23726   // Betalbatim beach, Goa, India
                        && longitude >= 72.90657 && longitude <= 73.92482
                        && (date.getMonthValue() >= 11 || date.getMonthValue() == 1)){
                    list.add(new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 52.50649 && latitude >= 51.49605   // Lough Hyne Nature Reserve, Ireland
                        && longitude >= -10.30917 && longitude <= -9.29381
                        && (date.getMonthValue() >= 6 && date.getMonthValue() <= 9)){
                    list.add(new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
                else if(latitude <= 52.35468 && latitude >= 51.34072   // Zeebrugge, Belgium
                        && longitude >= 2.11697 && longitude <= 3.27420) {
                        list.add(new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1));
                }
            }
        }
    return list;
    }

    public BioluminiscentniPlanktoniResponse getgetBioluminiscentnePlanktoneByDatumAndCoordinates(LocalDate date, int longitude, int latitude) {
        if(latitude <= -34.99065 && latitude >=-35.13755    // Jervis Bay, Australia
                && longitude >= 150.67139 && longitude <= 151.78752
                && (date.getMonthValue() == 12 || date.getMonthValue() ==1 || date.getMonthValue() ==2)){
            return new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1);
        }
        else if(latitude <= -38.10605 && latitude >=-39.19360   // Gippsland Lakes, Australia
                && longitude >= 147.39955 && longitude <= 148.51379
                && (date.getMonthValue() == 12 || date.getMonthValue() ==1 || date.getMonthValue() ==2)){
            return new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1);
        }
        else if(latitude <= -41.09586 && latitude >=-42.10272   // Preservation Bay, Australia
                && longitude >= 144.04034 && longitude <= 146.05398
                && (date.getMonthValue() == 12 || date.getMonthValue() ==1 || date.getMonthValue() ==2)){
            return new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1);
        }
        else if(latitude <= 6.20978 && latitude >= 5.20437   // Mudhdhoo, Maldives
                && longitude >= 73.08028 && longitude <= 74.08844
                && date.getMonthValue() >= 7){
            return new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1);
        }
        else if(latitude <= 11.79093 && latitude >= 10.64082   // Koh Rong Islands, Cambodia
                && longitude >= 103.17263 && longitude <= 104.33056
                && (date.getMonthValue() >= 6 && date.getMonthValue() <= 9)){
            return new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1);
        }
        else // Maya Bay, Thailand
            if(latitude <= 11.60800 && latitude >=  10.57156  // Saracen Bay, Cambodia
                    && longitude >= 102.30046 && longitude <= 103.33205
                    && (date.getMonthValue() >= 6 && date.getMonthValue() <= 9)){
                return new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1);
            }
            else if(latitude <= 8.68210 && latitude >= 7.67624
                    && longitude >= 98.76347 && longitude <= 99.76697) {
                return new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1);
            }
            else if(latitude <= 19 && latitude >= 18   // Mosquito Bay, Puerto Rico
                    && longitude >= -66 && longitude <= -65
                    && (date.getMonthValue() == 12 || date.getMonthValue() <= 4)){
                return new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1);
            }
            else if(latitude <= 37.06203 && latitude >= 36.75017   // Toyama Bay, Japan
                    && longitude >= 136.96762 && longitude <= 137.40364
                    && (date.getMonthValue() >= 3 && date.getMonthValue() <= 5)){
                return new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1);
            }
            else if(latitude <= 27.34844 && latitude >= 26.34871   // Matsu Islands, Taiwan
                    && longitude >= 120.21563 && longitude <= 121.23079
                    && (date.getMonthValue() >= 4 && date.getMonthValue() <= 8)){
                return new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1);
            }
            else if(latitude <= 29.37063 && latitude >= 28.28284   // Cocoa Beach, Florida
                    && longitude >= -81.64883 && longitude <= -80.60952
                    && (date.getMonthValue() >= 5 && date.getMonthValue() <= 9)){
                return new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1);
            }
            else if(latitude <= 31.38308 && latitude >= 30.37776   // Navarre Beach, Florida
                    && longitude >= -87.89349 && longitude <= -86.82605
                    && (date.getMonthValue() >= 5 && date.getMonthValue() <= 9)){
                return new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1);
            }
            else if(latitude <= 33.79100 && latitude >= 32.76054   // Mission Bay, California
                    && longitude >= -118.25243 && longitude <= -117.23394
                    && (date.getMonthValue() >= 5 && date.getMonthValue() <= 9)){
                return new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1);
            }
            else if(latitude <= 33.85095 && latitude >= 32.85024   // La Jolla Cove, California
                    && longitude >= -118.27299 && longitude <= -117.27198
                    && (date.getMonthValue() >= 5 && date.getMonthValue() <= 9)){
                return new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1);
            }
            else if(latitude <= 33.97579 && latitude >= 32.93294   // Torrey Pines Beach, California
                    && longitude >= -117.27232 && longitude <= -116.26031
                    && (date.getMonthValue() >= 5 && date.getMonthValue() <= 9)){
                return new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1);
            }
            else if(latitude <= 19.48379 && latitude >= 18.48249   // The Luminous Lagoon, Jamaica
                    && longitude >= -78.63065 && longitude <= -77.62851){
                return new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1);
            }
            else if(latitude <= 16.86978 && latitude >= 15.85463   // Manialtepec Lagoon, Mexico
                    && longitude >= -98.09874 && longitude <= -97.06128
                    && date.getMonthValue() >= 7){
                return new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1);
            }
            else if(latitude <= 22.59040 && latitude >= 21.50000  // Isla Holbox, Mexico
                    && longitude >= -88.40330 && longitude <= -87.08779
                    && date.getMonthValue() >= 7){
                return new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1);
            }
            else if(latitude <= 22.07812 && latitude >= 21.03613  // Nichupte’ Lagoon, Mexico
                    && longitude >= -87.81759 && longitude <= -86.78416
                    && date.getMonthValue() >= 7){
                return new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1);
            }
            else if(latitude <= 13.30256 && latitude >= 12.27438   // Little Corn Island, Nicaragua
                    && longitude >= -83.99196 && longitude <= -82.96953){
                return new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1);
            }
            else if(latitude <= -9.57283 && latitude >= -8.94257   // Bocas del Toro, Panama
                    && longitude >= -83.56231 && longitude <= -82.00475){
                return new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1);
            }
            else if(latitude <= 7.49802 && latitude >= 6.47858  // Ko Lipe, Thailand
                    && longitude >= 99.28247  && longitude <= 100.31221
                    && (date.getMonthValue() >= 11 || date.getMonthValue() <= 5)){
                return new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1);
            }
            else if(latitude <= 21.73039 && latitude >= 20.72632   // Hair of the Dog Beach, Vietnam
                    && longitude >= 107.03760 && longitude <= 108.04041
                    && (date.getMonthValue() >= 8 && date.getMonthValue() <= 10)){
                return new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1);
            }
            else if(latitude <= 11.44613 && latitude >= 10.42855   // Kaoh Tonsay, Cambodia
                    && longitude >= 104.31380 && longitude <= 105.33577
                    && (date.getMonthValue() >= 6 && date.getMonthValue() <= 9)){
                return new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1);
            }
            else if(latitude <= -8.51952 && latitude >= -9.54257   // Padang Bai, Bali, Indonesia
                    && longitude >= 115.50862 && longitude <= 116.51531
                    && (date.getMonthValue() == 12 || date.getMonthValue() <= 3)){
                return new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1);
            }
            else if(latitude <= 10.90116 && latitude >= 9.85102   // Kumbalangi, Kerala, India
                    && longitude >= 76.27221 && longitude <= 77.29728
                    && (date.getMonthValue() >= 3 && date.getMonthValue() <= 4)){
                return new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1);
            }
            else if(latitude <= 15.29535 && latitude >= 14.23726   // Betalbatim beach, Goa, India
                    && longitude >= 72.90657 && longitude <= 73.92482
                    && (date.getMonthValue() >= 11 || date.getMonthValue() == 1)){
                return new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1);
            }
            else if(latitude <= 52.50649 && latitude >= 51.49605   // Lough Hyne Nature Reserve, Ireland
                    && longitude >= -10.30917 && longitude <= -9.29381
                    && (date.getMonthValue() >= 6 && date.getMonthValue() <= 9)){
                return new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1);
            }
            else if(latitude <= 52.35468 && latitude >= 51.34072   // Zeebrugge, Belgium
                    && longitude >= 2.11697 && longitude <= 3.27420) {
                return new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 1);
            }
            else{ return new BioluminiscentniPlanktoniResponse(date, longitude, latitude, 0); }
    }
}