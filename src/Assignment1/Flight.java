package Assignment1;


public class Flight {
    private int id;
    private String departureDayTime;
    private String departureDay;
    private String departureTime;
    private String arriveDayTime;
    private String arriveDay;
    private String arriveTime;
    private String startLocation;
    private String endLocation;
    private double distance;
    private String durationStr;
    private int capacity;
    private double price;
    private int numOfBook;

    public Flight(int id, String departureDayTime, String startLocation, String endLocation, int capacity, int numOfBook) {
        this.id = id;
        this.departureDayTime = departureDayTime;
        this.departureDay = departureDayTime.split(" ")[0].substring(0, 3);
        this.departureTime = departureDayTime.split(" ")[1];
        this.arriveDay = "Sun";
        this.arriveTime = "00:00";
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.capacity = capacity;
        this.price = 0;
        this.numOfBook = numOfBook;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartureDay() {
        return departureDay;
    }

    public String getDepartureDayTime() {
        return departureDayTime;
    }

    public void setDepartureDayTime(String departureDayTime) {
        this.departureDayTime = departureDayTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setDepartureDay(String departureDay) {
        this.departureDay = departureDay;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArriveDayTime() {
        return arriveDayTime;
    }

    public void setArriveDayTime(String arriveDayTime) {
        this.arriveDayTime = arriveDayTime;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumOfBook() {
        return numOfBook;
    }

    public void setNumOfBook(int numOfBook) {
        this.numOfBook = numOfBook;
    }

    public String getArriveDay() {
        return arriveDay;
    }

    public void setArriveDay(String arriveDay) {
        this.arriveDay = arriveDay;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getDurationStr() {
        return durationStr;
    }

    public void setDurationStr(String durationStr) {
        this.durationStr = durationStr;
    }

    @Override
    public String toString() {
        return departureDayTime + ","
                + startLocation + ","
                + endLocation + ","
                + capacity + ","
                + numOfBook
                + "\n";
    }


}
