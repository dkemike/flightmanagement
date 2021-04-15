package Assignment1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;

public class LocationHandler {

    /**
     * add a new location to the flight scheduler system
     * need to check whether the user input location data is valid
     * @cmd LOCATION ADD <name> <lat> <long> <demand_coefficient>
     * @param args array of user input arguments to create a new location
     * @param flightScheduler flight scheduler object
     * @param showAddResult whether display add location result on the screen
     * @return location add success or failure
     */
    public static boolean handleAddLocation(String[] args, FlightScheduler flightScheduler, boolean showAddResult) {
        try {
            //check argument length
            if (args.length < 6) throw new IllegalArgumentException(SystemMessage.LOCATION_ADD_CMD_USAGE);

            //check location name
            String name = args[2];
            if (flightScheduler.getLocationMap().containsKey(name.toUpperCase())) throw new IllegalArgumentException("This location already exists.");

            //check latitude
            if (!Utility.isNumeric(args[3]) || Double.parseDouble(args[3]) < -85 || Double.parseDouble(args[3]) > 85) throw new IllegalArgumentException("Invalid latitude. It must be a number of degrees between -85 and +85.");
            double latitude = Double.parseDouble(args[3]);

            //check longitude
            if (!Utility.isNumeric(args[4]) || Double.parseDouble(args[4]) < -180 || Double.parseDouble(args[4]) > 180) throw new IllegalArgumentException("Invalid longitude. It must be a number of degrees between -180 and +180.");
            double longitude = Double.parseDouble(args[4]);

            //check demand coefficient
            if (!Utility.isNumeric(args[5]) || Double.parseDouble(args[5]) < -1 || Double.parseDouble(args[5]) > 1) throw new IllegalArgumentException("Invalid demand coefficient. It must be a number between -1 and +1.");
            double demandCoefficient = Double.parseDouble(args[5]);

            //add new location to the system
            Location newLocation = new Location(name, latitude, longitude, demandCoefficient);
            flightScheduler.getLocationList().add(newLocation);
            flightScheduler.getLocationMap().put(newLocation.getUppercaseName(), newLocation);
            if (showAddResult) System.out.println("Successfully added location " + name);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * Import location CSV file
     * CSV Format: locationName,latitude,longitude,demandCoefficient
     * @cmd LOCATION IMPORT <filename>
     * @param args array of user input arguments for import a location csv file
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
                    String[] locationArgs = {"", "", dataFields[0], dataFields[1], dataFields[2], dataFields[3]};
                    if (handleAddLocation(locationArgs, flightScheduler, false)) importCnt++;
                    else invalidCnt++;
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
            System.out.println("Error reading file.");
        }
    }

    /**
     * export locations information as CSV file
     * Export should write to the file location based on the location name
     * @cmd LOCATION EXPORT <filename>
     * @param args array of user input arguments to expoer a location csv file
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
            System.out.println("Error writing file.");
        }
    }

    /**
     * list all available locations in alphabetical order
     * @cmd LOCATIONS
     * @param args array of user input arguments to list all location
     * @param flightScheduler flight scheduler object
     */
    public static void handleListAllLocations(String[] args, FlightScheduler flightScheduler) {
        try {
            if (args.length > 1) throw new IllegalArgumentException(SystemMessage.GENERAL_INVALID_MSG);
            if (flightScheduler.getLocationList().size() == 0) {
                System.out.println("(None)");
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

    /**
     * view details about a location (it’s name, coordinates, demand coefficient)
     * @cmd LOCATION <name>
     * @param name location name input
     * @param flightScheduler flight scheduler object
     */
    public static void handleViewSingleLocation(String name, FlightScheduler flightScheduler) {
        Location location = flightScheduler.getLocationMap().get(name.toUpperCase());
        System.out.printf("%-14s%s\n", "Location:", location.getName());
        System.out.printf("%-14s%s\n", "Latitude:", location.getLatitude());
        System.out.printf("%-14s%s\n", "Longitude:", location.getLongitude());
        System.out.printf("%-14s%s\n", "Demand:", (location.getDemandCoefficient() >= 0 ? "+" : "") + location.getDemandCoefficient());
    }
}
