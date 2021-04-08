package OnlineTask2;

import java.util.concurrent.Flow;

public class Flower {
    private String colour;
    private String species;
    private double age;
    private int maxAge;
    private double maxHeight;

    /**
     * Create a new flower. Colour and species comparisons should be case insensitive. Age is initially 0.
     * @param colour
     * @param species
     * @param mAge If age is negative, then the flower is already dead.
     * @param mHeight
     */
    Flower(String colour, String species, int mAge, double mHeight) {
        this.colour = colour;
        this.species = species;
        this.maxAge = mAge;
        this.maxHeight = mHeight;
        this.age = 0;
    }

    /**
     * The flower grows, increasing its age by 1. If it reaches beyond its
     * max age, it dies. Dead flowers cannot grow anymore.
     * @return true if the flower is still alive, false if it died or is already dead
     */
    public boolean grow() {
        if (!isAlive()) return false;
        age++;
        return isAlive();
    }

    /**
     * For each free space inside the pot, the flower grows an additional 0.5 units,
     * up to a maximum of 2.5 units additional.
     * It still grows by a base of 1 each time, so the total maximum growth is 3.5.
     * Dead flowers cannot grow anymore.
     * @param pot The pot which this flower is growing inside.
     * @return true if the flower is still alive, false if it died or is already dead
     */
    public boolean growInside(Pot pot) {
        if (!isAlive()) return false;

        final double MAX_GROW_LIMIT = 3.5;
        double totalGrow = 1 + (pot.size() - pot.count()) * 0.5;
        age += totalGrow > MAX_GROW_LIMIT ? 3.5 : totalGrow;

        return isAlive();
    }

    /**
     * Check if the flower is alive.
     * @return whether the flower is alive or not
     */
    public boolean isAlive() {
        return age <= maxAge;
    }

    /**
     * @return The flower's colour
     */
    public String getColour() {
        return colour;
    }

    /**
     * @return The flower's species
     */
    public String getSpecies() {
        return species;
    }

    /**
     * Return the flower's age. If the flower died due to external circumstances, return -1. Otherwise, if the flower died naturally,
     * return the age it was at when it died (this is a value greater than maxAge).
     * @return
     */
    public double getAge() {
        return maxAge < 0 ? -1 : age;
    }

    /**
     * Height is determined by the following formula: H = (2/pi) * tan^-1(age/maxHeight) * maxHeight
     * @return The flower's height
     */
    public double getHeight() {
        return (2.0 / Math.PI) * Math.atan(age / maxHeight) * maxHeight;
    }

    /**
     * The flower dies due to external circumstances, such as being eaten by a caterpillar.
     */
    public void die() {
        maxAge = -1;
    }

    /**
     * Compare a flower that matches the given parameter exactly (case insensitive) in
     * terms of colour, species, age (within the nearest whole number) and height (within 0.001).
     * Need to handle the situation when colour or species are null
     * @param o - a flower object
     * @return boolean indicates whether two flower objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Flower)) return false;
        Flower f = (Flower) o;
        if ((this.getColour() == null && f.getColour() != null) || (this.getColour() != null && f.getColour() == null)) return false;
        if (!this.getColour().equalsIgnoreCase(f.getColour())) return false;

        if ((this.getSpecies() == null && f.getSpecies() != null) || (this.getSpecies() != null && f.getSpecies() == null)) return false;
        if (!this.getSpecies().equalsIgnoreCase(f.getSpecies())) return false;
        if ((int)this.getAge() != (int)f.getAge()) return false;
        if (Math.abs(this.getHeight() - f.getHeight()) > 0.001) return false;

        return true;
    }


    /**
     * Flower object deep clone
     * @return flower clone object
     */
    @Override
    public Flower clone() {
        Flower cloneFlower = new Flower(colour, species, maxAge, maxHeight);
        cloneFlower.age = age;
        return cloneFlower;
    }
}
