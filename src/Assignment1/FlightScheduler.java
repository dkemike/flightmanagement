package Assignment1;

import java.util.*;

public class FlightScheduler {
    private static FlightScheduler instance;
    private List<Flight> flightList;
    private List<Location> locationList;
    private Map<Integer, Flight> flightMap;
    private Map<String, Location> locationMap;

    public FlightScheduler(String[] args) {
        flightList = new ArrayList<>();
        locationList = new ArrayList<>();
        flightMap = new HashMap<>();
        locationMap = new HashMap<>();
    }

    public static FlightScheduler getInstance() {
        return instance;
    }

    public List<Flight> getFlightList() {
        return flightList;
    }

    public void setFlightList(List<Flight> flightList) {
        this.flightList = flightList;
    }

    public List<Location> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
    }

    public Map<Integer, Flight> getFlightMap() {
        return flightMap;
    }

    public void setFlightMap(Map<Integer, Flight> flightMap) {
        this.flightMap = flightMap;
    }

    public Map<String, Location> getLocationMap() {
        return locationMap;
    }

    public void setLocationMap(Map<String, Location> locationMap) {
        this.locationMap = locationMap;
    }

    /**
     * process the rgument of the user input and switch to relevant
     * methods to continue process the rest of the user arguments
     * @param args an array of user input arguments
     */
    public void processArguments(String[] args) {
        // first layer argument
        switch(args[0].toUpperCase()) {
            //second layer argument
            case UserCommand.FLIGHT:// FLIGHT ...
                if (args.length == 1) {
                    System.out.println(SystemMessage.FLIGHT_CMD_USAGE);
                    break;
                }
                switch (args[1].toUpperCase()) {
                    case UserCommand.IMPORT: // FLIGHT IMPORT <filename>
                        FlightHandler.handleFlightImport(args, this);
                        break;
                    case UserCommand.EXPORT: // FLIGHT EXPORT <filename>
                        FlightHandler.handleFlightExport(args, this);
                        break;
                    default: // FLIGHT <id> ...
                        //check whether flight id is valid
                        if (!args[1].matches("-?\\d+(\\.\\d+)?") || !flightMap.containsKey(Integer.valueOf(args[1]))) {
                            System.out.println("Invalid Flight ID");
                            break;
                        }
                        int flightId = Integer.parseInt(args[1]);
                        if (args.length == 2) { // FLIGHT <id>
                            FlightHandler.handleViewSingleFlight(flightId, this);
                        }
                        break;
                }
                break;
            case UserCommand.FLIGHTS: // FLIGHTS
                FlightHandler.handleListAllFlights(args, this);
                break;
            case UserCommand.LOCATION: // LOCATION ...
                if (args.length == 1) {
                    System.out.println(SystemMessage.LOCATION_CMD_USAGE);
                    break;
                }
                switch (args[1].toUpperCase()) {
                    case UserCommand.IMPORT: // LOCATION IMPORT <filename>
                        LocationHandler.handleLocationImport(args, this);
                        break;
                    case UserCommand.EXPORT: // LOCATION EXPORT <filename>
                        LocationHandler.handleLocationExport(args, this);
                        break;
                    default:
                        //check whether location name is valid
                        if (!locationMap.containsKey(args[1].toUpperCase())) {
                            System.out.println("Invalid location name.");
                            break;
                        }

                }
                break;
            case UserCommand.LOCATIONS:
                LocationHandler.handleListAllLocations(args, this);
                break;
            case UserCommand.HELP:
                System.out.println(SystemMessage.HELP_MSG);
                break;
            case UserCommand.EXIT:
                System.out.println(SystemMessage.EXIT_SYSTEM_MSG);
                break;
            default:
                System.out.println(SystemMessage.GENERAL_INVALID_MSG);
        }
    }

    /**
     * process argument for FLIGHT command
     * @param args array of user input arguments
     */
    public void processFlightArgument(String[] args) {
        String arg2 = args[1].toUpperCase();
        switch (arg2) {
            case UserCommand.IMPORT:
                FlightHandler.handleFlightImport(args, this);
                break;
            case UserCommand.EXPORT:
                FlightHandler.handleFlightExport(args, this);
                break;

        }
    }

    public void run() {
        // Do not use System.exit() anywhere in your code,
        // otherwise it will also exit the auto test suite.
        // Also, do not use static attributes otherwise
        // they will maintain the same values between testcases.

        // START YOUR CODE HERE
        String cmdStr = "";
        Scanner in = new Scanner(System.in);
        do {
            System.out.print("User: ");
            String[] cmds = in.nextLine().split(" ");
            processArguments(cmds);
        } while (true);
    }

    public static void main(String[] args) {
        instance = new FlightScheduler(args);
        instance.run();
    }




}
