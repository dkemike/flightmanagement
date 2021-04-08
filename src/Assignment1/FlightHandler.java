package Assignment1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
                    String departureDayTime = dataFields[0];
                    int id = flightScheduler.getFlightList().size();
                    String startLocation = dataFields[1];
                    String endLocation = dataFields[2];
                    int capacity = Integer.valueOf(dataFields[3]);
                    int numOfBook = Integer.valueOf(dataFields[4]);
                    //TODO: check each data field before creating the flight object
                    Flight newFlight = new Flight(id, departureDayTime, startLocation, endLocation, capacity, numOfBook);
                    flightScheduler.getFlightList().add(newFlight);
                    flightScheduler.getFlightMap().put(id, newFlight);
                    importCnt++;
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
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
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
            if (flightScheduler.getFlightList().size() == 0) {
                System.out.println("No flights are available");
                return;
            }

            //sort the flight array list
            Collections.sort(flightScheduler.getFlightList(), (f1, f2) -> {
                // sort according to day first, then time
                if (!f1.getDepartureDayTime().equals(f2.getDepartureDayTime())) {
                    if (f1.getDepartureDay().equals(f2.getDepartureDay())) {
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                        try {
                            Date d1 = sdf.parse(f1.getDepartureTime());
                            Date d2 = sdf.parse(f2.getDepartureTime());
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
            System.out.println("Flights");
            System.out.println("-------------------------------------------------------");
            System.out.printf("%-4s%-14s%-14s%s\n", "ID", "Departure", "Arrival", "Source --> Destination");
            System.out.println("-------------------------------------------------------");
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
