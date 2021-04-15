package Assignment1;

import java.util.List;

public class Location {
    private String name;
    private String uppercaseName;
    private double latitude;
    private double longitude;
    private double demandCoefficient;
    private List<Flight> arriveFlights;
    private List<Flight> departFlights;


    public Location(String name, double latitude, double longitude, double demandCoefficient) {
        this.name = name;
        this.uppercaseName = name.toUpperCase();
        this.latitude = latitude;
        this.longitude = longitude;
        this.demandCoefficient = demandCoefficient;
    }

    public List<Flight> getArriveFlights() {
        return arriveFlights;
    }

    public void setArriveFlights(List<Flight> arriveFlights) {
        this.arriveFlights = arriveFlights;
    }

    public List<Flight> getDepartFlights() {
        return departFlights;
    }

    public void setDepartFlights(List<Flight> departFlights) {
        this.departFlights = departFlights;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getDemandCoefficient() {
        return demandCoefficient;
    }

    public void setDemandCoefficient(double demandCoefficient) {
        this.demandCoefficient = demandCoefficient;
    }

    public String getUppercaseName() {
        return uppercaseName;
    }

    public void setUppercaseName(String uppercaseName) {
        this.uppercaseName = uppercaseName;
    }

    @Override
    public String toString() {
        return name + ","
                + latitude + ","
                + longitude + ","
                + demandCoefficient + ","
                + "\n";
    }
}
