package Assignment1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.*;

public class FlightHandler {

    private static Map<String, Integer> dayToNumber;

    // map day string to a number for comparison purpose
    static {
        dayToNumber = new HashMap<>();
        dayToNumber.put("Mon", 1);
        dayToNumber.put("Tue", 2);
        dayToNumber.put("Wed", 3);
        dayToNumber.put("Thu", 4);
        dayToNumber.put("Fri", 5);
        dayToNumber.put("Sat", 6);
        dayToNumber.put("Sun", 7);
    }

    /**
     * add a new flight to the flight scheduler system
     * need to check whether the user input flight data is valid
     * @cmd FLIGHT ADD <departure time> <from> <to> <capacity>
     * @param args array of user input arguments to create a new flight
     * @param flightScheduler flight scheduler object
     * @param showAddResult whether display add flight result on the screen
     * @return flight add success or failure
     */
    public static boolean handleAddFlight(String[] args, FlightScheduler flightScheduler, boolean showAddResult) {
        try {
            if (args.length < 7) throw new IllegalArgumentException(SystemMessage.FLIGHT_ADD_CMD_USAGE);

            //check day time format
            String departureDayTime = args[2] + " " + args[3];
            try {
                Utility.longDayTimeFormatter.parse(departureDayTime);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Invalid departure time. Use the format <day_of_week> <hour:minute>, with 24h time.");
            }

            //check start location
            String startLocation = args[4];
            if (!flightScheduler.getLocationMap().containsKey(startLocation.toUpperCase())) throw new IllegalArgumentException("Invalid starting location.");

            //check end location
            String endLocation = args[5];
            if (!flightScheduler.getLocationMap().containsKey(endLocation.toUpperCase())) throw new IllegalArgumentException("Invalid starting location.");

            //check start and end locations are same
            if (startLocation.equalsIgnoreCase(endLocation)) throw new IllegalArgumentException("Source and destination cannot be the same place.");

            //TODO: check scheduling

            //check capacity
            if ((!Utility.isNumeric(args[6]) || Integer.parseInt(args[6]) <= 0)) throw new IllegalArgumentException("Invalid positive integer capacity.");
            int capacity = Integer.parseInt(args[6]);

            //add number of book if has one
            int numOfBook = 0;
            if (args.length == 8) {
                if ((!Utility.isNumeric(args[7]) || Integer.parseInt(args[7]) < 0)) throw new IllegalArgumentException("Invalid number of passenger book.");
                numOfBook = Integer.parseInt(args[7]);
            }

            int id = flightScheduler.getFlightList().size();
            Flight newFlight = new Flight(id, departureDayTime, startLocation, endLocation, capacity, numOfBook);
            flightScheduler.getFlightList().add(newFlight);
            flightScheduler.getFlightMap().put(id, newFlight);

            if (showAddResult) System.out.println("Successfully added Flight " + id + ".");

        } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * Import flight CSV file
     * CSV Format: day time,startLocation,endLocation,capacity,booked
     * @param args array of user input arguments
     * @param flightScheduler flight scheduler object
     * @return number of imports and invalid row
     * @exception FileNotFoundException no filename is given, or the file doesn’t exist.
     */
    public static void handleFlightImport(String[] args, FlightScheduler flightScheduler) {
        int importCnt = 0, invalidCnt = 0;
        try {
            if (args.length < 3) throw new FileNotFoundException("Error reading file.");
            File csvFile = new File(args[2]);
            Scanner fileReader = new Scanner(csvFile);

            //read CSV file
            while (fileReader.hasNextLine()) {
                String row = fileReader.nextLine();
                String[] dataFields = row.split(",");

                //extract flight data and create flight objects
                if (dataFields.length < 5) {
                    invalidCnt++;
                } else {
                    String[] flightArgs = {"", "", dataFields[0].split(" ")[0], dataFields[0].split(" ")[1], dataFields[1], dataFields[2], dataFields[3], dataFields[4]};
                    if (handleAddFlight(flightArgs, flightScheduler, false)) importCnt++;
                    else invalidCnt++;
                }
            }

            //generate file import response;
            String importResult = "";
            importResult += "Imported " + importCnt +  (importCnt > 1 ? " flights." : " flight.");
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
     * export flights information as CSV file
     * Export should write to the file flights in the order of their flight id,
     * @param args array of user input arguments
     * @param flightScheduler flight scheduler object
     * @IOException
     */
    public static void handleFlightExport(String[] args, FlightScheduler flightScheduler) {
        try {
            if (args.length < 3) throw new FileNotFoundException("Error writing file.");
            FileWriter fileWriter = new FileWriter(args[2]);

            //Sort flight list based on id and write content to csv file
            Collections.sort(flightScheduler.getFlightList(), (f1, f2) -> f1.getId() - f2.getId());

            for (Flight flight: flightScheduler.getFlightList()) {
                fileWriter.write(flight.toString());
            }
            int numOfFlight = flightScheduler.getFlightList().size();
            System.out.println("Export " + numOfFlight + " of " + (numOfFlight > 1 ? "flights." : "flight."));
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error writing file.");
        }
    }

    /**
     * list all available flights ordered by departure time,
     * then departure location name
     * @param args array of user input arguments
     * @param flightScheduler flight scheduler object
     */
    public static void handleListAllFlights(String[] args, FlightScheduler flightScheduler) {
        try {
            if (args.length > 1) throw new IllegalArgumentException(SystemMessage.GENERAL_INVALID_MSG);
            //print the headers
            System.out.println("Flights");
            System.out.println("-------------------------------------------------------");
            System.out.printf("%-4s%-14s%-14s%s\n", "ID", "Departure", "Arrival", "Source --> Destination");
            System.out.println("-------------------------------------------------------");
            if (flightScheduler.getFlightList().size() == 0) {
                System.out.println("(None)");
                return;
            }

            //sort the flight array list
            Collections.sort(flightScheduler.getFlightList(), (f1, f2) -> {
                // sort according to day first, then time
                if (!f1.getDepartureDayTime().equals(f2.getDepartureDayTime())) {
                    if (f1.getDepartureDay().equals(f2.getDepartureDay())) {
                        SimpleDateFormat tempFormatter = new SimpleDateFormat("HH:mm");
                        try {
                            Date d1 = tempFormatter.parse(f1.getDepartureTime());
                            Date d2 = tempFormatter.parse(f2.getDepartureTime());
                            return (int) (d1.getTime() - d2.getTime());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    return dayToNumber.get(f1.getDepartureDay()) - dayToNumber.get(f2.getDepartureDay());
                } else {
                    //sort according to departure location name
                    return f1.getStartLocation().compareTo(f2.getStartLocation());
                }
            });

            //print out flight information
            for (Flight flight: flightScheduler.getFlightList()) {
                System.out.printf("%-4s%-14s%-14s%s\n", flight.getId(), flight.getDepartureDay() + " " + flight.getDepartureTime(), flight.getArriveDay() + " " + flight.getDepartureTime(), flight.getStartLocation() + " --> " + flight.getEndLocation());
            }

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * view information about a flight (from->to, departure arrival times,
     * current ticket price, capacity, passengers booked)
     * @param flightId flight id
     * @param flightScheduler
     */
    public static void handleViewSingleFlight(int flightId, FlightScheduler flightScheduler) {
        Flight flight = flightScheduler.getFlightMap().get(flightId);
        DecimalFormat distanceFormatter = new DecimalFormat("#,###.00");

        System.out.printf("%-14s%s\n", "Departure:", flight.getDepartureDay() + " " + flight.getDepartureTime() + " " + flight.getStartLocation());
        System.out.printf("%-14s%s\n", "Arrival:", flight.getArriveDay() + " " + flight.getArriveTime() + " " + flight.getEndLocation());
        System.out.printf("%-14s%s\n", "Distance:", distanceFormatter.format(flight.getDistance()));
        System.out.printf("%-14s%s\n", "Duration:", flight.getDurationStr());
        System.out.printf("%-14s%s\n", "Ticket Cost:", "$" + flight.getPrice());
        System.out.printf("%-14s%s\n", "Passengers:", flight.getNumOfBook() + "/" + flight.getCapacity());
    }

}
