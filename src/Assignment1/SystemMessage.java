package Assignment1;

public class SystemMessage {
        public static final String HELP_MSG = "FLIGHTS - list all available flights ordered by departure time, then departure location name\n" +
            "FLIGHT ADD <departure time> <from> <to> <capacity> - add a flight\n" +
            "FLIGHT IMPORT/EXPORT <filename> - import/export flights to csv file\n" +
            "FLIGHT <id> - view information about a flight (from->to, departure arrival times, current ticket price, capacity, passengers booked)\n" +
            "FLIGHT <id> BOOK <num> - book a certain number of passengers for the flight at the current ticket price, and then adjust the ticket price to reflect the reduced capacity remaining. If no number is given, book 1 passenger. If the given number of bookings is more than the remaining capacity, only accept bookings until the capacity is full.\n" +
            "FLIGHT <id> REMOVE - remove a flight from the schedule\n" +
            "FLIGHT <id> RESET - reset the number of passengers booked to 0, and the ticket price to its original state.\n" +
            "LOCATIONS - list all available locations in alphabetical order\n" +
            "LOCATION ADD <name> <lat> <long> <demand_coefficient> - add a location\n" +
            "LOCATION <name> - view details about a location (it’s name, coordinates, demand coefficient) LOCATION IMPORT/EXPORT <filename> - import/export locations to csv file\n" +
            "SCHEDULE <location_name> - list all departing and arriving flights, in order of the time they arrive/depart DEPARTURES <location_name> - list all departing flights, in order of departure time\n" +
            "ARRIVALS <location_name> - list all arriving flights, in order of arrival time\n" +
            "TRAVEL <from> <to> [sort] [n] - list the nth possible flight route between a starting location and destination, with a maximum of 3 stopovers. Default ordering is for shortest overall duration. If n is not provided, display the first one in the order. If n is larger than the number of flights available, display the last one in the ordering.\n" +
            "can have other orderings:\n" +
            "TRAVEL <from> <to> cost - minimum current cost TRAVEL <from> <to> duration - minimum total duration TRAVEL <from> <to> stopovers - minimum stopovers TRAVEL <from> <to> layover - minimum layover time TRAVEL <from> <to> flight_time - minimum flight time\n" +
            "HELP – outputs this help string. EXIT – end the program.";

        public static final String GENERAL_INVALID_MSG = "Invalid command. Type 'help' for a list of commands.";
        public static final String EXIT_SYSTEM_MSG = "Application closed.";
        public static final String FLIGHT_CMD_USAGE = "Usage:\nFLIGHT <id> [BOOK/REMOVE/RESET] [num]\nFLIGHT ADD <departure time> <from> <to> <capacity>\nFLIGHT IMPORT/EXPORT <filename>";
        public static final String FLIGHT_ADD_CMD_USAGE = "Usage: FLIGHT ADD <departure time> <from> <to> <capacity>\nExample: FLIGHT ADD Monday 18:00 Sydney Melbourne 120";
        public static final String LOCATION_CMD_USAGE = "Usage:\nLOCATION <name>\nLOCATION ADD <name> <latitude> <longitude> <demand_coefficient>\nLOCATION IMPORT/EXPORT <filename>";
        public static final String LOCATION_ADD_CMD_USAGE = "Usage: LOCATION ADD <name> <lat> <long> <demand_coefficient>\nExample: LOCATION ADD Sydney -33.847927 150.651786 0.2";

}
