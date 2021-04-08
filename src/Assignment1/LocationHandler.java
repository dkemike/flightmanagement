package Assignment1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

public class LocationHandler {
    /**
     * Import location CSV file
     * CSV Format: locationName,latitude,longitude,demandCoefficient
     * @param flightScheduler flight scheduler object
     * @return number of imports and invalid row
     * @FileNotFoundException no filename is given, or the file doesn’t exist.
     */
    public static void handleLocationImport(String[] args, FlightScheduler flightScheduler) {
        int importCnt = 0, invalidCnt = 0;
        try {
            if (args.length < 3) throw new FileNotFoundException("Error reading file.");
            File csvFile = new File(args[2]);
            Scanner fileReader = new Scanner(csvFile);

            //read CSV file
            while (fileReader.hasNextLine()) {
                String row = fileReader.nextLine();
                String[] dataFields = row.split(",");

                //extract location data and create location objects
                if (dataFields.length < 4) {
                    invalidCnt++;
                } else {
                    String name = dataFields[0];
                    double latitude = Double.parseDouble(dataFields[1]);
                    double longitude = Double.parseDouble(dataFields[2]);
                    double demandCoefficient = Double.parseDouble(dataFields[3]);

                    //TODO: check each data field before creating the location object
                    Location newLocation = new Location(name, latitude, longitude, demandCoefficient);
                    flightScheduler.getLocationList().add(newLocation);
                    flightScheduler.getLocationMap().put(name.toUpperCase(), newLocation);
                    importCnt++;
                }
            }

            //generate file import response;
            String importResult = "";
            importResult += "Imported " + importCnt +  (importCnt > 1 ? " locations." : " location.");
            if (invalidCnt != 0) {
                importResult += "\n" + invalidCnt + (invalidCnt > 1 ? " lines" : " line") + " was invalid.";
            }
            System.out.println(importResult);
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * export locations information as CSV file
     * Export should write to the file location based on the location name
     * @param args array of user input arguments
     * @param flightScheduler flight scheduler object
     * @IOException error writing file
     */
    public static void handleLocationExport(String[] args, FlightScheduler flightScheduler) {
        try {
            if (args.length < 3) throw new FileNotFoundException("Error writing file.");
            FileWriter fileWriter = new FileWriter(args[2]);

            //Sort location list based on names and write content to csv file
            Collections.sort(flightScheduler.getLocationList(), (l1, l2) -> l1.getName().compareTo(l2.getName()));

            for (Location location : flightScheduler.getLocationList()) {
                fileWriter.write(location.toString());
            }
            int numOfLocation = flightScheduler.getLocationList().size();
            System.out.println("Export " + numOfLocation + " of " + (numOfLocation > 1 ? "locations." : "location."));
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * list all available locations in alphabetical order
     * @param args array of user input arguments
     * @param flightScheduler flight scheduler object
     */
    public static void handleListAllLocations(String[] args, FlightScheduler flightScheduler) {
        try {
            if (args.length > 1) throw new IllegalArgumentException(SystemMessage.GENERAL_INVALID_MSG);
            if (flightScheduler.getLocationList().size() == 0) {
                System.out.println("No locations are available");
                return;
            }

            Collections.sort(flightScheduler.getLocationList(), (l1, l2) -> l1.getName().compareTo(l2.getName()));
            int idx = 0, size = flightScheduler.getLocationList().size();
            System.out.println("Locations (" + size + "):");
            for (Location location: flightScheduler.getLocationList()) {
                System.out.print(location.getName());
                System.out.print((++idx != size) ? "," : "\n");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
